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
import com.example.cardemo.view.Er1WaveUtil
import com.example.cardemo.view.FullEcgAmpAdapter
import com.example.cardemo.view.WavePara
import com.example.cardemo.view.WavePara.drawTask
import com.example.cardemo.view.WavePara.offerTask
import com.example.cardemo.view.WavePara.updateSignal
import com.example.cardemo.view.WaveView
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
    companion object{
        val ecgData = MutableLiveData<IntArray>()
    }
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
        WaveView.drawSize = (mm / 12.5f * 125f).toInt()
        WaveView.nd = width.toFloat() / WaveView.drawSize
        WaveView.data = IntArray(WaveView.drawSize) {
            0
        }
    }
    private fun intToIp(paramInt: Int): String {
        return ((paramInt.and(255)).toString() + "." + (paramInt.shr(8).and(255)) + "." + (paramInt.shr(16).and(255)) + "."
                + (paramInt.shr(24).and(255)))
    }
    var ecgArray:ArrayList<Short> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PathUtil.initVar(this)
        getScreenInfo()


        updateSignal.observe(this) {
            binding.waveView.invalidate()
        }

        leadStatus.observe(this) {
            binding.leadStatus.text = it
        }

        var status = 0

        ecgData.observe(this) {


            for (k in 0 until it.size) {
                WavePara.waveDataX.offer(Er1WaveUtil.byteTomV(it[k]))
            }
            if(status==1){
                dataScope.launch {
                    for (k in 0 until it.size) {
                        ecgArray.add(it[k].toShort())
                    }
                }

            }
        }



        binding.start.text = "开始录制"
        binding.status.text = "未录制"

        binding.start.setOnClickListener {
            if (status == 0) {
                ecgArray.clear()
                status = 1
                binding.start.text = "停止录制"
                binding.status.text = "正在录制"

            } else {
                status = 0
                binding.start.text = "开始录制"
                binding.status.text = "未录制"
                val byteBuffer= ByteBuffer.allocate(2*ecgArray.size).order(ByteOrder.LITTLE_ENDIAN)
                for(k in 0 until ecgArray.size){
                   byteBuffer.putShort(ecgArray[k])
                }
                val timeString=java.text.SimpleDateFormat("yyyyMMddHHmmss").format(java.util.Date())+".dat"
                Log.e("vaca",timeString);
                dataScope.launch{
                    Log.e("vaca",timeString);
                    sleep(100)
                    val file=File(PathUtil.getPathX(timeString))
                    file.writeBytes(byteBuffer.array())
                   withContext(Dispatchers.Main){
                        Toast.makeText(this@MainActivity,"正在上传",Toast.LENGTH_SHORT).show()
                    }
                    try {
                        val str=NetUtils.postFile("http://vaca.tpddns.cn:9889/ecg_file", file);
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@MainActivity,"上传成功",Toast.LENGTH_SHORT).show()
                        }
                    }catch (e:Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@MainActivity,"上传失败",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
        val wifiManager =getSystemService(Context.WIFI_SERVICE) as WifiManager
        val gate=intToIp(wifiManager.dhcpInfo.ipAddress)
        binding.status.text = gate

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

    override fun onStart() {
        initEcg()
        initOfferTask()
        super.onStart()
    }

    override fun onStop() {
        drawTask?.cancel()
        drawTask = null
        super.onStop()
    }

    private fun initEcg() {
        if (drawTask == null) {
            try {
                WaveView.reset()
                WaveView.disp = true
            } catch (e: Exception) {

            }
            drawTask = WaveView.Companion.DrawTask()
            Timer().schedule(drawTask, Date(), 32)
        }
    }


    private fun initOfferTask() {
        if (offerTask == null) {
            offerTask = WaveView.Companion.OfferTask()
            Timer().schedule(offerTask, Date(), 8)
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
        val mCarPropertyManager = car.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager

        mCarPropertyManager.registerCallback(object :
            CarPropertyManager.CarPropertyEventCallback {
            override fun onChangeEvent(carPropertyValue: CarPropertyValue<*>?) {
                if (carPropertyValue != null) {
                    if (carPropertyValue.value is IntArray) {
                        ecgData.postValue(carPropertyValue.value as IntArray)
                        var string = ""
                        for (k in 0 until (carPropertyValue.value as IntArray).size) {
                            string += Er1WaveUtil.byteTomV((carPropertyValue.value as IntArray)[k])
                                .toString() + " "
                        }
                    } else if (carPropertyValue.value is Array<*>) {
                        val temp = carPropertyValue.value as Array<*>
                        if (temp[0] is Int) {
                            val intArray2 = IntArray(temp.size) {
                                temp[it] as Int
                            }
                            ecgData.postValue(intArray2)
                        }
                    } else {
                        //print the type of carPropertyValue.value


                        binding.ecgValue.text =
                            "value is not int array, " + carPropertyValue.value.javaClass.name
                    }
                }
            }

            override fun onErrorEvent(p0: Int, p1: Int) {

            }

        }, propertyID.ID_ECG_VALUE, 10F)

        mCarPropertyManager.registerCallback(object :
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

        }, propertyID.ID_ECG_LEAD_OFFST, 10F)

    }

}