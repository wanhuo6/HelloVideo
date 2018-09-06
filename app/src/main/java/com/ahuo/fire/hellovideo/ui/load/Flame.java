package com.ahuo.fire.hellovideo.ui.load;

import android.app.Activity;

import com.ahuo.tools.util.MLog;

public class Flame {
    private Flame(FlameParam param) {
        initFlame(param);
    }

    private void initFlame(FlameParam param) {
        MLog.e(param.tip + "000");
        param.activity.addContentView(null, null);
    }

    public static Builder with(Activity activity) {

        Builder builder = new Builder(activity);

        return builder;
    }

    public static class Builder {
        private FlameParam param;

        public Builder(Activity activity) {
            param = new FlameParam(activity);
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

    public void remove(){

    }

}
