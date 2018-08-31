package com.ahuo.fire.hellovideo

import android.app.Application

import com.ahuo.fire.hellovideo.core.AppConfig
import com.ahuo.tools.util.MLog
import com.ahuo.tools.util.PreferencesUtils
import com.ahuo.tools.util.ToastUtils

/**
 * description :
 * author : LiuHuiJie
 * created on : 2018/5/4
 */
class HvApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        MLog.init(BuildConfig.KK_LOG, AppConfig.APP_LOG_TAG)
        PreferencesUtils.init(this)
        ToastUtils.init(this)
    }

    companion object {
        var instance: HvApp?=null
    }
}
