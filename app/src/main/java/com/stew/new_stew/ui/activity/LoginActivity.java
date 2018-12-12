package com.stew.new_stew.ui.activity;

import android.content.Intent;
import android.view.MotionEvent;
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

    @BindView(R.id.login_btn_with_wx)
    RelativeLayout loginBtnWithWx;
    @BindView(R.id.login_btn_with_qq)
    RelativeLayout loginBtnWithQq;
    @BindView(R.id.login_btn_with_email)
    TextView loginBtnWithEmail;

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


    @OnClick({R.id.login_btn_with_wx, R.id.login_btn_with_qq, R.id.login_btn_with_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn_with_wx:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.login_btn_with_qq:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.login_btn_with_email:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

}
