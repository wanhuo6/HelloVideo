package com.ahuo.fire.hellovideo.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.Button

class TestActivity : AppCompatActivity() {

    private val mButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        mButton!!.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    mButton.scaleX = 0.95.toFloat()
                    mButton.scaleY = 0.95.toFloat()
                }
                MotionEvent.ACTION_UP -> {
                    mButton.scaleX = 1f
                    mButton.scaleY = 1f
                }
            }


            false
        }
    }

}
