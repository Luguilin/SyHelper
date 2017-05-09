package com.syhelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.syhelper.MyApplication;
import com.syhelper.R;
import com.syhelper.adapter.FragmentTabAdapter;
import com.syhelper.fragment.CollectFragment;
import com.syhelper.fragment.HomeFragment;
import com.syhelper.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends BaseActivity {

    List<Fragment> mFragments;
    List<RadioButton> mRadioButtons;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_collect)
    RadioButton rbCollect;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;

    FragmentTabAdapter mTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new CollectFragment());
        mFragments.add(new MineFragment());

        mRadioButtons = new ArrayList<>();
        mRadioButtons.add(rbHome);
        mRadioButtons.add(rbCollect);
        mRadioButtons.add(rbMine);

        mTabAdapter = new FragmentTabAdapter(this, mFragments, mRadioButtons, R.id.flay_main, 0);

        rbMine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && MyApplication.getUser() == null){
                    startActivity(new Intent(mContext, LoginActivity.class));
//                    buttonView.setChecked(false);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
