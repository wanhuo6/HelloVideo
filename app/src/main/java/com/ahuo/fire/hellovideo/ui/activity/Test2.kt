package com.ahuo.fire.hellovideo.ui.activity

import android.animation.Animator
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class Test2 : AppCompatActivity() {

    private val animator: Animator? = null
    private val tvTest: View? = null
    private val a: Int? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        animator!!.duration = 300
        animator.start()
        tvTest!!.background.mutate().alpha = 0

    }
}
