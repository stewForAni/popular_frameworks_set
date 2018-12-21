package com.stew.new_stew.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.stew.new_stew.R;
import com.stew.new_stew.base.activity.BaseActivity;

import butterknife.BindView;


/**
 * describe: MainActivity
 * date： 2018/11/28 on 13:24
 * author: stew (https://github.com/stewForAni)
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, TAG + "onCreate");
        setSupportActionBar(toolbar);
        initToolbar("Main");
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

    @Override
    protected void initMain() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void onActivityFinish() {
        finish();
    }
}
