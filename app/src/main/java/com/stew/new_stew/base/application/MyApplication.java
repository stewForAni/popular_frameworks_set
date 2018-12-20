package com.stew.new_stew.base.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.stew.new_stew.utils.DeviceUtil;


/**
 * describe:
 * date： 2018/12/5 on 14:50
 * author: stew (https://github.com/stewForAni)
 */
public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    private static MyApplication instance;

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        instance = this;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DeviceUtil.init(instance);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d(TAG, "attachBaseContext: ");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory: ");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory: ");
    }
}
