package com.ahuo.fire.hellovideo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.ahuo.fire.hellovideo.R;

import java.util.List;


public class ViewPagerIndicator extends LinearLayout {
    private Paint mPaint;

    private Path mPath;

    private int mTriangleWidth = 0;

    private int mTriangleHeight = 0;

    private static final float RADIO_TRIANGLE_WIDTH = 1F;
    /**
     * 三角形底边的最大宽度
     */
    private final int DIMENSION_TRIANGLE_WIDTH_MAX = (int) (getScreenWidth() / 3 * RADIO_TRIANGLE_WIDTH);

    private int mInitTranslationX;

    private int mTranslationX;

    private int mTabVisibleCount;
    private int mTabBottomStyle;

    private static final int COUNT_DEFAULT_TAB = 3;
    private static final int TAB_DEFAULT_TAB_BOTTOM_STYTLE = 0;
    private static final int COLOR_TEXT_NORMAL = 0xFF666666;    //标题未选中颜色
    private static final int COLOR_TEXT_HIGHLIGHT = 0xFFEA5500;    //标题选中颜色
    private static final int COLOR_BACKGROUND = 0XFFFFFFFF;    //标题选中颜色
    private static final int COLOR_INDICATOR = 0XFFEA5500;    //底部指示器颜色
    private static final int COLOR_INDICATOR_BACKGROUND = 0XFFFFFFFF;    //底部指示器背景颜色


    private int mTabBackgroundDefaultColor = COLOR_BACKGROUND;
    private int mTabBackgroundHighlightColor = COLOR_BACKGROUND;
    private int mTabTextDefaultColor = COLOR_TEXT_NORMAL;
    private int mTabTextHighlightColor = COLOR_TEXT_NORMAL;
    private int mIndicatorBackgroundColor = COLOR_INDICATOR_BACKGROUND;
    private int mIndicatorColor = COLOR_INDICATOR;


