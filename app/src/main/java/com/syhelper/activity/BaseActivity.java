package com.syhelper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.syhelper.R;
import com.syhelper.tool.LDialog;

/**
 * Created by LGL on 2017/3/23.
 */

public class BaseActivity extends AppCompatActivity {
    Activity mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
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
