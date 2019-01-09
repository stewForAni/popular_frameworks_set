package com.stew.new_stew.widget.Banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.stew.new_stew.utils.CommonVarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * describe: BannerPager
 * date： 2018/12/29 on 11:24
 * author: stew (https://github.com/stewForAni)
 */
public class BannerPager extends ViewPager {

    private static final String TAG = BannerPager.class.getSimpleName();
    private List<String> imageUrlList;
    private int imageListLength;
    private int currentPosition;
    private WeakHandler handler;
    private boolean isAutoPlay;

    public BannerPager(@NonNull Context context) {
        this(context, null);
    }

    public BannerPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void initBanner(Context context, List<String> imageUrlList, boolean isAutoScroll) {
        Log.d(TAG, "initBanner: ");
        handler = new WeakHandler();
        isAutoPlay = isAutoScroll;
        dealUrls(imageUrlList);

        ViewPagerScroller scroller = new ViewPagerScroller(context);
        scroller.init(this);

        addOnPageChangeListener(new BannerPagerChangeListener());
        this.setAdapter(new BannerPagerAdapter(context, this.imageUrlList));
        if (this.imageUrlList.size() > 1 && isAutoPlay) {
            setCurrentItem(1, false);
            startAutoPlay();
        }
    }

    private void startAutoPlay() {
//        handler.removeCallbacks(runnable);
//        handler.postDelayed(runnable, CommonVarUtil.BANNER_DELAY_TIME);
    }

    private void stopAutoPlay() {
        handler.removeCallbacks(runnable);
    }

    private class BannerPagerChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            Log.d(TAG, "onPageSelected: " + i);
            currentPosition = i;
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            Log.d(TAG, "onPageScrollStateChanged: " + i);
            switch (i) {
                //no operation:0
                case CommonVarUtil.SCROLL_STATE_IDLE: {
                    if (imageListLength > 1) {
                        if (currentPosition == 0) {
                            setCurrentItem(imageListLength - 2, false);
                        } else if (currentPosition == imageListLength - 1) {
                            setCurrentItem(1, false);
                        }
                    }
                    break;
                }
                //click and drag:1
                case CommonVarUtil.SCROLL_STATE_DRAGGING: {
                    break;
                }
                //finger up:2
                case CommonVarUtil.SCROLL_STATE_SETTLING: {
                    break;
                }
            }
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (imageListLength > 1 && isAutoPlay) {
                currentPosition = currentPosition % (imageListLength - 1) + 1;
                Log.d(TAG, "auto run: " + currentPosition);
                setCurrentItem(currentPosition, true);
                handler.postDelayed(runnable, CommonVarUtil.BANNER_DELAY_TIME);
            }
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isAutoPlay) {
            int action = ev.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                stopAutoPlay();
            } else if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_OUTSIDE) {
                startAutoPlay();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private void dealUrls(List<String> imageUrlList) {
        int length = imageUrlList.size();
        if (length <= 1) {
            return;
        }

        List<String> newList = new ArrayList<>();
        newList.add(imageUrlList.get(length - 1));
        for (int i = 0; i < length; i++) {
            newList.add(imageUrlList.get(i));
        }

        newList.add(imageUrlList.get(0));
        this.imageUrlList = newList;
        imageListLength = newList.size();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
