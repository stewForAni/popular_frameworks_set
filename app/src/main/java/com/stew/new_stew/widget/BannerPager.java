package com.stew.new_stew.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * describe: BannerPager
 * date： 2018/12/29 on 11:24
 * author: stew (https://github.com/stewForAni)
 */
public class BannerPager extends ViewPager {

    private static final String TAG = BannerPager.class.getSimpleName();
    private Context context;
    private List<String> imageViewUrlList;
    private boolean isAutoScroll = false;
    private BannerHandler handler;

    public BannerPager(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public void isAutoScroll(boolean isAutoScroll) {
        this.isAutoScroll = isAutoScroll;
    }

    public void setImageviewUrlList(List<String> imageViewUrlList) {
        this.imageViewUrlList = imageViewUrlList;
    }

    public void initBanner() {
        ViewPagerScroller scroller = new ViewPagerScroller(context);
        scroller.init(this);
        this.addOnPageChangeListener(new BannerPagerChangeListener());
//        handler = new BannerHandler(context);
    }

    public void runBanner() {

    }


    public class BannerPagerChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    }

    public static class BannerHandler extends Handler {
        private WeakReference<Activity> reference;

        public BannerHandler(Activity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (reference.get() != null) {
                Log.d(TAG, "handleMessage: " + msg.what);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
