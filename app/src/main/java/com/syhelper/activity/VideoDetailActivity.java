package com.syhelper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syhelper.DataListener;
import com.syhelper.R;
import com.syhelper.api.ApiConfig;
import com.syhelper.api.ApiVideoInfo;
import com.syhelper.bean.Video;
import com.syhelper.httpBean.VideoInfo;
import com.syhelper.tool.L;
import com.syhelper.tool.T;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @BindView(R.id.tv_context)
    TextView tvContext;
   @BindView(R.id.tv_back)
    TextView tvVack;

    int resourceId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);
        tvTitle.setVisibility(View.VISIBLE);
        tvVack.setVisibility(View.VISIBLE);
        tvTitle.setText("视频详情");
        resourceId = getIntent().getIntExtra("resourceId", 0);
        L.e("resourceId===="+resourceId);
        RequestNet();
    }

    @OnClick(R.id.tv_back)
    public void onClick() {
        onBackPressed();
    }

    private void RequestNet() {
        ApiVideoInfo apiVideoInfo = new ApiVideoInfo();
        apiVideoInfo.getVideoInfo(resourceId, new DataListener<VideoInfo>() {
            @Override
            public void attach(VideoInfo object) {
                if (object == null) {
                    failure("");
                    return;
                }
                Video video = object.getVideo();
                if (video == null) {
                    failure("");
                    return;
                }
                videoplayer.setUp(ApiConfig.imageBaseUrl + video.getVideoURL()
                        , JCVideoPlayer.SCREEN_LAYOUT_LIST, video.getVideoTypeName());
                Picasso.with(mContext).load(ApiConfig.imageBaseUrl + video
                        .getVideoImageURL()).into(videoplayer.thumbImageView);
                tvContext.setText(video.getExplanation());
            }

            @Override
            public void failure(String msg) {
                T.showShort("无法请求到详情信息");
                onBackPressed();
                return;
            }
        });
    }
}
