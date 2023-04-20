package com.smm.help.util

import android.content.Context
import android.widget.Toast

/**
 * 吐司工具
 * */
internal object ToastUtil {
    private var toast: Toast? = null//吐司

    /**
     * 显示
     *
     * @param context 上下文
     * @param msg 信息
     * @param duration 持续时间
     * */
    @JvmStatic
    fun show(context: Context?, msg: Any?, duration: Int = Toast.LENGTH_SHORT) {
        try {
            toast?.cancel()
            toast = Toast.makeText(context, msg.toString(), duration)
            toast?.show()
        } catch (_: Exception) {
        }
    }
}