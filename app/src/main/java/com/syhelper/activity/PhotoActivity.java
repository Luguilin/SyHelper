package com.syhelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.syhelper.AppConfig;
import com.syhelper.R;
import com.syhelper.adapter.MyImageAdapter;
import com.syhelper.view.PhotoViewPage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends AppCompatActivity {

    @BindView(R.id.page)
    PhotoViewPage mViewPager;

    List<View> mData = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < 5; i++) {
            mData.add(inflater.inflate(R.layout.photo_item, null, false));
        }
        initData();
    }

    int currentPosition=0;
    MyImageAdapter myImageAdapter;
    private List<String> Urls;
    private void initData() {
        Urls=new ArrayList<>();
        Urls.addAll(AppConfig.mImage);
        Intent intent = getIntent();
        currentPosition = intent.getIntExtra("currentPosition", 0);
//        HomeQuestionListModel.DataBeanX DataBean = ((HomeQuestionListModel.DataBeanX) intent.getSerializableExtra("questionlistdataBean"));
//        Urls = DataBean.getAttach().getImage().getOri();

        myImageAdapter = new MyImageAdapter(Urls, this);
        mViewPager.setAdapter(myImageAdapter);
        mViewPager.setCurrentItem(currentPosition, false);
//        mTvImageCount.setText(currentPosition+1 + "/" + Urls.size());
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
//                mTvImageCount.setText(currentPosition + 1 + "/" + Urls.size());
            }
        });
    }

}
