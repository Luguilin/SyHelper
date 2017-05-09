package com.syhelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.syhelper.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LGL on 2017/3/27.
 */

public class RouteDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);
        ButterKnife.bind(this);
        ((TextView) findViewById(R.id.tv_title)).setText("西藏之路");
        findViewById(R.id.tv_back).setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.tv_back)
    public void onClick() {
        onBackPressed();
    }
}
