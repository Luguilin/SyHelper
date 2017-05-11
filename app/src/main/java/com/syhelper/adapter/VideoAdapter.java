package com.syhelper.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syhelper.R;
import com.syhelper.api.ApiConfig;
import com.syhelper.bean.Video;
import com.syhelper.bean.VideoItem;

import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by LGL on 2017/4/7.
 */

public class VideoAdapter extends CommonAdapter<VideoItem> {

    Map<String, Video> mVideoMap;

    public void setVideoMap(Map<String, Video> videoMap) {
        this.mVideoMap = videoMap;
    }

    public VideoAdapter(Context context, List<VideoItem> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, VideoItem videoItem) {
        holder.setText(R.id.tv_uploaderName, videoItem.getUploaderName());
        Video video = mVideoMap.get(videoItem.getRescontrnId() + "");

        if (video == null) return;

        JCVideoPlayerStandard videoPlayerStandard = holder.getView(R.id.videoplayer);
        videoPlayerStandard.setUp(ApiConfig.imageBaseUrl + video.getVideoURL()
                , JCVideoPlayer.SCREEN_LAYOUT_LIST, video.getVideoTypeName());
        Picasso.with(mContext).load(ApiConfig.imageBaseUrl + video
                .getVideoImageURL()).into(videoPlayerStandard.thumbImageView);

        TextView tv_explanation=holder.getView(R.id.tv_explanation);
        tv_explanation.setText(Html.fromHtml("<font color='black'>内容介绍：</font>" + video.getExplanation()));
        videoPlayerStandard.titleTextView.setText("测试视频");
        holder.setText(R.id.tv_uploaderName,video.getVideoTypeName());
    }
}
