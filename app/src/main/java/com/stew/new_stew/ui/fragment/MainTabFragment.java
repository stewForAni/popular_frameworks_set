package com.stew.new_stew.ui.fragment;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stew.new_stew.R;
import com.stew.new_stew.base.fragment.RootFragment;
import com.stew.new_stew.widget.BannerPager;
import com.stew.new_stew.widget.widgetAdapter.BannerPagerAdapter;

import java.lang.ref.WeakReference;
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
    @BindView(R.id.test_image)
    ImageView testImage;

    private List<String> imageUrlsList;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View rootView) {

        if (getActivity() == null) {
            return;
        }

        imageUrlsList = new ArrayList<>();
        imageUrlsList.add("https://b-ssl.duitang.com/uploads/item/201406/27/20140627102052_KB3eJ.jpeg");
        imageUrlsList.add("http://n.sinaimg.cn/sinacn19/400/w1280h720/20180706/c453-hexfcvk7554456.jpg");
        imageUrlsList.add("http://i0.hdslb.com/bfs/archive/b56b1547496427408699f8610178e6fb73dc4622.jpg");
        imageUrlsList.add("http://i0.hdslb.com/bfs/article/62599c740deb5e0544772c402f984011b3a1e957.jpg");

        bannerPager = new BannerPager(getActivity(), imageUrlsList);
        bannerPager.initBanner();

        if (imageUrlsList.size() > 1) {
            runBanner(true);
        }

        Glide.with(getActivity()).load(imageUrlsList.get(0)).into(testImage);
    }

    private void runBanner(boolean isAutoScroll) {
        if (isAutoScroll) {
            bannerPager.setCurrentItem(bannerPager.getCurrentItem() + 1);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
