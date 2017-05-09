package com.syhelper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.syhelper.AppConfig;
import com.syhelper.R;
import com.syhelper.activity.GreeVideoDetailActivity;
import com.syhelper.adapter.BigMovieAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by LGL on 2017/3/23.
 * 大片Fragment
 */

public class GreeVideo extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<ListView> {

    @BindView(R.id.rotate_header_list_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    @BindView(R.id.refresh_listView)
    ListView mListView;

    List<String> mData;

    BigMovieAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_gree, container, false);

        ButterKnife.bind(this, mView);

        mData = new ArrayList<>();
        mData.add(AppConfig.dashi);
        mData.add(AppConfig.dashi);
        mData.add(AppConfig.dashi);
        mData.add(AppConfig.dashi);
        mData.add(AppConfig.dashi);


        mAdapter=new BigMovieAdapter(mContext,mData, R.layout.item_greevideo_lv);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, GreeVideoDetailActivity.class);
                startActivity(intent);
            }
        });
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPullToRefresh(true);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                        mAdapter.notifyDataSetChanged();
                    }
                }, 2000);
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
