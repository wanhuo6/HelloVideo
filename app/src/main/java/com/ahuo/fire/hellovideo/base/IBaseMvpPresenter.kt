package com.ahuo.fire.hellovideo.base

import io.reactivex.disposables.Disposable

/**
 * description :
 * author : LiuHuiJie
 * created on : 2018/5/4
 */
interface IBaseMvpPresenter<in T : IBaseMvpView> {

    fun attachView(mvpView: T)

    fun detachView()

    fun removeAllDisposable()

    fun addDisposable(disposable: Disposable)

}
