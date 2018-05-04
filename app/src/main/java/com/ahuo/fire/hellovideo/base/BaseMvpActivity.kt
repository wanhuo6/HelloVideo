package com.ahuo.fire.hellovideo.base

import android.os.Bundle

/**
 * description :
 * author : LiuHuiJie
 * created on : 2018/5/4
 */
abstract class BaseMvpActivity<in V : IBaseMvpView, P : IBaseMvpPresenter<V>> : BaseActivity(), IBaseMvpView {

    protected var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initLayout())
        initPresenter()
        if (mPresenter != null) {
            mPresenter!!.attachView(this as V)
        }
        initData()

    }

    protected abstract fun initLayout(): Int

    protected abstract fun initPresenter()

    protected abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
    }
}
