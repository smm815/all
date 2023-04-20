package com.smm.demo

import android.app.Application
import com.smm.help.Help

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Help.init(Help.Config.Builder(this).setDebug(true).build())
    }
}