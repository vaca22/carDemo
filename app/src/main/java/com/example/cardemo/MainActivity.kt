package com.example.cardemo

import android.car.Car
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cardemo.databinding.ActivityMainBinding
import com.example.cardemo.utils.NetUtils
import com.example.cardemo.utils.PathUtil
import com.example.cardemo.view.Converter
import com.example.cardemo.view.EcgWaveUtil
import com.example.cardemo.view.FullEcgAmpAdapter
import com.example.cardemo.view.WavePara
import com.example.cardemo.view.WavePara.drawTask

import com.example.cardemo.view.WavePara.updateSignal
import com.example.cardemo.view.WaveView
import com.viatom.gqsdk.EcgAnalysis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.lang.Thread.sleep
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.Date
import java.util.Timer

class MainActivity : AppCompatActivity() {


    val dataScope = CoroutineScope(Dispatchers.IO)
    private lateinit var binding: ActivityMainBinding

    val leadStatus = MutableLiveData<String>()



    val ampx = arrayOf("1.25 mm/mV", "2.5 mm/mV", "5 mm/mV", "10 mm/mV", "20 mm/mV")
    val ampn = arrayOf(0.125f, 0.25f, 0.5f, 1f, 2f)
    var currentIndex = 3
    private fun getScreenInfo() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        Log.e("vaca", "height: $height, width: $width")

        val mm = Converter.pxToMm(width.toFloat(), this)

    }

    private fun intToIp(paramInt: Int): String {
        return ((paramInt.and(255)).toString() + "." + (paramInt.shr(8)
            .and(255)) + "." + (paramInt.shr(16).and(255)) + "."
                + (paramInt.shr(24).and(255)))
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        EcgAnalysis.init()
        PathUtil.initVar(this)
        getScreenInfo()



        updateSignal.observe(this) {
            binding.waveView.invalidate()
        }

        leadStatus.observe(this) {
            binding.leadStatus.text = it
        }



        Timer().scheduleAtFixedRate(object : java.util.TimerTask() {
            override fun run() {
                runOnUiThread {
                    binding.msgCounter.text = "${WavePara.waveDataX.size}"
                }


            }
        }, 0, 1000)




        initEcg()
        initAmp()
        initAndroidCar()
    }

    fun initAmp() {
        val buttonAdapter = FullEcgAmpAdapter(this)
        val lm = object : LinearLayoutManager(this, RecyclerView.VERTICAL, false) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }
        binding.reg.layoutManager = lm
        buttonAdapter.addAll(ampx)
        buttonAdapter.setSelect(currentIndex)
        val updateAmp = MutableLiveData<Int>()
        updateAmp.observe(this) { u ->
            WavePara.realTimeDoubler = ampn[u]
            binding.amm.text = ampx[u]
            binding.amp2.visibility = View.GONE
        }
        binding.amp.setOnClickListener {
            if (binding.amp2.visibility != View.VISIBLE) {
                binding.amp2.visibility = View.VISIBLE
            } else {
                binding.amp2.visibility = View.GONE
            }
        }

        buttonAdapter.myGo = object : FullEcgAmpAdapter.WantInfo {
            override fun go(u: Int) {
                updateAmp.postValue(u)
                Log.e("index", u.toString())
                currentIndex = u
            }
        }
        binding.reg.adapter = buttonAdapter
    }



    private fun initEcg() {
        if (drawTask == null) {
            try {
                WaveView.reset()
                WaveView.disp = true
            } catch (e: Exception) {

            }
            drawTask = WaveView.Companion.DrawTask()
            Timer().scheduleAtFixedRate(drawTask, Date(), 32)
        }
    }


    var mCarPropertyManager: CarPropertyManager? = null

    val ecgValueCallback=object :
        CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(carPropertyValue: CarPropertyValue<*>?) {
            if (carPropertyValue != null) {
                if (carPropertyValue.value is Array<*>) {
                    val temp = carPropertyValue.value as Array<*>
                    val intArray2 = IntArray(temp.size) {
                        temp[it] as Int
                    }
                    //move intArray2 last  3 number to first
                    //create new intArray3
                    val intArray3 = IntArray(intArray2.size) {
                        0
                    }
                    //move intArray2 last  3 number to first

                    for (k in 0 until 3) {
                        intArray3[k] = intArray2[intArray2.size - 3 + k]
                    }
                    for (k in 0 until intArray2.size - 3) {
                        intArray3[k+3] =intArray2[k]
                    }

                    Log.e("vaca", "ecg: ${intArray3.size}")




                    for (k in 0 until intArray3.size) {
                        WavePara.waveDataX.offer( EcgWaveUtil.byteToFilter(intArray3[k]) )
                    }
                }
            }
        }

        override fun onErrorEvent(p0: Int, p1: Int) {

        }

    }

    val ecgLeadCallback=object :
        CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(carPropertyValue: CarPropertyValue<*>?) {
            if (carPropertyValue != null) {
                if (carPropertyValue.value is Int) {
                    if (carPropertyValue.value == 1) {
                        leadStatus.postValue("导联状态：脱落")
                    } else {
                        leadStatus.postValue("导联状态：正常")
                    }
                }
            }
        }

        override fun onErrorEvent(p0: Int, p1: Int) {

        }

    }



    private fun initAndroidCar() {
        val car = Car.createCar(this)
        if (car == null) {
            binding.hint.text = "car is null"
            Log.e("vaca", "car is null")
            return
        } else {
            binding.hint.text = "create car success"
            Log.e("vaca", "car is not null")
        }
        mCarPropertyManager = car.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager
        mCarPropertyManager?.registerCallback(ecgValueCallback, propertyID.ID_ECG_VALUE, 10F)
        mCarPropertyManager?.registerCallback(ecgLeadCallback, propertyID.ID_ECG_LEAD_OFFST, 10F)
    }

    override fun onDestroy() {
        mCarPropertyManager?.unregisterCallback(ecgValueCallback, propertyID.ID_ECG_VALUE)
        mCarPropertyManager?.unregisterCallback(ecgLeadCallback, propertyID.ID_ECG_LEAD_OFFST)
        super.onDestroy()

    }

}