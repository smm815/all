package com.smm.help

import android.app.Application
import android.widget.Toast
import com.smm.help.util.LogUtil
import com.smm.help.util.SharedPreferenceUtil
import com.smm.help.util.ToastUtil

/**
 * 帮助
 * */
object Help {
    private var config: Config? = null//配置

    /**
     * 配置
     *
     * @param application 应用
     * */
    class Config private constructor(internal val application: Application) {
        internal var debug = false//调试

        /**
         * 建造者
         *
         * @param application 应用
         * */
        class Builder(private val application: Application) {
            private var debug = false//调试

            /**
             * 设置调试
             *
             * @param debug 调试
             * @return Builder 建造者
             * */
            fun setDebug(debug: Boolean): Builder {
                this.debug = debug
                return this
            }

            /**
             * 建造
             *
             * @return Config 配置
             * */
            fun build(): Config {
                val config = Config(application)
                config.debug = debug
                return config
            }
        }
    }

    /**
     * 初始化
     *
     * @param config 配置
     * */
    @JvmStatic
    fun init(config: Config?) {
        this.config = config
        LogUtil.debug = this.config?.debug ?: false
    }

    /**
     * 日志
     *
     * @param tag 标签
     * @param msg 信息
     * */
    @JvmStatic
    fun log(tag: Any?, msg: Any?) = LogUtil.print(tag, msg)

    /**
     * 吐司
     *
     * @param msg 信息
     * */
    @JvmStatic
    fun toast(msg: Any?, duration: Int = Toast.LENGTH_SHORT) =
        ToastUtil.show(config?.application, msg, duration)

    /**
     * 存储共享参数
     *
     * @param name 名
     * @param key 键
     * @param value 值
     * */
    @JvmStatic
    fun putSharedPreference(name: String?, key: String?, value: Any?) =
        SharedPreferenceUtil.put(config?.application, name, key, value)

    /**
     * 获取共享参数
     *
     * @param name 名
     * @param key 键
     * @param defValue 默认值
     * @return Any? 共享参数
     * */
    @JvmStatic
    fun getSharedPreference(name: String?, key: String?, defValue: Any?) =
        SharedPreferenceUtil.get(config?.application, name, key, defValue)
}