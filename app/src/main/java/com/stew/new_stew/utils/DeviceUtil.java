package com.stew.new_stew.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * describe:
 * date： 2018/12/14 on 18:01
 * author: stew (https://github.com/stewForAni)
 */
public class DeviceUtil {

    private static Context mContext;
    private static WindowManager windowManager;

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        mContext = context.getApplicationContext();
        if (windowManager == null) {
            windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        }
    }

    public static int getScreenWidth() {
        if (windowManager == null) {
            return 0;
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight() {
        if (windowManager == null) {
            return 0;
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getStatusBarHeight() {
        int height = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

}
