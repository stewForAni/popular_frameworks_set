package com.stew.new_stew.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

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
    private float downX = 0f;
    private float downY = 0f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        Log.d(TAG, TAG + "onCreate");
        setStatusBar();
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

    private void setStatusBar() {
        StatusBarUtil.transparencyStatusBar(this);
        StatusBarUtil.StatusBarLightMode(this);
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
        overridePendingTransition(R.anim.slide_in, 0);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in, 0);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.slide_out);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                //get X position to screen {getX(), get X to ti's view}
                downX = ev.getRawX();
                downY = ev.getRawY();
                break;
            }

            case MotionEvent.ACTION_MOVE: {

                //x轴方向没有滑动
                if (ev.getRawX() == downX) {
                    break;
                }

                //左滑
                if (ev.getRawX() < downX) {
                    break;
                }

                break;
            }


            case MotionEvent.ACTION_UP: {
                break;
            }
        }
        return true;
    }
}
