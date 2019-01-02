package com.stew.new_stew.widget.widgetAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stew.new_stew.R;

import java.util.List;

/**
 * describe: BannerPagerAdapter
 * date： 2019/1/2 on 13:05
 * author: stew (https://github.com/stewForAni)
 */
public class BannerPagerAdapter extends PagerAdapter {

    private static final String TAG = BannerPagerAdapter.class.getSimpleName();
    private Context context;
    private List<String> imageUrlsList;

    public BannerPagerAdapter(Context context, List<String> imageUrlsList) {
        this.context = context;
        this.imageUrlsList = imageUrlsList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d(TAG, "instantiateItem: ---------" + position);
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
        ImageView imageView = view.findViewById(R.id.banner_item_image);
        String url = imageUrlsList.get(position);
        Glide.with(context).load(url).into(imageView);
        imageView.setTag(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                Toast.makeText(context, "position = " +position, 0).show();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return imageUrlsList.size();
    }

    /**
     * @param view
     * @param o
     * @return the view is the id , if view == o the view can be reuse. ???
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        super.finishUpdate(container);
    }
}
