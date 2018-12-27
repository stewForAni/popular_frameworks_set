package com.stew.new_stew.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.stew.new_stew.R;
import com.stew.new_stew.base.fragment.RootFragment;

import butterknife.BindView;

/**
 * describe:
 * date： 2018/12/24 on 14:33
 * author: stew (https://github.com/stewForAni)
 */
public class MainTabFragment extends RootFragment {

    private static final String TAG = MainTabFragment.class.getSimpleName();

    @BindView(R.id.main_fragment_editText)
    EditText mainFragmentEditText;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View rootView) {
        mainFragmentEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 212121212121212121");
            }
        });
    }

}
