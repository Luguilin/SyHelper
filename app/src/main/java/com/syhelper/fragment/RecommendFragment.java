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
import com.syhelper.ResType;
import com.syhelper.ResTypeHelper;
import com.syhelper.activity.GreeVideoDetailActivity;
import com.syhelper.activity.VideoDetailActivity;
import com.syhelper.adapter.RecommendAdapter;
import com.syhelper.api.ApiRecommends;
import com.syhelper.bean.Recommend;
import com.syhelper.bean.RecommendMap;
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recom, container, false);
        ButterKnife.bind(this, mView);

        mAdapter = new RecommendAdapter(recommendMaps, mContext);
        mListView.setAdapter(mAdapter);
        mListView.addHeaderView(LayoutInflater.from(mContext).inflate(R.layout.layout_serach, mListView, false));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecommendMap recommendMap = (RecommendMap) parent.getItemAtPosition(position);
                Intent intent = null;
                switch (recommendMap.getType()) {
                    case Picture:
                        intent = new Intent(mContext, GreeVideoDetailActivity.class);
                        break;
                    case VIDEO:
                        intent = new Intent(mContext, VideoDetailActivity.class);
                        intent.putExtra("resourceId", recommendMap.getRecommend().getRescontrnId()+"");
                        break;
                    case recMasterShow:
                        break;
                    case Route:
                        break;
                    default:
                        break;
                }
                if (intent != null) startActivity(intent);
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
                RequestData4Net();
            }
        });
//        mPtrFrame.setPullToRefresh(false);
        mPtrFrame.autoRefresh(true);
//        mPtrFrame.setKeepHeaderWhenRefresh(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    int count = 5;
    int startIndex = 0;

    List<RecommendMap> recommendMaps = new ArrayList<>();

    private void RequestData4Net() {
        ShowLoading();
        apiRecommends.fetch(startIndex, count, new DataListener<RecommendResponse>() {
            @Override
            public void attach(RecommendResponse object) {

                List<Recommend> temp_rec = object.getData();

                if (object.getNowPage() == 1) recommendMaps.clear();

                for (int i = 0; i < temp_rec.size(); i++) {
                    RecommendMap recommendMap = new RecommendMap();
                    String type = temp_rec.get(i).getType();
                    ResType resType = ResTypeHelper.getType(type);
                    recommendMap.setRecommend(temp_rec.get(i));
                    recommendMap.setType(resType);
                    switch (resType) {
                        case recMasterShow:
                            recommendMap.setMasterShow(object.getMasterShows().get(temp_rec.get(i).getRescontrnId() + ""));
                            break;
                        case VIDEO:
                            recommendMap.setVideo(object.getVideos().get(temp_rec.get(i).getRescontrnId() + ""));
                            break;
                        case Picture:
                            recommendMap.setShowImages(object.getImageShowImages().get(temp_rec.get(i).getRescontrnId() + ""));
                            break;
                        case Route:
                            recommendMap.setRoute(object.getRoutes().get(temp_rec.get(i).getRescontrnId() + ""));
                            break;
                    }
                    recommendMaps.add(recommendMap);
                }

                DismissLoading();
                mAdapter.setResponse(object);

                mPtrFrame.refreshComplete();
                mAdapter.notifyDataSetChanged();
                startIndex = object.getNowIndex() + 1;
            }

            @Override
            public void failure(String msg) {
                DismissLoading();
                T.showShort(msg);
            }
        });
    }
}
