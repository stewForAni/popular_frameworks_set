package com.stew.new_stew.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stew.new_stew.R;
import com.stew.new_stew.base.activity.BaseActivity;
import com.stew.new_stew.contract.LoginContract;
import com.stew.new_stew.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * describe:
 * date： 2018/12/3 on 16:06
 * author: stew (https://github.com/stewForAni)
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    private static final String TAG = LoginActivity.class.getName();

    @BindView(R.id.login_btn_with_wx)
    RelativeLayout loginBtnWithWx;
    @BindView(R.id.login_btn_with_qq)
    RelativeLayout loginBtnWithQq;
    @BindView(R.id.login_btn_with_email)
    TextView loginBtnWithEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.whiteStatusTheme);
        super.onCreate(savedInstanceState);
        Log.d(TAG, TAG + "onCreate");
        setStatusBarTransparentWithLightMode();
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
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initMain() {

    }

    @Override
    public void loginSuccess() {

    }


    /**
     * bug tip for enter Activity with FLAG_ACTIVITY_NEW_TASK flag:
     * If startActivity() and finish() both do overridePendingTransition(),
     * overridePendingTransition() will not work.
     * So delete the overridePendingTransition() for finish().
     */

    @OnClick({R.id.login_btn_with_wx, R.id.login_btn_with_qq, R.id.login_btn_with_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn_with_wx:
                break;
            case R.id.login_btn_with_qq:
                break;
            case R.id.login_btn_with_email:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finishWithNoAnimation();
                break;
        }
    }

}
