package com.ahuo.fire.hellovideo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ahuo.fire.hellovideo.R;
import com.ahuo.fire.hellovideo.ui.fragment.MyFragment;
import com.ahuo.fire.hellovideo.ui.fragment.MyFragment2;
import com.ahuo.fire.hellovideo.ui.fragment.MyFragmentPagerAdapter;
import com.ahuo.fire.hellovideo.widget.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private List<String> mTitleList;
    private List<Fragment> mFragmentList;
    private MyFragmentPagerAdapter mFragmentPagerAdapter;

    private ViewPager mViewPager;
    private ViewPagerIndicator mViewPagerIndicator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }

    private void init() {
        mViewPager=findViewById(R.id.viewpager);
        mViewPagerIndicator=findViewById(R.id.viewPagerIndicator);
        MyFragment mFragment = new MyFragment();
        MyFragment2 mFragment2=new MyFragment2();
        mFragmentList = new ArrayList<>();
        mFragmentList.add(mFragment);
        mFragmentList.add(mFragment2);
        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mTitleList=new ArrayList<>();
        mTitleList.add("fragment1");
        mTitleList.add("fragment2");
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPagerIndicator.setViewPager(mViewPager, 0);
        mViewPagerIndicator.setTabItemTitles(mTitleList);
        mViewPagerIndicator.setVisibleTabCount(mFragmentList.size());
        mViewPager.setCurrentItem(0);

    }
}
