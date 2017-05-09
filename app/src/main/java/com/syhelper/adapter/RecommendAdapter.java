package com.syhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syhelper.AppConfig;
import com.syhelper.CircleTransform;
import com.syhelper.R;
import com.syhelper.api.ApiConfig;
import com.syhelper.bean.Recommend;
import com.syhelper.bean.ShowImage;
import com.syhelper.bean.Video;
import com.syhelper.httpBean.RecommendResponse;
import com.syhelper.tool.DateHelper;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by LGL on 2017/5/1.
 */

public class RecommendAdapter extends BaseAdapter {

    List<Recommend> mData;

    Context mContext;

    RecommendResponse mResponse;

    public RecommendAdapter(List<Recommend> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    public void setResponse(RecommendResponse response) {
        this.mResponse = response;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public final static int Picture = 0;
    public final static int VIDEO = 1;
    public final static int Multi_Picture = 2;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = null;
        if (convertView == null) {
            myHolder = new MyHolder();
            switch (getItemViewType(position)) {
                case Picture:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_greevideo_lv, parent, false);
                    break;
                case VIDEO:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_video_lv, parent, false);
                    break;
                case Multi_Picture:
                    break;
                case 3:
                    break;
                default:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_recom_lv, parent, false);
                    break;
            }
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }

        switch (getItemViewType(position)) {
            case Picture:
                myHolder.BindBigPhotoViewItem(convertView, mData.get(position));
                break;
            case VIDEO:
                myHolder.BindVideoViewItem(convertView, mData.get(position));
                break;
            case Multi_Picture:
                break;
            case 3:
                break;
            default:
                break;
        }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        switch (mData.get(position).getType()) {
            case "recImageShow":
                return Picture;
            case "recVideo":
                return VIDEO;
            case "recMasterShow":
                return Picture;
            case "route":
                return Picture;
        }
        return super.getItemViewType(position);
    }

    class MyHolder {
        @BindView(R.id.tv_skimedCount)
        TextView tv_skimedCount;
        @BindView(R.id.tv_likedCount)
        TextView tv_likedCount;
        @BindView(R.id.tv_conmentCount)
        TextView tv_conmentCount;
        @BindView(R.id.textView3)
        TextView textView3;
        @BindView(R.id.tv_share)
        TextView tv_share;

        VideoHolder videoHolder = null;
        BigPhotoHolder bigPhotoHolder = null;

        public void BindBigPhotoViewItem(View view, Recommend recommend) {
            if (bigPhotoHolder == null) {
                bigPhotoHolder = new BigPhotoHolder();
                ButterKnife.bind(bigPhotoHolder, view);
            }
            List<ShowImage> images = null;
            if (mResponse.getImageShowImages() != null)
                images = mResponse.getImageShowImages().get(recommend.getRescontrnId() + "");
            if (images == null) return;
            bigPhotoHolder.BindDate(images, recommend);
        }

        public void BindVideoViewItem(View view, Recommend recommend) {
            if (videoHolder == null) {
                videoHolder = new VideoHolder();
                ButterKnife.bind(videoHolder, view);
            }
            Video video = null;
            if (mResponse.getVideos() != null)
                video = mResponse.getVideos().get("" + recommend.getRescontrnId());
            if (video == null) return;
            videoHolder.BindDate(video, recommend);
        }
    }

    class BigPhotoHolder {
        @BindView(R.id.iv_image_gree_video)
        ImageView iv_image_gree_video;
        @BindView(R.id.iv_people_face)
        ImageView iv_people_face;
        @BindView(R.id.tv_explanation)
        TextView tv_explanation;
        @BindView(R.id.tv_people_name)
        TextView tv_people_name;
        @BindView(R.id.tv_people_time)
        TextView tv_people_time;

        public void BindDate(List<ShowImage> images, Recommend recommend) {
            Picasso.with(mContext).load(AppConfig.touxiang).transform(new CircleTransform()).into(iv_people_face);
            ShowImage showImage = images == null ? null : images.get(0);
//            if (showImage != null) {
//                Picasso.with(mContext).load(ApiConfig.imageBaseUrl + showImage.getImageURLThumbnail()).into(iv_image_gree_video);
//                tv_explanation.setText(showImage.getImageTitle());
//            }

            Picasso.with(mContext).load(ApiConfig.imageBaseUrl + showImage.getImageURLThumbnail()).into(iv_image_gree_video);
            tv_explanation.setText(showImage.getImageTitle());


            if (recommend != null) {
                tv_people_name.setText(recommend.getUploaderName());
                tv_people_time.setText(DateHelper.getString4unixTimestamp(recommend.getUploadTime(), DateHelper.DATE_FULL_STR));
            }
        }
    }


    class VideoHolder {

        @BindView(R.id.tv_explanation)
        TextView tv_explanation;
        @BindView(R.id.tv_uploadTime)
        TextView tv_uploadTime;
        @BindView(R.id.tv_uploaderName)
        TextView tv_uploaderName;

        @BindView(R.id.videoplayer)
        JCVideoPlayerStandard videoPlayerStandard;


        public void BindDate(Video video, Recommend recommend) {
            videoPlayerStandard.setUp(ApiConfig.imageBaseUrl + video.getVideoURL()
                    , JCVideoPlayer.SCREEN_LAYOUT_LIST, recommend.getTitle());
            videoPlayerStandard.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(mContext).load(ApiConfig.imageBaseUrl + video.getVideoImageURL()).into(
                    videoPlayerStandard.thumbImageView);

            tv_explanation.setText(video.getExplanation());
            tv_uploaderName.setText(recommend.getUploaderName());
            tv_uploadTime.setText(DateHelper.getString4unixTimestamp(recommend.getUploadTime(), DateHelper.DATE_FULL_STR));
        }
    }
}
