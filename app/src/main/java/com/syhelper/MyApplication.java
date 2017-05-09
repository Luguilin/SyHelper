package com.syhelper;

import android.app.Application;

import com.syhelper.bean.User;
import com.syhelper.tool.LHandler;
import com.syhelper.tool.T;

/**
 * Created by LGL on 2017/3/23.
 */

public class MyApplication extends Application {

    private static Application mContext;
    /**
     * true为连接不上网络  false 有网
     */
    public static boolean netWorkUnavailable = false;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        try {
            LHandler.initHandler(this);
            T.Instance(mContext,"摄影助手");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Application getContext() {
        return mContext;
    }

    static User user;

    public static User getUser() {
        return MyApplication.user;
    }

    public static void setUser(User user) {
        MyApplication.user = user;
    }
}
