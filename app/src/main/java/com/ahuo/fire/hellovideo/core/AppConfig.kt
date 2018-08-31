package com.ahuo.fire.hellovideo.core


import com.ahuo.fire.hellovideo.BuildConfig

/**
 * Created on 17-8-1
 *
 * @author liuhuijie
 */

interface AppConfig {
    companion object {

        val API_HOST = BuildConfig.API_HOST

        val APP_LOG_TAG = "Hello_video"

        val APP_SP_NAME = "hv_share_data"

        val APP_WX_ID = ""

        val APP_WX_SCOPE="snsapi_userinfo"

        val APP_WX_STATE="wechat_hehe_auth";

    }


}
