package com.stew.new_stew.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stew.new_stew.R;
import com.stew.new_stew.base.activity.BaseActivity;
import com.stew.new_stew.ui.adapter.MainPageViewAdapter;
import com.stew.new_stew.ui.fragment.LessonTabFragment;
import com.stew.new_stew.ui.fragment.MainTabFragment;
import com.stew.new_stew.ui.fragment.MeTabFragment;
import com.stew.new_stew.utils.CommonVarUtil;
import com.stew.new_stew.utils.DeviceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * describe: MainActivity
 * date： 2018/11/28 on 13:24
 * author: stew (https://github.com/stewForAni)
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private int clickTabID;

    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;
    @BindView(R.id.top_bar_ll)
    LinearLayout topBarLayout;
    @BindView(R.id.main_tab_ll_home)
    LinearLayout mainTabLlHome;
    @BindView(R.id.main_tab_ll_lesson)
    LinearLayout mainTabLlLesson;
    @BindView(R.id.main_tab_ll_me)
    LinearLayout mainTabLlMe;
    @BindView(R.id.main_tab_iv_home)
    ImageView mainTabIvHome;
    @BindView(R.id.main_tab_iv_lesson)
    ImageView mainTabIvLesson;
    @BindView(R.id.main_tab_iv_me)
    ImageView mainTabIvMe;
    @BindView(R.id.main_tab_tx_home)
    TextView mainTabTxHome;
    @BindView(R.id.main_tab_tx_lesson)
    TextView mainTabTxLesson;
    @BindView(R.id.main_tab_tx_me)
    TextView mainTabTxMe;


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
        initBottomBarLayout();

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
                Log.d(TAG, "onPageSelected: " + i);
                switch (i) {
                    case 0: {
                        onClick(mainTabLlHome);
                        break;
                    }
                    case 1: {
                        onClick(mainTabLlLesson);
                        break;
                    }
                    case 2: {
                        onClick(mainTabLlMe);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

    }

    private void initTopBarLayout() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        int topBarLayoutHeight = DeviceUtil.getStatusBarHeight() + DeviceUtil.dip2px(this, CommonVarUtil.TOP_TOOL_BAR_HEIGHT);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, topBarLayoutHeight);
        relativeLayout.setLayoutParams(params);
        relativeLayout.setBackgroundResource(R.drawable.top_layout_gradient);
        topBarLayout.addView(relativeLayout, 0);
    }

    private void initBottomBarLayout() {
        mainTabLlHome.setOnClickListener(this);
        mainTabLlLesson.setOnClickListener(this);
        mainTabLlMe.setOnClickListener(this);
        onClick(mainTabLlHome);
        clickTabID = R.id.main_tab_ll_home;
    }

    @Override
    public void onClick(View v) {

        if (clickTabID == v.getId()) {
            Log.d(TAG, "click repetition");
            return;
        } else {
            clickTabID = v.getId();
        }

        initIcon();
        switch (clickTabID) {

            case R.id.main_tab_ll_home: {
                mainTabIvHome.setImageResource(R.drawable.main_tab_icon_home_selected);
                mainTabTxHome.setTextSize(TypedValue.COMPLEX_UNIT_SP, CommonVarUtil.MAIN_TAB_TEXT_SIZE_SELECTED);
                mainViewpager.setCurrentItem(0);
                break;
            }

            case R.id.main_tab_ll_lesson: {
                mainTabIvLesson.setImageResource(R.drawable.main_tab_icon_lesson_selected);
                mainTabTxLesson.setTextSize(TypedValue.COMPLEX_UNIT_SP, CommonVarUtil.MAIN_TAB_TEXT_SIZE_SELECTED);
                mainViewpager.setCurrentItem(1);
                break;
            }

            case R.id.main_tab_ll_me: {
                mainTabIvMe.setImageResource(R.drawable.main_tab_icon_me_selected);
                mainTabTxMe.setTextSize(TypedValue.COMPLEX_UNIT_SP, CommonVarUtil.MAIN_TAB_TEXT_SIZE_SELECTED);
                mainViewpager.setCurrentItem(2);
                break;
            }
        }
    }

    private void initIcon() {
        mainTabIvHome.setImageResource(R.drawable.main_tab_icon_home);
        mainTabIvLesson.setImageResource(R.drawable.main_tab_icon_lesson);
        mainTabIvMe.setImageResource(R.drawable.main_tab_icon_me);

        mainTabTxHome.setTextSize(TypedValue.COMPLEX_UNIT_SP, CommonVarUtil.MAIN_TAB_TEXT_SIZE);
        mainTabTxLesson.setTextSize(TypedValue.COMPLEX_UNIT_SP, CommonVarUtil.MAIN_TAB_TEXT_SIZE);
        mainTabTxMe.setTextSize(TypedValue.COMPLEX_UNIT_SP, CommonVarUtil.MAIN_TAB_TEXT_SIZE);
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
