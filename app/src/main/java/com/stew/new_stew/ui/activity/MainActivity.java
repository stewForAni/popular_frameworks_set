package com.stew.new_stew.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.stew.new_stew.R;
import com.stew.new_stew.base.activity.BaseActivity;

import butterknife.BindView;


/**
 * describe: MainActivity
 * date： 2018/11/28 on 13:24
 * author: stew (https://github.com/stewForAni)
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        initToolbar("Main");
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
