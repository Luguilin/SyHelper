package com.syhelper.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syhelper.CircleTransform;
import com.syhelper.R;
import com.syhelper.activity.CommonActivity;
import com.syhelper.activity.MySpaceActivity;
import com.syhelper.activity.SettingActivity;
import com.syhelper.tool.T;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LGL on 2017/3/23.
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_login_face)
    ImageView ivFace;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_info)
    TextView tvInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        TextView title = (TextView) mView.findViewById(R.id.tv_title);
        title.setText("个人中心");
        title.setVisibility(View.VISIBLE);

        Picasso.with(mContext)
                .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1887792679,709769868&fm=117&gp=0.jpg")
                .transform(new CircleTransform())
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(ivFace);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.tv_space, R.id.tv_setting, R.id.tv_collect, R.id.tv_universally, R.id.tv_more, R.id.tv_check_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_space:
                startActivity(new Intent(mContext, MySpaceActivity.class));
                break;
            case R.id.tv_setting:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.tv_collect:
                break;
            case R.id.tv_universally:
                startActivity(new Intent(mContext, CommonActivity.class));
                break;
            case R.id.tv_more:
                break;
            case R.id.tv_check_update:
                T.showShort("已经是最新版了");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Picasso.with(mContext)
                    .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1887792679,709769868&fm=117&gp=0.jpg")
                    .transform(new CircleTransform())
                    .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(ivFace);
        }
    }
}
