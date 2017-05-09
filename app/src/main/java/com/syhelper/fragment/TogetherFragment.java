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
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.syhelper.DataListener;
import com.syhelper.R;
import com.syhelper.activity.RouteDetailActivity;
import com.syhelper.adapter.TogetherRouteAdapter;
import com.syhelper.api.ApiRoutes;
import com.syhelper.bean.Route;
import com.syhelper.httpBean.RoutesResponse;
import com.syhelper.tool.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LGL on 2017/3/23.
 * 活动
 */

public class TogetherFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<ListView> {

    @BindView(R.id.refresh_listView)
    PullToRefreshListView mRefreshListView;

    List<Route> mData = new ArrayList<>();
    TogetherRouteAdapter mRouteAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_together, container, false);
        ButterKnife.bind(this, mView);

        mRefreshListView.setOnRefreshListener(this);
//        mRouteAdapter = new TogetherRouteAdapter(mContext, mData, R.layout.item_together_route_lv);
        mRefreshListView.setAdapter(mRouteAdapter);
        mRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

        mRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(mContext, RouteDetailActivity.class));
            }
        });
        fetchRoute();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    int start_index = 0;
    private final int count = 10;

    private void fetchRoute() {
        ApiRoutes apiRoutes = new ApiRoutes();
        apiRoutes.getRoutes(start_index, count, new DataListener<RoutesResponse>() {
            @Override
            public void attach(RoutesResponse object) {
                if (object != null) {
                    if (start_index == 0) {
                        mData.clear();
                    }
                    if (object.getRoutes() != null)
                        mData.addAll(object.getRoutes().values());
                } else {
                    T.showShort("没有更多信息");
                }
                if (mRouteAdapter != null) mRouteAdapter.notifyDataSetChanged();
                if (mRefreshListView.isRefreshing()) mRefreshListView.onRefreshComplete();
            }

            @Override
            public void failure(String msg) {
                T.showShort(msg);
                if (mRefreshListView.isRefreshing()) mRefreshListView.onRefreshComplete();
            }
        });
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

        start_index = 0;
        fetchRoute();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        start_index += count;
        fetchRoute();
    }
}
