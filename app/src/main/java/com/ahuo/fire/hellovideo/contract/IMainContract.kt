package com.ahuo.fire.hellovideo.contract

import com.ahuo.fire.hellovideo.base.IBaseMvpView

/**
 * description :
 * author : LiuHuiJie
 * created on : 2018/5/4
 */
interface IMainContract {

    interface IMainPresenter {
        fun getData()

        fun wxLogin()

    }

    interface IMainViewI : IBaseMvpView {
        fun getDataSuccess()

        fun loginFail(message:String)
    }
}
