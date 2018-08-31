package com.ahuo.fire.hellovideo.presenter

import com.ahuo.fire.hellovideo.HvApp
import com.ahuo.fire.hellovideo.base.BaseMvpPresenter
import com.ahuo.fire.hellovideo.contract.IMainContract
import com.ahuo.fire.hellovideo.core.AppConfig
import com.ahuo.tools.util.ToastUtils
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

/**
 * description :
 * author : LiuHuiJie
 * created on : 2018/5/4
 */
class MainPresenter : BaseMvpPresenter<IMainContract.IMainViewI>(), IMainContract.IMainPresenter {
    override fun wxLogin() {
        var iWXApi: IWXAPI= WXAPIFactory.createWXAPI(HvApp.instance!!.applicationContext,AppConfig.APP_WX_ID,false)
        if (!iWXApi.isWXAppInstalled){
           mView!!.loginFail("未安装")
            return
        }
        var a = iWXApi.registerApp(AppConfig.APP_WX_ID)
        if (!a){
            mView!!.loginFail("未授权")
            return
        }
        val req = SendAuth.Req()
        req.scope = AppConfig.APP_WX_SCOPE
        req.state = AppConfig.APP_WX_STATE
        iWXApi.sendReq(req)
    }

    override fun getData() {
      //  mCompositeDisposable!!.add(null!!)
        mView!!.getDataSuccess()
    }



}
