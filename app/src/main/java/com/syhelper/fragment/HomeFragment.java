package com.syhelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.syhelper.R;
import com.syhelper.adapter.HomeViewPaterAdapter;
import com.syhelper.view.NoScrollViewPager;
import com.syhelper.view.UnderlinePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCUserAction;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;

/**
 * Created by LGL on 2017/3/23.
 * 主页
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.indicator)
    UnderlinePageIndicator mIndicator;
    @BindView(R.id.pager)
    NoScrollViewPager mViewPager;

    List<Fragment> mFragments;

    HomeViewPaterAdapter mHomeViewPaterAdapter;


    @BindView(R.id.rg_title_home)
    RadioGroup mRadioGroupTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mView);

        mFragments = new ArrayList<>();
        mFragments.add(new RecommendFragment());
        mFragments.add(new VideoFragment());
        mFragments.add(new GreeVideo());
        mFragments.add(new GreatPeople());
        mFragments.add(new TogetherFragment());
        mHomeViewPaterAdapter = new HomeViewPaterAdapter(getChildFragmentManager(), mFragments);
        mViewPager.setAdapter(mHomeViewPaterAdapter);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setScroll(false);

        mIndicator.setViewPager(mViewPager);
        mIndicator.setSelectedColor(0xffffffff);
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int index = R.id.rb_title_recommend;
                switch (position) {
                    case R.id.rb_title_recommend:
                        break;
                    case 1:
                        index = R.id.rb_title_video;
                        break;
                    case 2:
                        index = R.id.rb_title_gree_video;
                        break;
                    case 3:
                        index = R.id.rb_title_great_people;
                        break;
                    case 4:
                        index = R.id.rb_title_together;
                        break;
                }
                for (int i = 0; i < mRadioGroupTitle.getChildCount(); i++) {
                    ((RadioButton)mRadioGroupTitle.getChildAt(i)).setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                }

                if (JCVideoPlayerManager.getCurrentJcvd() != null) {
                    JCMediaManager.instance().mediaPlayer.pause();
                    JCVideoPlayerManager.getCurrentJcvd().onEvent(JCUserAction.ON_CLICK_PAUSE);
                    JCVideoPlayerManager.getCurrentJcvd().setUiWitStateAndScreen(JCVideoPlayer.CURRENT_STATE_PAUSE);
                }
                ((RadioButton)mRadioGroupTitle.findViewById(index)).setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mRadioGroupTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = 0;
                switch (checkedId) {
                    case R.id.rb_title_recommend:
                        break;
                    case R.id.rb_title_video:
                        index = 1;
                        break;
                    case R.id.rb_title_gree_video:
                        index = 2;
                        break;
                    case R.id.rb_title_great_people:
                        index = 3;
                        break;
                    case R.id.rb_title_together:
                        index = 4;
                        break;
                }
                if (mViewPager.getCurrentItem() != index)
                    mViewPager.setCurrentItem(index);
            }
        });

        mViewPager.setCurrentItem(0);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
