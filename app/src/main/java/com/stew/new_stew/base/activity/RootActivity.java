package com.stew.new_stew.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.stew.new_stew.R;
import com.stew.new_stew.utils.DeviceUtil;
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
    private boolean hadJudge = false;
    private boolean smoothScrollJudge = true;
    private float downX = 0f;
    private float lastX = 0;
    private View shadowView = null;
    private View rootView = null;
    private VelocityTracker velocityTracker = null;
    private int maxFlingVelocity = 0;

    protected RootActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, TAG + "onCreate");
        setStatusBar();
        setContentView(getLayoutID());

        //bind butterKnife at RootActivity onCreate()
        //and unbind at onDestroy()

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

    public void initToolbar(String title) {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    protected abstract void initPresenter();

    protected abstract void initMain();

    protected abstract int getLayoutID();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        //intercept activity dispatch touch event
        //for slide the whole decor-view (activity)
        if (shouldIntercept) {
            return onTouchEvent(ev);
        }

        doDispatchDetailJudge(ev);

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
    public boolean onTouchEvent(MotionEvent event) {
        doTouchDetailJudge(event);
        return super.onTouchEvent(event);
    }

    private void doDispatchDetailJudge(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN: {
                Log.d(TAG, "dispatchTouchEvent---ACTION_DOWN : " + ev.getRawX());

                //get X position to screen {getX(), get X to ti's view}
                downX = ev.getRawX();
                hadJudge = false;
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                Log.d(TAG, "dispatchTouchEvent---ACTION_MOVE : " + ev.getRawX());

                if (hadJudge) {
                    Log.d(TAG, "hadJudge");
                    break;
                }

                //no slide at X direction
                if (ev.getRawX() == downX) {
                    Log.d(TAG, "no slide at X direction");
                    break;
                }

                //slide from right
                if (ev.getRawX() < downX) {
                    Log.d(TAG, "slide from right");
                    hadJudge = true;
                    break;
                }

                //out of range
                if (ev.getRawX() - downX >= 100) {
                    Log.d(TAG, "out of range");
                    hadJudge = true;
                    break;
                }

                //X direction slide from left range:0px~100px
                if ((ev.getRawX() > downX) && (downX < 50)) {
                    Log.d(TAG, "X direction slide from left range:0px~100px");
                    shouldIntercept = true;
                }
                break;
            }

            case MotionEvent.ACTION_UP: {
                Log.d(TAG, "dispatchTouchEvent---ACTION_UP : " + ev.getRawX());

                downX = 0;
                shouldIntercept = false;
                hadJudge = false;
                break;
            }
        }
    }


    private void doTouchDetailJudge(MotionEvent event) {

        initShadow();

        //get finger slide speed
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
            maxFlingVelocity = ViewConfiguration.get(this).getScaledMaximumFlingVelocity();
        }
        velocityTracker.addMovement(event);

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: {
                Log.d(TAG, "onTouchEvent---ACTION_DOWN : " + event.getRawX());

//                lastX = event.getRawX();
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                Log.d(TAG, "onTouchEvent---ACTION_MOVE : " + event.getRawX());

                if (smoothScrollJudge) {
                    smoothScrollJudge = false;
                    lastX = event.getRawX();
                }

                float X = event.getRawX();
                float TX = rootView.getTranslationX();
                rootView.setTranslationX(X - lastX + TX);
                shadowView.setTranslationX(-shadowView.getWidth() + TX);
                lastX = X;

                break;
            }

            case MotionEvent.ACTION_UP: {
                Log.d(TAG, "onTouchEvent---ACTION_UP : " + event.getRawX());

                velocityTracker.computeCurrentVelocity(1000, maxFlingVelocity);

                //get slide speed when finger up
                float velocityX = velocityTracker.getXVelocity();
                if (velocityTracker != null) {
                    velocityTracker.recycle();
                    velocityTracker = null;
                }

                //judge if back
                if (downX < 50 && velocityX > 1000) {
                    //finger down at screen left 0~50px and speed > 1000
                    onActivityFinish();
                } else if (velocityX > 3600) {
                    //speed > 3600
                    onActivityFinish();
                } else if (rootView.getTranslationX() > (DeviceUtil.getScreenWidth() * 0.3)) {
                    //slide distance > 30% of screen
                    onActivityFinish();
                } else {
                    //root view back
                    rootView.animate().translationX(0).setDuration(200).start();
                    //shadow view back
                    shadowView.animate().translationX(-shadowView.getWidth()).setDuration(200).start();
                }

                //reset
                lastX = 0;
                shouldIntercept = false;
                hadJudge = false;
                smoothScrollJudge = true;
                downX = 0;
                break;
            }
        }
    }


    /**
     * add shadow view to decor view at index = 0
     */
    private void initShadow() {
        if (shadowView == null) {
            shadowView = new View(this);

            ViewGroup viewGroup = (ViewGroup) (getWindow().getDecorView());

            viewGroup.addView(shadowView, 0);
            ViewGroup.LayoutParams params = shadowView.getLayoutParams();
            params.width = (int) (DeviceUtil.getScreenWidth() * 0.05);
            params.height = DeviceUtil.getScreenHeight();

            shadowView.setLayoutParams(params);
            shadowView.setBackgroundResource(R.drawable.root_activity_left_shadow);
            shadowView.setTranslationX(-params.width);
            rootView = viewGroup.getChildAt(1);
        }
    }


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

    public abstract void onActivityFinish();
}
