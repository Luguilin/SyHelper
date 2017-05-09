package com.syhelper.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.syhelper.AppConfig;
import com.syhelper.CircleTransform;
import com.syhelper.R;

import java.util.List;

/**
 * Created by LGL on 2017/4/24.
 */

public class BigMovieAdapter extends CommonAdapter<String>{

    public BigMovieAdapter(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, String s) {
        Picasso.with(mContext).load(AppConfig.getImageUrl()).into((ImageView) holder.getView(R.id.iv_image_gree_video));
        Picasso.with(mContext).load(AppConfig.touxiang).transform(new CircleTransform())
                .into((ImageView) holder.getView(R.id.iv_people_face));
    }
}
