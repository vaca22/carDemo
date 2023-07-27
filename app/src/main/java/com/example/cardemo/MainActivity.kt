package com.example.cardemo

import android.car.Car
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.cardemo.databinding.ActivityMainBinding
import com.example.cardemo.view.Converter
import com.example.cardemo.view.Er1WaveUtil
import com.example.cardemo.view.WavePara
import com.example.cardemo.view.WavePara.drawTask
import com.example.cardemo.view.WavePara.offerTask
import com.example.cardemo.view.WavePara.updateSignal
import com.example.cardemo.view.WaveView
import java.util.Date
import java.util.Timer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val leadStatus=MutableLiveData<String>()
    val ecgData=MutableLiveData<IntArray>()

    fun getScreenInfo(){
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        Log.e("vaca", "height: $height, width: $width")

        val mm= Converter.pxToMm(width.toFloat(), this)
        WaveView.drawSize= (mm/12.5f*125f).toInt()
        WaveView.nd = width.toFloat() / WaveView.drawSize
        WaveView.data = IntArray(WaveView.drawSize) {
            0
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getScreenInfo()


        updateSignal.observe(this) {
            binding.waveView.invalidate()
        }

        leadStatus.observe(this){
            binding.leadStatus.text=it
        }

        ecgData.observe(this){
            for(k in 0 until it.size){
               WavePara.waveDataX.offer(Er1WaveUtil.byteTomV(it[k]))
            }
        }

        initAndroidCar()
    }

    override fun onStart() {
        initEcg()
//        initOfferTask()
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
                Log.e("vaca", "reset error: $e")
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
                    if(carPropertyValue.value is IntArray){
                        ecgData.postValue(carPropertyValue.value as IntArray)
                        var string=""
                        for(k in 0 until (carPropertyValue.value as IntArray).size){
                           string+=Er1WaveUtil.byteTomV((carPropertyValue.value as IntArray)[k]).toString()+" "
                        }
                    }else{
                        binding.ecgValue.text= "value is not int array"
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
                    if(carPropertyValue.value is Int){
                        if(carPropertyValue.value == 1){
                            leadStatus.postValue("导联状态：脱落")
                        }else{
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