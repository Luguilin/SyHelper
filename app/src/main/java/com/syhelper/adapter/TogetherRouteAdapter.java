package com.syhelper.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.syhelper.R;
import com.syhelper.api.ApiConfig;
import com.syhelper.bean.Resource;
import com.syhelper.bean.Route;
import com.syhelper.tool.DateHelper;

import java.util.List;

/**
 * Created by LGL on 2017/3/27.
 */

public class TogetherRouteAdapter extends CommonAdapter<Route> {


    public TogetherRouteAdapter(Context context, List<Route> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Route route) {
        holder.setText(R.id.tv_explanation, "\t\t\t\t"+route.getExplanation());
        holder.setText(R.id.tv_hText, route.getHText());


        Resource resource=route.getResource();
        if (resource != null){
            holder.setText(R.id.tv_uploaderName, resource.getUploaderName());
            holder.setText(R.id.tv_updateTime, DateHelper.getUnixTimestamp2DateString(
                    resource.getUpdateTime(),DateHelper.DATE_FULL_STR));

            holder.setText(R.id.tv_skimedCount, resource.getSkimedCount()+"");
            holder.setText(R.id.cb_likedCount, resource.getLikedCount()+"");
            holder.setText(R.id.tv_conmentCount, resource.getConmentCount()+"");
        }

        Picasso.with(mContext).load(ApiConfig.imageBaseUrl + route.getTitleImageURL())
                .into((ImageView) holder.getView(R.id.iv_together_image));
    }
}
