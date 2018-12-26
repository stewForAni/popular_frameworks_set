package com.stew.new_stew.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.stew.new_stew.R;
import com.stew.new_stew.base.activity.BaseActivity;
import com.stew.new_stew.ui.adapter.MainPageViewAdapter;
import com.stew.new_stew.ui.fragment.LessonTabFragment;
import com.stew.new_stew.ui.fragment.MainTabFragment;
import com.stew.new_stew.ui.fragment.MeTabFragment;

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

    private List<Fragment> fragmentList;
    private FragmentPagerAdapter adapter;

    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.appTheme);
        super.onCreate(savedInstanceState);
        Log.d(TAG, TAG + "onCreate");
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

        fragmentList = new ArrayList<>();
        fragmentList.add(new MainTabFragment());
        fragmentList.add(new LessonTabFragment());
        fragmentList.add(new MeTabFragment());

        adapter = new MainPageViewAdapter(getSupportFragmentManager(), fragmentList);
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
