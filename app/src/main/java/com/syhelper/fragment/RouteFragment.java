package com.syhelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.syhelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LGL on 2017/3/24.
 * 路线
 */

public class RouteFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<ListView> {


    @BindView(R.id.refresh_listView)
    PullToRefreshListView mRefreshListView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_route, container, false);
        ButterKnife.bind(this, mView);
        mRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(mContext, RouteDetailActivity.class));
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
