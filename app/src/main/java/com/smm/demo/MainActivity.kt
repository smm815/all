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
            val screenWidth = Help.getScreenWidth()
            Help.log("TAG", "screenWidth $screenWidth")
            val screenHeight = Help.getScreenHeight()
            Help.log("TAG", "screenHeight $screenHeight")
            var px = Help.dp2px(50F)
            Help.log("TAG", "dp2px(50F) $px")
            var dp = Help.px2dp(px)
            Help.log("TAG", "px2dp($px) $dp")
            px = Help.sp2px(50F)
            Help.log("TAG", "sp2px(50F) $px")
            var sp = Help.px2sp(px)
            Help.log("TAG", "px2sp($px) $sp")
            sp = Help.dp2sp(dp)
            Help.log("TAG", "dp2sp($dp) $sp")
            dp = Help.sp2dp(sp)
            Help.log("TAG", "sp2dp($sp) $dp")
        }
    }
}