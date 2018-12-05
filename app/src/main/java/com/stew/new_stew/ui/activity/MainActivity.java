package com.stew.new_stew.ui.activity;
import android.os.Bundle;
import com.stew.new_stew.R;
import com.stew.new_stew.base.activity.BaseActivity;

/**
 * describe: MainActivity
 * date： 2018/11/28 on 13:24
 * author: stew (https://github.com/stewForAni)
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initMain() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }
}
