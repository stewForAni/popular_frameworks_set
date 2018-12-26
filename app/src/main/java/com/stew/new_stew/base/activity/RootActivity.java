package com.stew.new_stew.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.stew.new_stew.R;
import com.stew.new_stew.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * describe:RootActivity
 * date： 2018/11/28 on 17:33
 * author: stew (https://github.com/stewForAni)
 */
public abstract class RootActivity extends AppCompatActivity {

    private static final String TAG = RootActivity.class.getName();
    private Unbinder unbinder;

    /**
     * @param savedInstanceState savedInstanceState
     * bind butterKnife at RootActivity onCreate() and unbind at onDestroy()
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, TAG + "onCreate");
        setContentView(getLayoutID());

        unbinder = ButterKnife.bind(this);
        initPresenter();
        initMain();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
            unbinder = null;
        }
    }


    public void initToolbar(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setStatusBarTransparentWithLightMode() {
        StatusBarUtil.transparencyStatusBar(this);
        StatusBarUtil.StatusBarLightMode(this);
    }

    public void setStatusBarTransparent() {
        StatusBarUtil.transparencyStatusBar(this);
    }

    protected abstract void initPresenter();

    protected abstract void initMain();

    protected abstract int getLayoutID();


    /**
     * override startActivity(),startActivityForResult(),finish()
     * unify overridePendingTransition() func with same slide animation
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.no);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in, R.anim.no);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.no, R.anim.slide_out);
    }

    public void finishWithNoAnimation(){
        super.finish();
    }
}
