package com.syhelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Html;
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
import com.syhelper.ResType;
import com.syhelper.activity.PhotoActivity;
import com.syhelper.api.ApiConfig;
import com.syhelper.bean.Recommend;
import com.syhelper.bean.RecommendMap;
import com.syhelper.bean.Route;
import com.syhelper.bean.ShowImage;
import com.syhelper.bean.Video;
import com.syhelper.httpBean.RecommendResponse;
import com.syhelper.tool.DateHelper;
import com.syhelper.tool.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static com.syhelper.ResType.Picture;

/**
 * Created by LGL on 2017/5/1.
 */

public class RecommendAdapter extends BaseAdapter {

    List<RecommendMap> mData;

    Context mContext;

    RecommendResponse mResponse;

    public RecommendAdapter(List<RecommendMap> recommendMaps, Context mContext) {
        this.mData = recommendMaps;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = null;
        if (convertView == null) {
            myHolder = new MyHolder();
            switch (mData.get(position).getType()) {
                case Picture:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_greevideo_lv, parent, false);
                    break;
                case VIDEO:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_video_lv, parent, false);
                    break;
                case recMasterShow:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_great_people_lv, parent, false);
                    break;
                case Route:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_together_route_lv, parent, false);
                    break;
            }
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }

        switch (mData.get(position).getType()) {
            case Picture:
                myHolder.BindBigPhotoViewItem(convertView, mData.get(position).getShowImages(), mData.get(position).getRecommend());
                break;
            case VIDEO:
                myHolder.BindVideoViewItem(convertView, mData.get(position).getVideo(), mData.get(position).getRecommend());
                break;
            case recMasterShow:
                break;
            case Route:
                myHolder.BindRouteViewItem(convertView, mData.get(position).getRoute(), mData.get(position).getRecommend());
                break;
            default:
                break;
        }
        ButterKnife.bind(myHolder, convertView);
        myHolder.BindDataAndEvent(mData.get(position).getRecommend());
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType().getValues();
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

        @OnClick(R.id.tv_share)
        public void Share(View view) {
            T.showShort("分享该项成功");
        }

        @OnClick(R.id.textView3)
        public void Collect(View view) {

            if ((boolean) view.getTag(R.id.textView3)) {
                textView3.setCompoundDrawables(getLeftDrawable(R.mipmap.icon_recommend), null, null, null);
                view.setTag(R.id.textView3, false);
            }else{
                textView3.setCompoundDrawables(getLeftDrawable(R.mipmap.icon_recommend_selected), null, null, null);
                view.setTag(R.id.textView3, true);
                T.showShort("收藏成功");
            }
        }

        private Drawable getLeftDrawable(int imageRes) {
            Drawable rightDrawable = mContext.getResources().getDrawable(imageRes);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
            return rightDrawable;
        }

        @OnClick(R.id.tv_likedCount)
        public void LikedCount(View view) {

            int lickCount = Integer.parseInt(tv_likedCount.getText().toString().trim());
            if ((boolean) view.getTag(R.id.tv_likedCount)) {
                tv_likedCount.setCompoundDrawables(getLeftDrawable(R.mipmap.icon_unprise), null, null, null);
                view.setTag(R.id.tv_likedCount, false);
                tv_likedCount.setText(String.valueOf(lickCount - 1));
            }else{
                tv_likedCount.setCompoundDrawables(getLeftDrawable(R.mipmap.icon_praised), null, null, null);
                view.setTag(R.id.tv_likedCount, true);
                tv_likedCount.setText(String.valueOf(lickCount + 1));
            }
        }

        VideoHolder videoHolder = null;
        BigPhotoHolder bigPhotoHolder = null;
        RouteHolder routeHolder = null;

        public void BindDataAndEvent(Recommend recommend) {
            tv_skimedCount.setText(recommend.getSkimedCount() + "");
            tv_likedCount.setText(recommend.getLikedCount() + "");
            tv_conmentCount.setText(recommend.getConmentCount() + "");

            //收藏图标变化
            textView3.setCompoundDrawables(getLeftDrawable(R.mipmap.icon_recommend), null, null, null);
            textView3.setTag(R.id.textView3, false);
            tv_likedCount.setCompoundDrawables(getLeftDrawable(R.mipmap.icon_unprise), null, null, null);
            tv_likedCount.setTag(R.id.tv_likedCount, false);
        }

        public void BindBigPhotoViewItem(View view, List<ShowImage> images, Recommend recommend) {
            if (bigPhotoHolder == null) {
                bigPhotoHolder = new BigPhotoHolder();
                ButterKnife.bind(bigPhotoHolder, view);
            }
            bigPhotoHolder.BindDate(images, recommend);
        }

        public void BindVideoViewItem(View view, Video video, Recommend recommend) {
            if (videoHolder == null) {
                videoHolder = new VideoHolder();
                ButterKnife.bind(videoHolder, view);
            }
            videoHolder.BindDate(video, recommend);
        }

        public void BindRouteViewItem(View view, Route video, Recommend recommend) {
            if (routeHolder == null) {
                routeHolder = new RouteHolder();
                ButterKnife.bind(routeHolder, view);
            }
            routeHolder.BindData(view, video, recommend);
        }
    }

    /**
     * 图片的Holder
     */
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

        public void BindDate(final List<ShowImage> images, Recommend recommend) {
            Picasso.with(mContext).load(AppConfig.touxiang).transform(new CircleTransform()).into(iv_people_face);
            ShowImage showImage = images == null ? null : images.get(0);

            if (showImage == null || images == null) return;

            final int image_count = images.size();
            iv_image_gree_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> strings = new ArrayList<String>();
                    for (int i = 0; i < image_count; i++) {
                        strings.add(ApiConfig.imageBaseUrl + images.get(i).getImageURLThumbnail());
                    }
                    Intent intent = new Intent(mContext, PhotoActivity.class);
                    intent.putStringArrayListExtra("Urls", strings);
                    mContext.startActivity(intent);
                }
            });


            Picasso.with(mContext).load(ApiConfig.imageBaseUrl + showImage.getImageURLThumbnail()).error(R.mipmap.ic_launcher).into(iv_image_gree_video);
            tv_explanation.setText(showImage.getImageTitle());


            if (recommend != null) {
                tv_people_name.setText(recommend.getUploaderName());
                tv_people_time.setText(DateHelper.getString4unixTimestamp(recommend.getUploadTime(), DateHelper.DATE_FULL_STR));
            }
        }
    }


    //视频视图的Holder
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
            Picasso.with(mContext).load(ApiConfig.imageBaseUrl + video.getVideoImageURL()).error(R.mipmap.ic_launcher).into(
                    videoPlayerStandard.thumbImageView);

            tv_explanation.setText(Html.fromHtml("<font color='black'>内容介绍：</font>" + video.getExplanation()));
            tv_uploaderName.setText(recommend.getUploaderName());
            tv_uploadTime.setText(DateHelper.getString4unixTimestamp(recommend.getUploadTime(), DateHelper.DATE_FULL_STR));
        }
    }


    class RouteHolder {

        @BindView(R.id.tv_explanation)
        TextView tv_explanation;
        @BindView(R.id.tv_hText)
        TextView tv_hText;
        @BindView(R.id.tv_uploaderName)
        TextView tv_uploaderName;
        @BindView(R.id.tv_updateTime)
        TextView tv_updateTime;
        @BindView(R.id.iv_together_image)
        ImageView iv_together_image;

        public void BindData(View view, Route route, Recommend recommend) {
            tv_explanation.setText(route.getExplanation());
            tv_hText.setText(route.getHText());
            tv_uploaderName.setText(recommend.getUploaderName());
            tv_updateTime.setText(DateHelper.getString4unixTimestamp(recommend.getUploadTime()
                    , DateHelper.DATE_FULL_STR));
            Picasso.with(mContext).load(ApiConfig.imageBaseUrl + route.getTitleImageURL())
                    .error(R.mipmap.ic_launcher).into(iv_together_image);
        }

    }
}
