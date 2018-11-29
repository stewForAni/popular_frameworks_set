package com.stew.new_stew.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.stew.new_stew.base.presenter.AbstractPresenter;
import com.stew.new_stew.base.view.BaseView;

/**
 * describe: BaseActivity
 * date： 2018/11/28 on 13:24
 * author: stew (https://github.com/stewForAni)
 */

public abstract class BaseActivity<T extends AbstractPresenter> extends RootActivity implements BaseView {

    private static final String TAG = BaseActivity.class.getName();

    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, TAG + "onCreate");
    }

    @Override
    protected void initPresenter() {
        Log.d(TAG, "initPresenter");
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {

        if(presenter!=null){
            presenter.detachView();
            presenter = null;
        }

        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showSnackBar(String message) {

    }
}
