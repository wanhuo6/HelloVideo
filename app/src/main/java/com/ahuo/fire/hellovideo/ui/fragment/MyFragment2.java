package com.ahuo.fire.hellovideo.ui.fragment;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ahuo.fire.hellovideo.R;
import com.ahuo.fire.hellovideo.ui.load.Flame;
import com.ahuo.fire.hellovideo.ui.load.FlameView;

import java.util.ArrayList;

public class MyFragment2 extends Fragment {

    public FrameLayout mFrameLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*   viewGroup = container;*/
        View v = inflater.inflate(R.layout.fragment_my2, container, false);
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.addView(v);
        mFrameLayout=frameLayout;
        return mFrameLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    private void init() {

    }
}
