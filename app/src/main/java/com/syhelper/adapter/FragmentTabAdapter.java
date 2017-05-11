package com.syhelper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentTabAdapter implements CompoundButton.OnCheckedChangeListener {

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentActivity baseFragmentActivity;
    private int currentIndex;
    private int fragmentContentId; // Activity中所要被替换的区域的id
    private List<RadioButton> rbList;

    public FragmentTabAdapter(FragmentActivity baseFragmentActivity, List<Fragment> fragments
            , List<RadioButton> rbList, int fragmentContentId) {
        this(baseFragmentActivity, fragments, rbList, fragmentContentId, 0);
    }

    public FragmentTabAdapter(FragmentActivity baseFragmentActivity, List<Fragment> fragments
            , List<RadioButton> rbList, int fragmentContentId, int currentIndex) {
        this.currentIndex = currentIndex;
        this.baseFragmentActivity = baseFragmentActivity;
        this.fragments = fragments;
        this.fragmentContentId = fragmentContentId;
        this.rbList = rbList;

        FragmentTransaction ft = baseFragmentActivity.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments.get(currentIndex));
        ft.commit();
        for (int i = 0; i < rbList.size(); i++) {
            rbList.get(i).setOnCheckedChangeListener(this);
        }
    }

    private void showTab(int index) {
        for (int i = 0; i < fragments.size(); i++) {
            FragmentTransaction ft = baseFragmentActivity.getSupportFragmentManager().beginTransaction();
            Fragment f = fragments.get(i);
            if (i == index) {
                ft.show(f);
            } else {
                ft.hide(f);
            }
            ft.commit();
//            ft.commitAllowingStateLoss();
        }
        currentIndex = index;
        if (switchTabListener!=null)switchTabListener.SwitchTab(index);
    }

    SwitchTabListener switchTabListener;

    public void setSwitchTabListener(SwitchTabListener switchTabListener) {
        this.switchTabListener = switchTabListener;
    }

    public interface SwitchTabListener{
        public void SwitchTab(int index);
    }

    private Fragment getCurrentFragment() {
        return fragments.get(currentIndex);
    }

    public Fragment getFragment(int index) {
        return fragments.get(index);
    }

    public void TurnTab(int index) {
        rbList.get(index).setChecked(true);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            for (int i = 0; i < rbList.size(); i++) {
                if (rbList.get(i).getId() == buttonView.getId()) {
                    Fragment f = fragments.get(i);
                    // 暂停当前Fragment
                    getCurrentFragment().onPause();
                    FragmentTransaction ft = baseFragmentActivity.getSupportFragmentManager().beginTransaction();
                    if (fragments.get(i).isAdded()) {
                        f.onResume();
                    } else {
                        ft.add(fragmentContentId, f);
                    }
                    ft.commit();
//                    ft.commitAllowingStateLoss();
                    showTab(i);
                } else {
                    rbList.get(i).setChecked(false);
                }
            }
        }

    }
}