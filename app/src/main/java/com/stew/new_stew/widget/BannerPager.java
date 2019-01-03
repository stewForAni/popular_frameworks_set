package com.stew.new_stew.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

import com.stew.new_stew.widget.widgetAdapter.BannerPagerAdapter;

import java.util.ArrayList;
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

    public BannerPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerPager(@NonNull Context context, List<String> imageViewUrlList) {
        super(context);
        this.context = context;
        this.imageViewUrlList = imageViewUrlList;
        dealUrls();
    }

    private void dealUrls() {
        int length = imageViewUrlList.size();
        if (length <= 1) {
            return;
        }

        List<String> newList = new ArrayList<>();
        newList.add(imageViewUrlList.get(length - 1));
        for (int i = 0; i < length; i++) {
            newList.add(imageViewUrlList.get(i));
        }
        newList.add(imageViewUrlList.get(0));
        imageViewUrlList = newList;
    }

    public void initBanner() {
        ViewPagerScroller scroller = new ViewPagerScroller(context);
        scroller.init(this);
        addOnPageChangeListener(new BannerPagerChangeListener());
        setAdapter(new BannerPagerAdapter(context, imageViewUrlList));
    }

    private class BannerPagerChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrolled(int i, float v, int i1) {}
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
        public void onPageScrollStateChanged(int i) {}
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
