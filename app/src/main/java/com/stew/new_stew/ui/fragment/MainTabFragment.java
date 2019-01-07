package com.stew.new_stew.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.stew.new_stew.R;
import com.stew.new_stew.base.fragment.RootFragment;
import com.stew.new_stew.widget.Banner.BannerPager;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.banner_pager)
    BannerPager bannerPager;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View rootView) {
        Log.d(TAG, "initView: ");
        List<String> imageUrlsList = new ArrayList<>();
        getBannerData(imageUrlsList);
        bannerPager.initBanner(getActivity(), imageUrlsList, true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void getBannerData(List<String> imageUrlsList) {
        imageUrlsList.add("https://b.zol-img.com.cn/desk/bizhi/image/1/960x600/1546509403790.jpg");
        imageUrlsList.add("https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/0F/00/ChMkJ1wt5DKIBeJWAAj2O1BxI5QAAuKIwKGDHQACPZT867.jpg");
        imageUrlsList.add("https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/0F/00/ChMkJ1wt5CqIdyJxAAGQDerQKB4AAuKIwH72fAAAZAl347.jpg");
    }
}