    private List<String> mTitles;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ViewPagerIndicator);

        // 获取可见Tab的数量
        mTabVisibleCount = a.getInt(
                R.styleable.ViewPagerIndicator_visibleTabCount,
                COUNT_DEFAULT_TAB);
        mTabBottomStyle = a.getInt(R.styleable.ViewPagerIndicator_tabBottomStyle,
                TAB_DEFAULT_TAB_BOTTOM_STYTLE);
        mTabBackgroundDefaultColor = a.getColor(R.styleable.ViewPagerIndicator_tabBackgroundDefaultColor, mTabBackgroundDefaultColor);
        mIndicatorBackgroundColor = a.getColor(R.styleable.ViewPagerIndicator_tabIndicatorBackgroundColor, mIndicatorBackgroundColor);
        mIndicatorColor = a.getColor(R.styleable.ViewPagerIndicator_tabIndicatorDefaultColor, mIndicatorColor);
        mTabBackgroundHighlightColor = a.getColor(R.styleable.ViewPagerIndicator_tabBackgroundHighlightColor, mTabBackgroundHighlightColor);
        mTabTextHighlightColor = a.getColor(R.styleable.ViewPagerIndicator_tabTextHighlightColor, mTabTextHighlightColor);
        mTabTextDefaultColor = a.getColor(R.styleable.ViewPagerIndicator_tabTextDefaultColor, mTabTextDefaultColor);
        if (mTabVisibleCount < 0) {
            mTabVisibleCount = COUNT_DEFAULT_TAB;
        }
        a.recycle();

        // 初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mIndicatorColor);
        mPaint.setStyle(Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(4));

    }

   /* @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //把整张画布绘制成白色
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        canvas.drawRect(10, 150, 70, 190, paint);
    }*/

    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.save();
        canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 2);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
        //设置底部阴影高度=矩形高度
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TabView) {
                if (mTriangleHeight != 0) {
                    ((TabView) view).setBottomLineHeight(mTriangleHeight);
                }
            }
        }
        super.dispatchDraw(canvas);

    }
    //绘制矩形


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTriangleWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGLE_WIDTH);
        mTriangleWidth = Math.min(mTriangleWidth, DIMENSION_TRIANGLE_WIDTH_MAX);
        mInitTranslationX = w / mTabVisibleCount / 2 - mTriangleWidth / 2;

        switch (mTabBottomStyle) {
            case 0:
                initRectangle();
                break;
            case 1:
                initPositiveTriangle();
                break;
            case 2:
                initNagetiveTriangle();
                break;
            case 3:
                initPositiveTriangleWithRect();
                break;
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int cCount = getChildCount();
        if (cCount == 0)
            return;

        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LayoutParams lp = (LayoutParams) view
                    .getLayoutParams();
            lp.weight = 0;
            lp.width = getScreenWidth() / mTabVisibleCount;
            view.setLayoutParams(lp);
        }

        setItemClickEvent();

    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 底部矩形矩形
     */
    private void initRectangle() {
        mTriangleHeight = mTriangleWidth / 12;
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(0, -mTriangleHeight);
        mPath.lineTo(mTriangleWidth, -mTriangleHeight);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.close();

    }


    /**
     * 底部倒三角形
     */
    private void initNagetiveTriangle() {
        mTriangleHeight = mTriangleWidth / 12;
        mPath = new Path();
        mPath.moveTo(mTriangleWidth / 2 + mTriangleHeight, 0);
        mPath.lineTo(mTriangleWidth / 2 - mTriangleHeight, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();
    }

    /**
     * 底部顺三角形△
     */
    private void initPositiveTriangle() {
        mTriangleHeight = mTriangleWidth / 12;
        mPath = new Path();
        mPath.moveTo(mTriangleWidth / 2 + mTriangleHeight, 0);
        mPath.lineTo(mTriangleWidth / 2 - mTriangleHeight, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();
    }


    /**
     * 底部顺三角形带矩形
     */
    private void initPositiveTriangleWithRect() {
        mTriangleHeight = mTriangleWidth / 12;
        mPath = new Path();
        mPath.moveTo(0, -mTriangleHeight / 2);
        mPath.lineTo(mTriangleWidth / 2 - mTriangleHeight / 2, -mTriangleHeight / 2);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.lineTo(mTriangleWidth / 2 + mTriangleHeight / 2, -mTriangleHeight / 2);
        mPath.lineTo(mTriangleWidth, -mTriangleHeight / 2);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(0, 0);
        mPath.close();
    }

    /**
     * 指示器跟随手指进行滚动
     *
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {
        int tabWidth = getWidth() / mTabVisibleCount;
        mTranslationX = (int) (tabWidth * (offset + position));

        // 容器移动，在tab处于移动至最后一个时
        if (position >= (mTabVisibleCount - 2) && offset > 0
                && getChildCount() > mTabVisibleCount) {

            if (mTabVisibleCount != 1) {
//                Log.e("TAG",
//                        ((position - (mTabVisibleCount - 2)) * tabWidth + (int) (tabWidth * offset))
//                                + "");
                this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth
                        + (int) (tabWidth * offset), 0);
            } else {
                this.scrollTo(position * tabWidth + (int) (tabWidth * offset), 0);
            }

        }

        invalidate();

    }

    public void setTabItemTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            this.removeAllViews();
            mTitles = titles;
            for (String title : mTitles) {
                addView(generateTextView(title));
            }

            setItemClickEvent();
        }
    }
    /**
     * 设置可见的Tab数量
     *
     * @param count
     */
    public void setVisibleTabCount(int count) {
        mTabVisibleCount = count;
    }

    /**
     * 根据title创建Tab
     *
     * @param title
     * @return
     */
    private View generateTextView(String title) {
        TabView tv = new TabView(getContext());
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / mTabVisibleCount;
        tv.setTitle(title);
        tv.findViewById(R.id.rl_param).setBackgroundColor(mTabBackgroundDefaultColor);
        tv.setTextColor(mTabTextDefaultColor);
        tv.setLayoutParams(lp);
        tv.findViewById(R.id.line_bottom).setBackgroundResource(mIndicatorBackgroundColor);
        return tv;
    }

    private ViewPager mViewPager;

    public interface PageOnchangeListener {
        void onPageScrolled(int position, float positionOffset,
                            int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }

    public PageOnchangeListener mListener;

    public void setOnPageChangeListener(PageOnchangeListener listener) {
        this.mListener = listener;
    }

    /**
     * 设置关联的ViewPager
     *
     * @param viewPager
     * @param pos
     */
    public void setViewPager(ViewPager viewPager, int pos) {
        mViewPager = viewPager;
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                if (mListener != null) {
                    mListener.onPageSelected(position);
                }

                highLightTextView(position);
                // 极端情况的Bug修复
                if (position <= (mTabVisibleCount - 2))
                    scrollTo(0, 0);

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                scroll(position, positionOffset);
                if (mListener != null) {
                    mListener.onPageScrolled(position, positionOffset,
                            positionOffsetPixels);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (mListener != null) {
                    mListener.onPageScrollStateChanged(state);
                }

            }
        });
        mViewPager.setCurrentItem(pos);
        highLightTextView(pos);
    }

    /**
     * 重置TAB文本颜色
     */
    private void resetTextViewColor() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TabView) {
                ((TabView) view).setTextColor(mTabTextDefaultColor);
//                view.setBackgroundColor(mTabBackgroundDefaultColor);
                view.findViewById(R.id.rl_param).setBackgroundColor(mTabBackgroundDefaultColor);
            }
        }

    }

    /**
     * 高亮某个Tab的文本
     *
     * @param pos
     */
    private void highLightTextView(int pos) {
        resetTextViewColor();
        View view = getChildAt(pos);
        if (view instanceof TabView) {
            ((TabView) view).setTextColor(mTabTextHighlightColor);
//            view.setBackgroundColor(mTabBackgroundHighlightColor);
            view.findViewById(R.id.rl_param).setBackgroundColor(mTabBackgroundHighlightColor);
        }
    }

    /**
     * 设置Tab的点击事件
     */
    private void setItemClickEvent() {
        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {
            final int j = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });

        }

    }

}
