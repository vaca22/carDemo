package com.example.cardemo

import android.app.Application
import com.tencent.bugly.crashreport.CrashReport

class MainApplication:Application() {
    companion object{
        lateinit var instance:MainApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
        CrashReport.initCrashReport(this, "febb03b363", false)
    }
}