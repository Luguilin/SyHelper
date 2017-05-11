package com.syhelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.syhelper.R;
import com.syhelper.fragment.CollectFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 我的空间
 */
public class MySpaceActivity extends BaseActivity {

//    @BindView(R.id.tv_back)
//    TextView tvBack;
//    @BindView(R.id.tv_title)
//    TextView tvTitle;
//    @BindView(R.id.ptr_frame)
//    PtrFrameLayout mPtrFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_space);
        ButterKnife.bind(this);

//        tvBack.setVisibility(View.VISIBLE);
//        tvTitle.setVisibility(View.VISIBLE);
//        tvTitle.setText("我的空间");

//        findViewById(R.id.tv_send_image).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(mContext, PublishPhotoActivity.class));
//            }
//        });
        getSupportFragmentManager().beginTransaction().add(R.id.fay,new CollectFragment()).commit();
    }


//    @OnClick(R.id.tv_back)
//    public void onViewClicked() {
//        onBackPressed();
//    }
}
