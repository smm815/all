@file:Suppress("unchecked_cast")

package com.smm.help.util

import android.content.Context

/**
 * 共享参数工具
 * */
internal object SharedPreferenceUtil {
    /**
     * 存储
     *
     * @param context 上下文
     * @param name 名
     * @param key 键
     * @param value 值
     * */
    @JvmStatic
    fun put(context: Context?, name: String?, key: String?, value: Any?) {
        try {
            context?.getSharedPreferences(name, Context.MODE_PRIVATE)?.edit()?.apply {
                when (value) {
                    is Boolean -> putBoolean(key, value)
                    is Float -> putFloat(key, value)
                    is Int -> putInt(key, value)
                    is Long -> putLong(key, value)
                    is String -> putString(key, value)
                    is Set<*> -> putStringSet(key, value as? Set<String?>?)
                }
            }?.apply()
        } catch (_: Exception) {
        }
    }

    /**
     * 获取
     *
     * @param context 上下文
     * @param name 名
     * @param key 键
     * @param defValue 默认值
     * @return Any? 值
     * */
    @JvmStatic
    fun get(context: Context?, name: String?, key: String?, defValue: Any?): Any? {
        try {
            context?.getSharedPreferences(name, Context.MODE_PRIVATE)?.run {
                return when (defValue) {
                    is Boolean -> getBoolean(key, defValue)
                    is Float -> getFloat(key, defValue)
                    is Int -> getInt(key, defValue)
                    is Long -> getLong(key, defValue)
                    is String -> getString(key, defValue)
                    is Set<*> -> getStringSet(key, defValue as? Set<String?>?)
                    else -> null
                }
            }
        } catch (_: Exception) {
        }
        return null
    }
}