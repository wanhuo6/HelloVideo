package com.ahuo.fire.hellovideo.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * description :
 * author : LiuHuiJie
 * created on : 2018/5/4
 */
open class BaseMvpPresenter<T : IBaseMvpView> : IBaseMvpPresenter<T> {

    protected var mView: T? = null

    protected var mCompositeDisposable: CompositeDisposable? = null


    override fun attachView(mvpView: T) {
        mView = mvpView
    }

    override fun detachView() {
        removeAllDisposable()
        this.mView = null
    }

    override fun removeAllDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
        }
    }

    override fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(disposable)
    }
}
