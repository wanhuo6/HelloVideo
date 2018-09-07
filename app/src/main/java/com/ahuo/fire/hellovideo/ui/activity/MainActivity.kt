package com.ahuo.fire.hellovideo.ui.activity

import android.view.MotionEvent
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import com.ahuo.fire.hellovideo.R
import com.ahuo.fire.hellovideo.base.BaseMvpActivity
import com.ahuo.fire.hellovideo.contract.IMainContract
import com.ahuo.fire.hellovideo.presenter.MainPresenter
import com.ahuo.tools.util.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.button
import android.animation.Animator
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import com.ahuo.fire.hellovideo.ui.load.Flame
import com.ahuo.tools.util.MLog


/**
 * description :
 * author : LiuHuiJie
 * created on : 2018/5/4
 */
class MainActivity : BaseMvpActivity<IMainContract.IMainViewI, MainPresenter>(), IMainContract.IMainViewI {
    var smallAnimation: Animation? = null
    var animator: Animator? = null

    override fun loginFail(message: String) {
        ToastUtils.showToast(message)
    }

    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter() {
        mPresenter = MainPresenter()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initData() {
        Flame.with(this).setTip("你好").crate()
        rl_parent.background.mutate().alpha = 0
        smallAnimation = AnimationUtils.loadAnimation(this, R.anim.small)

        //tv_start.setOnClickListener { tv_start.startAnimation(smallAnimation) }

        tv_start!!.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    tv_start.scaleX = 0.96f
                    tv_start.scaleY = 0.96f
                }

                MotionEvent.ACTION_UP -> {
                    tv_start.scaleX = 1f
                    tv_start.scaleY = 1f
                }
            }
            false
        }
        tv_start.setOnClickListener {
            ToastUtils.showToast("开始跑步")
            var a: Int = 0
            a = tv_start.left;
            animator = ViewAnimationUtils.createCircularReveal(rl_parent, (tv_start.left + tv_start.right) / 2, (tv_start.top + tv_start.bottom) / 2, tv_start.width.toFloat(), 2111f)
            animator!!.duration = 700
            animator!!.start()
            rl_parent.background.mutate().alpha = 255
            Handler().postDelayed({
                animator = ViewAnimationUtils.createCircularReveal(rl_parent, (tv_start.left + tv_start.right) / 2, (tv_start.top + tv_start.bottom) / 2, 2111f, tv_start.width.toFloat())
                animator!!.duration = 700
                animator!!.start()
                rl_parent.background.mutate().alpha = 0
                startActivity(Intent(this, TestActivity::class.java))
            }, 1000)


        }

        mPresenter!!.getData()

    }

    override fun showErrorMessage() {

    }

    override fun getDataSuccess() {

        ToastUtils.showToast("hello")

    }
}
