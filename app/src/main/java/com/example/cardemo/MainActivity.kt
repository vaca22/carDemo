package com.example.cardemo

import android.car.Car
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cardemo.databinding.ActivityMainBinding
import com.example.cardemo.propertyID.mControlIds
import com.example.cardemo.propertyID.mControlNames
import com.example.cardemo.view.CustomAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customAdapter = CustomAdapter()
        customAdapter.mCarDataList.clear()
        mControlNames.forEachIndexed { index, s -> customAdapter.mCarDataList.add(CarData(s,mControlIds[index],null)) }
        binding.recyclerView.adapter = customAdapter
        binding.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this).apply {
            orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
        }

        initAndroidCar()
    }


    fun initAndroidCar(){
        val car = Car.createCar(this)
        if(car==null){
            Log.e("vaca","car is null")
            return
        }
        val mCarPropertyManager = car.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager
        for(i in mControlIds){
            mCarPropertyManager.registerCallback(object:CarPropertyManager.CarPropertyEventCallback{
                override fun onChangeEvent(carPropertyValue: CarPropertyValue<*>?) {
                    if(carPropertyValue!=null){
                        Log.e("vaca","onchange event id: "+carPropertyValue.propertyId+" value: "+carPropertyValue.value)
                        customAdapter.setProperty(carPropertyValue.propertyId,carPropertyValue.value)
                    }else{
                        Log.e("vaca"," value: null")
                    }
                }

                override fun onErrorEvent(p0: Int, p1: Int) {

                }

            },i, 10F)
        }
    }

}