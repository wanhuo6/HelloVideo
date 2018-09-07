package com.ahuo.fire.hellovideo.ui.load;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahuo.fire.hellovideo.R;

public class FlameView extends RelativeLayout{

    private ProgressBar progressBar;
    private TextView tvTip;

    public FlameView(Context context) {
        this(context,null,0);
    }

    public FlameView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_flame, this);
        setBackgroundResource(R.color.gray);
        progressBar=findViewById(R.id.progress);
        tvTip=findViewById(R.id.tv_tip);
    }

    public void setProgress(int visibility){
        progressBar.setVisibility(visibility);
    }

    public void setTip(String tip){
        tvTip.setVisibility(VISIBLE);
        tvTip.setText(tip);
    }
}
