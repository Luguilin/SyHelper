package com.syhelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syhelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LGL on 2017/3/23.
 * 收藏
 */

public class CollectFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_collect, container, false);
        ButterKnife.bind(this, mView);
        tvTitle.setText("我的收藏");
        tvTitle.setVisibility(View.VISIBLE);
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flay_content,new RecommendFragment(),"CollectFragment_RecommendFragment");
        fragmentTransaction.commit();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @OnClick(R.id.tv_title)
    public void onClick() {
    }
}
