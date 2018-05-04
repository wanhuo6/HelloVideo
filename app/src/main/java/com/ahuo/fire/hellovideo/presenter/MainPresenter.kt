package com.ahuo.fire.hellovideo.presenter

import com.ahuo.fire.hellovideo.base.BaseMvpPresenter
import com.ahuo.fire.hellovideo.contract.IMainContract

/**
 * description :
 * author : LiuHuiJie
 * created on : 2018/5/4
 */
class MainPresenter : BaseMvpPresenter<IMainContract.IMainViewI>(), IMainContract.IMainPresenter {

    override fun getData() {
      //  mCompositeDisposable!!.add(null!!)
        mView!!.getDataSuccess()
    }

}
