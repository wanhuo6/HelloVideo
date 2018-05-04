package com.ahuo.fire.hellovideo.ui.activity

import com.ahuo.fire.hellovideo.R
import com.ahuo.fire.hellovideo.base.BaseMvpActivity
import com.ahuo.fire.hellovideo.contract.IMainContract
import com.ahuo.fire.hellovideo.presenter.MainPresenter
import com.ahuo.tools.util.ToastUtils

/**
 * description :
 * author : LiuHuiJie
 * created on : 2018/5/4
 */
class MainActivity : BaseMvpActivity<IMainContract.IMainViewI, MainPresenter>(), IMainContract.IMainViewI {

    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter() {
        mPresenter = MainPresenter()
    }

    override fun initData() {
        mPresenter!!.getData()

    }

    override fun showErrorMessage() {

    }

    override fun getDataSuccess() {
        ToastUtils.showToast("hello")

    }
}
