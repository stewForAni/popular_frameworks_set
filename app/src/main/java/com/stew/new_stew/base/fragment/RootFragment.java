package com.stew.new_stew.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * describe: aim to lazy init fragment
 * date： 2018/12/24 on 14:23
 * author: stew (https://github.com/stewForAni)
 */
public abstract class RootFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private View rootView;
    private Unbinder unbinder;

    /**
     * current Fragment view is created
     */
    private boolean isViewCreated = false;

    /**
     * return the layout of fragment
     *
     * @return the id of fragment layout
     */
    public abstract int getLayoutID();

    /**
     * init the views of fragment layout
     */
    public abstract void initView(View rootView);


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser);
        if (isViewCreated) {
            if (isVisibleToUser) {
                dealFragment(true);
            } else {
                dealFragment(false);
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutID(), container, false);
            unbinder = ButterKnife.bind(this, rootView);
            initView(rootView);
        }
        return rootView;
    }

    /**
     * onViewCreated will do soon after onCreateView()
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    public void dealFragment(boolean isVisible) {
        if (isVisible) {
            //fragment visible
            Log.d(TAG, "dealFragment: Visible");

        } else {
            //fragment invisible
            Log.d(TAG, "dealFragment: isnVisible");

        }
    }

}
