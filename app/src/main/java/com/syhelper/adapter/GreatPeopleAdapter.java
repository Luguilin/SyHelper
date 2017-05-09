package com.syhelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.syhelper.R;
import com.syhelper.activity.PhotoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGL on 2017/3/24.
 */

public class GreatPeopleAdapter extends PagerAdapter implements View.OnClickListener {

    Context mContext;
    List<String> mData;
    List<ImageView> mViews = new ArrayList<>();

    public GreatPeopleAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        for (int i = 0; i < getCount(); i++) {
            ImageView view = (ImageView) layoutInflater.inflate(R.layout.item_great_people_vp, null, false);
            view.setOnClickListener(this);
            mViews.add(view);
        }
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        (ImageView(mData.get(position))
        container.addView(mViews.get(position));//
        Picasso.with(mContext).load(mData.get(position)).into(mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public float getPageWidth(int position) {
        return super.getPageWidth(position) * 0.4f;
    }

    @Override
    public void onClick(View v) {
        mContext.startActivity(new Intent(mContext, PhotoActivity.class));
    }
}
