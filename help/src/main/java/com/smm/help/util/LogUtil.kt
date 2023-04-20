package com.smm.help.util

import android.util.Log

/**
 * 日志工具
 * */
internal object LogUtil {
    @JvmField
    var debug = false//调试

    /**
     * 打印
     *
     * @param tag 标签
     * @param msg 信息
     * */
    @JvmStatic
    fun print(tag: Any?, msg: Any?) {
        if (debug) Log.d(tag.toString(), msg.toString())
    }
}