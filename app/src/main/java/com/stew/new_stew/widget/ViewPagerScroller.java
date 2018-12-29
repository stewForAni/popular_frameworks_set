package com.stew.new_stew.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * describe:
 * date： 2018/12/29 on 13:39
 * author: stew (https://github.com/stewForAni)
 */
public class ViewPagerScroller extends Scroller {

    private int mDuration = 1000;

    public ViewPagerScroller(Context context) {
        super(context);
    }

    public void setScrollDuration(int duration) {
        this.mDuration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    /**
     * @param viewPager reflect to get the var "mScroller"
     */
    public void init(ViewPager viewPager) {
        try {
            Field scroller = ViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(viewPager, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
