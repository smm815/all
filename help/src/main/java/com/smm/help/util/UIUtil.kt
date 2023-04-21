package com.smm.help.util

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View

/**
 * UI工具
 * */
internal object UIUtil {
    private var density = 0F//像素密度
    private var scaledDensity = 0F//缩放像素密度
    private var densityDpi = 0//屏幕像素密度

    /**
     * 适配
     *
     * @param application 应用
     * @param width 宽度
     * */
    @JvmStatic
    fun adapt(application: Application?, width: Float) {
        application?.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                adapt(application, activity, width)
            }

            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
        application?.registerComponentCallbacks(object : ComponentCallbacks {
            override fun onConfigurationChanged(configuration: Configuration) {
                try {
                    if (configuration.fontScale > 0) {
                        val applicationDisplayMetrics =
                            application.resources?.displayMetrics ?: return
                        scaledDensity =
                            applicationDisplayMetrics.scaledDensity * density / applicationDisplayMetrics.density
                    }
                } catch (_: Exception) {
                }
            }

            override fun onLowMemory() {}
        })
    }

    /**
     * 适配
     *
     * @param application 应用
     * @param activity 活动
     * @param width 宽度
     * */
    @JvmStatic
    fun adapt(application: Application?, activity: Activity?, width: Float) {
        try {
            if (density == 0F) {
                val applicationDisplayMetrics = application?.resources?.displayMetrics ?: return
                density = applicationDisplayMetrics.widthPixels / width
                scaledDensity =
                    applicationDisplayMetrics.scaledDensity * density / applicationDisplayMetrics.density
                densityDpi = (160 * density).toInt()
                applicationDisplayMetrics.density = density
                applicationDisplayMetrics.scaledDensity = scaledDensity
                applicationDisplayMetrics.densityDpi = densityDpi
            }
            val activityDisplayMetrics = activity?.resources?.displayMetrics ?: return
            activityDisplayMetrics.density = density
            activityDisplayMetrics.scaledDensity = scaledDensity
            activityDisplayMetrics.densityDpi = densityDpi
        } catch (_: Exception) {
        }
    }

    /**
     * 获取屏幕宽度
     *
     * @param context 上下文
     * @return Int 屏幕宽度
     * */
    @JvmStatic
    fun getScreenWidth(context: Context?) = context?.resources?.displayMetrics?.widthPixels ?: 0

    /**
     * 获取屏幕高度
     *
     * @param context 上下文
     * @return Int 屏幕高度
     * */
    @JvmStatic
    fun getScreenHeight(context: Context?) = context?.resources?.displayMetrics?.heightPixels ?: 0

    /**
     * dp转px
     *
     * @param context 上下文
     * @param value 值
     * @return Float px
     * */
    @JvmStatic
    fun dp2px(context: Context?, value: Float): Float {
        val density = context?.resources?.displayMetrics?.density ?: return value
        return value * density
    }

    /**
     * px转dp
     *
     * @param context 上下文
     * @param value 值
     * @return Float dp
     * */
    @JvmStatic
    fun px2dp(context: Context?, value: Float): Float {
        val density = context?.resources?.displayMetrics?.density ?: return value
        return value / density
    }

    /**
     * sp转px
     *
     * @param context 上下文
     * @param value 值
     * @return Float px
     * */
    @JvmStatic
    fun sp2px(context: Context?, value: Float): Float {
        val scaledDensity = context?.resources?.displayMetrics?.scaledDensity ?: return value
        return value * scaledDensity
    }

    /**
     * px转sp
     *
     * @param context 上下文
     * @param value 值
     * @return Float sp
     * */
    @JvmStatic
    fun px2sp(context: Context?, value: Float): Float {
        val scaledDensity = context?.resources?.displayMetrics?.scaledDensity ?: return value
        return value / scaledDensity
    }

    /**
     * dp转sp
     *
     * @param context 上下文
     * @param value 值
     * @return Float sp
     * */
    @JvmStatic
    fun dp2sp(context: Context?, value: Float): Float {
        val displayMetrics = context?.resources?.displayMetrics ?: return value
        return value * displayMetrics.density / displayMetrics.scaledDensity
    }

    /**
     * sp转dp
     *
     * @param context 上下文
     * @param value 值
     * @return Float dp
     * */
    @JvmStatic
    fun sp2dp(context: Context?, value: Float): Float {
        val displayMetrics = context?.resources?.displayMetrics ?: return value
        return value * displayMetrics.scaledDensity / displayMetrics.density
    }

    /**
     * 设置可见
     *
     * @param visibility 可见
     * @param views 视图组
     * */
    @JvmStatic
    fun setVisibility(visibility: Int, vararg views: View?) =
        views.forEach { view -> if (visibility != view?.visibility) view?.visibility = visibility }
}