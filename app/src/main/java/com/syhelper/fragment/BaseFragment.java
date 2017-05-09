package com.syhelper.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syhelper.R;
import com.syhelper.tool.LDialog;

/**
 * Created by LGL on 2017/3/23.
 */

public class BaseFragment extends Fragment {

    protected View mView;

    protected Activity mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return mView;
    }

    LDialog mLoadingDialog=null;
    protected void ShowLoading(){
        if (mLoadingDialog==null){
            mLoadingDialog=new LDialog();
            mLoadingDialog.CreateDailog(mContext, R.layout.dialog_loading,false);
        }
    }
    protected void DismissLoading(){
        if (mLoadingDialog==null){
            mLoadingDialog.dismiss();
        }
    }
}
