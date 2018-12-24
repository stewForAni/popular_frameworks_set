package com.stew.new_stew.ui.fragment;


import android.view.View;
import android.widget.Button;

import com.stew.new_stew.R;
import com.stew.new_stew.base.fragment.RootFragment;

import butterknife.BindView;

/**
 * describe:
 * date： 2018/12/24 on 14:33
 * author: stew (https://github.com/stewForAni)
 */
public class MainTabFragment extends RootFragment {
    @BindView(R.id.button)
    Button button;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View rootView) {

    }

}
