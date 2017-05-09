package com.syhelper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.syhelper.R;
import com.syhelper.tool.T;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.tv_back, R.id.btn_logout, R.id.tv_change_info, R.id.tv_change_pwd, R.id.tv_change_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                onBackPressed();
                break;
            case R.id.btn_logout:
                T.showShort("退出成功");
                break;
            case R.id.tv_change_info:
                break;
            case R.id.tv_change_pwd:
                break;
            case R.id.tv_change_phone:
                break;
        }
    }
}
