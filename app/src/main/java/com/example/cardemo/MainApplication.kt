package com.example.cardemo

import android.app.Application

class MainApplication:Application() {
    companion object{
        lateinit var instance:MainApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}