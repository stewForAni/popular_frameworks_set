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
import com.stew.new_stew.widget.ShapedImageView;

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
        Log.d(TAG, "BannerPagerAdapter: ");
        Log.d(TAG, "context: " + context);
        this.context = context;
        this.imageUrlsList = imageUrlsList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.banner_item, container, false);
        ShapedImageView imageView = view.findViewById(R.id.banner_item_image);
        String url = imageUrlsList.get(position);
        Glide.with(context).load(url).into(imageView);

        imageView.setTag(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position1 = (Integer) v.getTag();
                Toast.makeText(context, "position = " + position1, Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: " + imageUrlsList.size());
        return imageUrlsList.size();
    }

    /**
     * the view is the id , if view == o the view can be reuse. ???
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        Log.d(TAG, "isViewFromObject: ");
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(TAG, "destroyItem: ");
        container.removeView((View) object);
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        super.finishUpdate(container);
    }
}
