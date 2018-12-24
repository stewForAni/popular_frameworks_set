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

    private static final String TAG = RootFragment.class.getName();
    private View rootView;
    private Unbinder unbinder;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: ");
    }


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
    }


}