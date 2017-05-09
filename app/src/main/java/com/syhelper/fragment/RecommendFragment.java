package com.syhelper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.syhelper.DataListener;
import com.syhelper.R;
import com.syhelper.activity.VideoDetailActivity;
import com.syhelper.adapter.RecommendAdapter;
import com.syhelper.api.ApiRecommends;
import com.syhelper.bean.Recommend;
import com.syhelper.httpBean.RecommendResponse;
import com.syhelper.tool.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * Created by LGL on 2017/3/23.
 * 推荐Fragment
 */

public class RecommendFragment extends BaseFragment {

    @BindView(R.id.rotate_header_list_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    @BindView(R.id.refresh_listView)
    ListView mListView;
    RecommendAdapter mAdapter;


    ApiRecommends apiRecommends = new ApiRecommends();


    List<Recommend> mData=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recom, container, false);
        ButterKnife.bind(this, mView);


        mAdapter = new RecommendAdapter(mData,mContext);
        mListView.setAdapter(mAdapter);
        mListView.addHeaderView(LayoutInflater.from(mContext).inflate(R.layout.layout_serach,mListView,false));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, VideoDetailActivity.class);
                intent.putExtra("resourceId", 158);
                startActivity(intent);
            }
        });

        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler2() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                startIndex = 0;
                RequestData4Net();
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
//                RequestData4Net();
            }
        });
//        mPtrFrame.setPullToRefresh(false);
        mPtrFrame.autoRefresh(true);
//        mPtrFrame.setKeepHeaderWhenRefresh(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    int count = 5;
    int startIndex = 0;

    private void RequestData4Net() {
        ShowLoading();
        apiRecommends.fetch(startIndex, count, new DataListener<RecommendResponse>() {
            @Override
            public void attach(RecommendResponse object) {
                DismissLoading();

                if (object.getNowPage()==1)mData.clear();
                if (object.getData() != null) mData.addAll(object.getData());
                mAdapter.setResponse(object);

                mPtrFrame.refreshComplete();
                mAdapter.notifyDataSetChanged();

                startIndex = object.getNowIndex()+1;
            }

            @Override
            public void failure(String msg) {
                DismissLoading();
                T.showShort(msg);
            }
        });
    }
}
