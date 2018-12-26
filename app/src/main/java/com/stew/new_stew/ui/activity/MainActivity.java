package com.stew.new_stew.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.stew.new_stew.R;
import com.stew.new_stew.base.activity.BaseActivity;
import com.stew.new_stew.ui.adapter.MainPageViewAdapter;
import com.stew.new_stew.ui.fragment.LessonTabFragment;
import com.stew.new_stew.ui.fragment.MainTabFragment;
import com.stew.new_stew.ui.fragment.MeTabFragment;
import com.stew.new_stew.utils.DeviceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * describe: MainActivity
 * date： 2018/11/28 on 13:24
 * author: stew (https://github.com/stewForAni)
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getName();

    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;

    @BindView(R.id.top_bar_rl)
    RelativeLayout topBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.appTheme);
        super.onCreate(savedInstanceState);
        Log.d(TAG, TAG + "onCreate" + DeviceUtil.getStatusBarHeight());
        setStatusBarTransparent();

    }

    @Override
    protected int getLayoutID() {
        Log.d(TAG, "getLayoutID: ");
        return R.layout.activity_main;
    }


    @Override
    protected void initMain() {
        Log.d(TAG, "initMain: ");

        initTopBarLayout();

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MainTabFragment());
        fragmentList.add(new LessonTabFragment());
        fragmentList.add(new MeTabFragment());

        FragmentPagerAdapter adapter = new MainPageViewAdapter(getSupportFragmentManager(), fragmentList);
        mainViewpager.setAdapter(adapter);

        mainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void initTopBarLayout() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        int topBarLayoutHeight = DeviceUtil.getStatusBarHeight() + DeviceUtil.dip2px(this, 46);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,topBarLayoutHeight);
        relativeLayout.setLayoutParams(params);
        relativeLayout.setBackgroundResource(R.drawable.top_layout_gradient);
        topBarLayout.addView(relativeLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, TAG + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, TAG + "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, TAG + "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, TAG + "onDestroy");
    }

}
