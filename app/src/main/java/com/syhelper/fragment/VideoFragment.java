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
import com.syhelper.activity.VideoDetailActivity;
import com.syhelper.adapter.VideoAdapter;
import com.syhelper.api.ApiVideos;
import com.syhelper.bean.VideoItem;
import com.syhelper.httpBean.VideosResponse;
import com.syhelper.tool.L;
import com.syhelper.tool.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LGL on 2017/3/23.
 * 视频Fragment
 */

public class VideoFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<ListView> {


    @BindView(R.id.refresh_listView)
    PullToRefreshListView mRefreshListView;

    VideoAdapter mVideoAdapter;

    List<VideoItem> mData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_video, container, false);

        ButterKnife.bind(this, mView);

        mRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mRefreshListView.setOnRefreshListener(this);
        L.e("VideoFragment");
        mRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoItem videoItem = (VideoItem) parent.getItemAtPosition(position);
                if (videoItem != null) {
                    Intent intent = new Intent(mContext, VideoDetailActivity.class);
                    intent.putExtra("resourceId", videoItem.getId());
                    startActivity(intent);
                } else {
                    T.showShort("异常");
                }
            }
        });

        getRequestData();
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    int pageCount = 10;
    int startIndex = 0;
    int viceoTypeId = 0;

    private void getRequestData() {
        ApiVideos apiVideos = new ApiVideos();
        apiVideos.getVideos(startIndex, pageCount, viceoTypeId, new DataListener<VideosResponse>() {
            @Override
            public void attach(VideosResponse object) {
                if (object == null) {
                    T.showShort("没有更多视频信息");
                    return;
                }
                if (object.getNowPage() == 1) {
                    if (mData == null) mData = new ArrayList<VideoItem>();
                    mData.clear();
                }
                if (object.getData() != null) {
                    mData.addAll(object.getData());
                }

                if (mRefreshListView.isRefreshing()) mRefreshListView.onRefreshComplete();

                if (mVideoAdapter == null) {
                    mVideoAdapter = new VideoAdapter(mContext, mData, R.layout.item_video_lv);
                    mVideoAdapter.setVideoMap(object.getVideos());
                    mRefreshListView.setAdapter(mVideoAdapter);
                }
                mVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(String msg) {
                T.showShort(msg);
            }
        });
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        startIndex = 0;
        getRequestData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        startIndex += 10;
        getRequestData();
    }
}
