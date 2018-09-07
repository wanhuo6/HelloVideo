package com.ahuo.fire.hellovideo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahuo.fire.hellovideo.R;

/**
 * Created by user on 2016/1/20.
 */
public class TabView extends RelativeLayout {
    private TextView tv_title;
    private RelativeLayout rl_param;
    private View line_bottom;
    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    public TabView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_bottom_tab, this, true);
        tv_title= findViewById(R.id.title);
        rl_param=  findViewById(R.id.rl_param);
        line_bottom=findViewById(R.id.line_bottom);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }
    public void setTitle(String title){
     tv_title.setText(title);
    }
    public void setTextColor(int color){
        tv_title.setTextColor(color);
    }
    public void setLayoutParam(LayoutParams params){
        rl_param.setLayoutParams(params);
    }
    public void setBottomLineHeight(int px){
        LayoutParams params = (LayoutParams) line_bottom.getLayoutParams();
        params.height = px;
        params.width = LayoutParams.MATCH_PARENT;
        line_bottom.setLayoutParams(params);
    }
}
