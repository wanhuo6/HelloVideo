package com.ahuo.fire.hellovideo.ui.load;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.ahuo.tools.util.MLog;


public class Flame {

    public static final int TYPE_PROGRESS = 0;
    public static final int TYPE_TIP = 1;

    private static FlameView mFlameView;

    private Flame(FlameParam param) {
        initFlame(param);
    }

    private void initFlame(FlameParam param) {
        MLog.e(param.tip + "qq");
        mFlameView = new FlameView(param.context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                remove(mFlameView);
            }
        }, 3000);
        switch (param.type) {
            case TYPE_PROGRESS:
                mFlameView.setProgress(View.VISIBLE);
                break;
            case TYPE_TIP:
                mFlameView.setTip(param.tip);
                break;
            default:
                break;
        }
        ((Activity)(param.context)).addContentView(mFlameView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public static Builder with(Activity activity) {

        Builder builder = new Builder(activity);

        return builder;
    }

    public static Builder with(Fragment fragment) {

        Builder builder = new Builder(fragment.getContext());

        return builder;
    }

    public static class Builder {
        private FlameParam param;

        public Builder(Context context) {
            param = new FlameParam(context);
        }

        public Builder setType(String type) {
            param.tip = type;
            return this;
        }

        public Builder setTip(String tip) {
            param.tip = tip;
            return this;
        }

        public Builder setConfirm(String confirm) {
            param.confirm = confirm;
            return this;
        }

        public Builder setCancel(String cancel) {
            param.cancel = cancel;
            return this;
        }

        public Flame crate() {
            return new Flame(param);
        }
    }

    public void remove(View view) {
        if (view != null) {
            ViewGroup contentParent = (ViewGroup) view.getParent();
            contentParent.removeView(view);
        }
    }

}
