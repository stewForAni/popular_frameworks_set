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


    //slide in or out variable
    private boolean shouldIntercept = false;
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

        if (shouldIntercept) {
            return onTouchEvent(ev);
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                Log.d(TAG, "dispatchTouchEvent---ACTION_DOWN");
                //get X position to screen {getX(), get X to ti's view}
                downX = ev.getRawX();
                downY = ev.getRawY();
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                Log.d(TAG, "dispatchTouchEvent---ACTION_MOVE");
                //x轴方向没有滑动
                if (ev.getRawX() == downX) {
                    break;
                }

                //左滑
                if (ev.getRawX() < downX) {
                    break;
                }

                //超过范围
                if (ev.getRawX() - downX >= 100) {
                    break;
                }

                //X轴方向 右滑 50~100px
                if (ev.getRawX() - downX > 50) {
                    float rate = (ev.getRawX() - downX) / (Math.abs(ev.getRawY() - downY));
                    if ((downX < 50 && rate > 0.5f) || rate > 2) {
                        shouldIntercept = true;
                    }
                }

                break;
            }


            case MotionEvent.ACTION_UP: {
                Log.d(TAG, "dispatchTouchEvent---ACTION_UP");
                downX = 0;
                downY = 0;
                shouldIntercept = false;
                break;
            }
        }


        //Activity default dispatch
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            onUserInteraction();
        }

        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }

        return true;
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                Log.d(TAG, "onTouchEvent---ACTION_DOWN");
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                Log.d(TAG, "onTouchEvent---ACTION_MOVE");
                break;
            }
            case MotionEvent.ACTION_UP: {
                Log.d(TAG, "onTouchEvent---ACTION_UP");
                break;
            }
        }
        return super.onTouchEvent(event);
    }
}
