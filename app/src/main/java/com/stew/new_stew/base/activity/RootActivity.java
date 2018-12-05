package com.stew.new_stew.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, TAG + "onCreate");
        setContentView(getLayoutID());
        /*
         * bind butterKnife at RootActivity onCreate()
         * and unbind at onDestroy()
         */
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

    protected abstract void initPresenter();

    protected abstract void initMain();

    protected abstract int getLayoutID();
}
