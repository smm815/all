package com.smm.demo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.smm.help.Help

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View?>(R.id.btn_test)?.setOnClickListener {
            val name = "demo"
            val key = "token"
            Help.putSharedPreference(name, key, "${(0..10).random()}")
            Help.toast(Help.getSharedPreference(name, key, ""))
        }
    }
}