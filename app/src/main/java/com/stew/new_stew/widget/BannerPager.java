package com.stew.new_stew.widget;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.stew.new_stew.widget.widgetAdapter.BannerPagerAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * describe: BannerPager
 * date： 2018/12/29 on 11:24
 * author: stew (https://github.com/stewForAni)
 */
public class BannerPager extends ViewPager {

    private static final String TAG = BannerPager.class.getSimpleName();
    private Activity context;
    private List<String> imageViewUrlList;
    private boolean isAutoScroll = false;
    private BannerHandler handler;

    public BannerPager(@NonNull Activity context) {
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
        this.setAdapter(new BannerPagerAdapter(context, imageViewUrlList));
        if (handler == null && context != null && imageViewUrlList.size() > 1) {
            handler = new BannerHandler(context);
        }
    }

    private class BannerPagerChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            Log.d(TAG, "onPageSelected: " + i);
            if (imageViewUrlList.size() > 1) {
                if (i < 1) {
                    BannerPager.this.setCurrentItem(imageViewUrlList.size() - 2);
                } else if (i > imageViewUrlList.size() - 2) {
                    BannerPager.this.setCurrentItem(1);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    }

    private class BannerHandler extends Handler {
        private final WeakReference<Activity> reference;

        public BannerHandler(Activity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (reference.get() != null) {
                Log.d(TAG, "handleMessage: -------------" + msg.what);
                runBanner();
            }
        }
    }

    private void runBanner() {
        if (handler != null) {
            this.setCurrentItem(this.getCurrentItem() + 1);
            handler.sendEmptyMessageDelayed(666, 1000);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.onDetachedFromWindow();
    }
}
