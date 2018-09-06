package com.ahuo.fire.hellovideo.ui.load;

import android.app.Activity;

public class FlameParam {
    public final Activity activity;
    public int type;
    public String tip;
    public String confirm;
    public String cancel;

    public FlameParam(Activity act) {
        activity=act;
    }
}
