package com.smm.help

import android.app.Activity
import android.app.Application
import android.view.View
import android.widget.Toast
import com.smm.help.util.LogUtil
import com.smm.help.util.SharedPreferenceUtil
import com.smm.help.util.ToastUtil
import com.smm.help.util.UIUtil

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
        internal var width = 0F//宽度

        /**
         * 建造者
         *
         * @param application 应用
         * */
        class Builder(private val application: Application) {
            private var debug = false//调试
            private var width = 0F//宽度

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
             * 设置宽度
             *
             * @param width 宽度
             * @return Builder 建造者
             * */
            fun setWidth(width: Float): Builder {
                this.width = width
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
                config.width = width
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
        val width = config?.width ?: 0F
        if (width > 0F) adapt(config?.application, width)
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

    /**
     * 获取屏幕宽度
     *
     * @return Int 屏幕宽度
     * */
    @JvmStatic
    fun getScreenWidth() = UIUtil.getScreenWidth(config?.application)

    /**
     * 获取屏幕高度
     *
     * @return Int 屏幕高度
     * */
    @JvmStatic
    fun getScreenHeight() = UIUtil.getScreenHeight(config?.application)

    /**
     * dp转px
     *
     * @param value 值
     * @return Float px
     * */
    @JvmStatic
    fun dp2px(value: Float) = UIUtil.dp2px(config?.application, value)

    /**
     * px转dp
     *
     * @param value 值
     * @return Float dp
     * */
    @JvmStatic
    fun px2dp(value: Float) = UIUtil.px2dp(config?.application, value)

    /**
     * sp转px
     *
     * @param value 值
     * @return Float px
     * */
    @JvmStatic
    fun sp2px(value: Float) = UIUtil.sp2px(config?.application, value)

    /**
     * px转sp
     *
     * @param context 上下文
     * @param value 值
     * @return Float sp
     * */
    @JvmStatic
    fun px2sp(value: Float) = UIUtil.px2sp(config?.application, value)

    /**
     * dp转sp
     *
     * @param value 值
     * @return Float sp
     * */
    @JvmStatic
    fun dp2sp(value: Float) = UIUtil.dp2sp(config?.application, value)

    /**
     * sp转dp
     *
     * @param value 值
     * @return Float dp
     * */
    @JvmStatic
    fun sp2dp(value: Float) = UIUtil.sp2dp(config?.application, value)

    /**
     * 设置可见
     *
     * @param visibility 可见
     * @param views 视图组
     * */
    @JvmStatic
    fun setVisibility(visibility: Int, vararg views: View?) =
        UIUtil.setVisibility(visibility, *views)

    /**
     * 适配
     *
     * @param application 应用
     * @param width 宽度
     * */
    @JvmStatic
    private fun adapt(application: Application?, width: Float) = UIUtil.adapt(application, width)

    /**
     * 适配
     *
     * @param application 应用
     * @param activity 活动
     * @param width 宽度
     * */
    @JvmStatic
    fun adapt(application: Application?, activity: Activity?, width: Float) =
        UIUtil.adapt(application, activity, width)
}