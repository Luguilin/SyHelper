package com.syhelper.net;

import com.syhelper.tool.LHandler;

import java.util.Map;

/**
 * Created by LGL on 2017/1/17.
 */

public abstract class BaseOkHttpClient implements HttpClient, HttpCallBack {



    public boolean Debug=false;

    public void setDebug(boolean debug) {
        Debug = debug;
    }

    @Override
    public void post(String pathSegment, Map<String, String> parameter, Map<String, String> extra,
                     HttpCallBack callback, String... host) {
        initCallBack(callback);
    }

    @Override
    public void get(String pathSegment, Map<String, String> parameter,
                    HttpCallBack callback, String... host) {
        initCallBack(callback);
    }

    HttpCallBack callback;
    protected void setCallBack(HttpCallBack callback) {
        this.callback = callback;
    }

    private void initCallBack(HttpCallBack callback) {
        this.callback = callback;
    }

    @Override
    public void success(final StringBuilder response) {
        if (callback == null) return;
        LHandler.switchMainThread(new Runnable() {
            @Override
            public void run() {
                callback.success(response);
            }
        });
    }

    @Override
    public void failure(final StringBuilder msg) {
        if (callback == null) return;
        LHandler.switchMainThread(new Runnable() {
            @Override
            public void run() {
                callback.failure(msg);
            }
        });
    }
}
