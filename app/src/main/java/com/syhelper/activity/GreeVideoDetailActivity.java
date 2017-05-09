package com.syhelper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syhelper.AppConfig;
import com.syhelper.CircleTransform;
import com.syhelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 大片详情
 */
public class GreeVideoDetailActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_people_face)
    ImageView ivPeopleFace;
    @BindView(R.id.tv_conmentCount)
    TextView tvConmentCount;
    @BindView(R.id.cb_likedCount)
    CheckBox cbLikedCount;
    @BindView(R.id.activity_video_detail)
    RelativeLayout activityVideoDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gree_video_detail);
        ButterKnife.bind(this);
        tvTitle.setText("大片详情");
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);

        Picasso.with(mContext).load(AppConfig.touxiang).transform(new CircleTransform())
                .error(R.mipmap.ic_launcher).into(ivPeopleFace);

    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
