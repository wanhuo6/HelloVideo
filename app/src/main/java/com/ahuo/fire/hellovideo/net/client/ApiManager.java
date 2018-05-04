package com.ahuo.fire.hellovideo.net.client;


import com.ahuo.fire.hellovideo.BuildConfig;

/**
 * Created on 17-8-1
 *
 * @author liuhuijie
 */

public class ApiManager {

    public static ApiManager mApiManager;

    public ApiService mApiService;

    private ApiManager(){

    }

    public static ApiManager getInstance() {
        if (mApiManager == null) {
            mApiManager = new ApiManager();
        }
        return mApiManager;
    }

    public ApiService getApiService(){
        if (mApiService==null){
            mApiService= RetrofitManager.build(BuildConfig.API_HOST).create(ApiService.class);
        }
        return mApiService;
    }
}
