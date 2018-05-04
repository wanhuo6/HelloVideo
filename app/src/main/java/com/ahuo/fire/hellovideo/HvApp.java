package com.ahuo.fire.hellovideo;

import android.app.Application;

import com.ahuo.fire.hellovideo.core.AppConfig;
import com.ahuo.tools.util.MLog;
import com.ahuo.tools.util.PreferencesUtils;
import com.ahuo.tools.util.ToastUtils;

/**
 * description :
 * author : LiuHuiJie
 * created on : 2018/5/4
 */
public class HvApp extends Application{
    public static HvApp mApplication;

    public static HvApp getInstance(){
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
        MLog.init(BuildConfig.KK_LOG, AppConfig.APP_LOG_TAG);
        PreferencesUtils.init(this);
        ToastUtils.init(this);
      /*  if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }*/
    }
}
