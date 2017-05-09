package com.syhelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.syhelper.AppConfig;
import com.syhelper.CircleTransform;
import com.syhelper.R;
import com.syhelper.adapter.CommonAdapter;
import com.syhelper.adapter.GreatPeopleAdapter;
import com.syhelper.adapter.ViewHolder;
import com.syhelper.view.MyDrawerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LGL on 2017/3/23.
 * 名家Fragment
 */

public class GreatPeople extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<ListView> {

    @BindView(R.id.refresh_listView)
    PullToRefreshListView mRefreshListView;

    List<String> mData;

    @BindView(R.id.drawer_layout)
    MyDrawerLayout mDrawerLayout;

//    @BindView(R.id.lay_left)
//    LinearLayout lay_left;

    @BindView(R.id.tv_tag_open)
    ImageView tv_tag_open;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_great, container, false);

        ButterKnife.bind(this, mView);


        tv_tag_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT, true);
                tv_tag_open.setVisibility(View.GONE);
            }
        });
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {

            @Override
            public void onDrawerClosed(View drawerView) {
                tv_tag_open.setVisibility(View.VISIBLE);
            }
        });

        mData = new ArrayList<>();
        mData.add(AppConfig.minjia);
        mData.add(AppConfig.minjia);
        mData.add(AppConfig.minjia);
        mData.add(AppConfig.minjia);
        mData.add(AppConfig.minjia);

        mRefreshListView.setAdapter(new CommonAdapter<String>(getContext()
                , mData, R.layout.item_great_people_lv) {
            @Override
            public void convert(ViewHolder holder, String s) {

                Picasso.with(mContext).load(AppConfig.touxiang).transform(new CircleTransform())
                        .into((ImageView) holder.getView(R.id.iv_great_people_face));

//                MyGalleryViewPager viewPager=holder.getView(R.id.vp_picture);
                ViewPager viewPager = holder.getView(R.id.vp_picture);

                viewPager.setAdapter(new GreatPeopleAdapter(mContext, mData));
                viewPager.setOffscreenPageLimit(3);
//                viewPager.setClipChildren(false);
                viewPager.setPageMargin(20);
//                viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
                // 2.设置页与页之间的间距
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        if (refreshView.isRefreshing()) refreshView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        if (refreshView.isRefreshing()) refreshView.onRefreshComplete();
    }
}
