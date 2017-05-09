package com.syhelper.net;


import com.syhelper.MyApplication;
import com.syhelper.tool.L;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


class OKHttpPost extends BaseOkHttpClient {

    @Override
    public void post(String url, Map<String, String> parameter, Map<String, String> extra, HttpCallBack callback, String... host) {
        super.post(url, parameter,extra,callback,host);
        if (MyApplication.netWorkUnavailable) {
            failure(new StringBuilder("连接不到到网络"));
            return;
        }

        MultipartBody.Builder builder=new MultipartBody.Builder();
        if (extra!=null){
            for (String key:extra.keySet()) {
                builder.addFormDataPart(key,extra.get(key));
            }
        }

        RequestBody body=builder.build();
        Request request=new Request.Builder().post(body).url(OKHttpHelper.getUrl(url, parameter, host)).build();

        OkHttpImmu.Instance().newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (Debug) L.e(call.request().toString());
                failure(new StringBuilder("抱歉，我们没能完成您的请求"));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (Debug) {
                    L.e(response.request().toString());
                    L.e(response.toString());
                }
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    String result = response.body().string();
                    success(new StringBuilder(result));
                } else {
                    failure(new StringBuilder(response.code()));
                }
            }
        });

    }

    @Override
    public void get(String url, Map<String, String> parameter, HttpCallBack callback, String... host) {
        super.get(url, parameter,callback,host);
        if (MyApplication.netWorkUnavailable) {
            failure(new StringBuilder("连接不到到网络"));
            return;
        }
        Request.Builder builder = new Request.Builder().url(OKHttpHelper.getUrl(url, parameter, host));
        if (Debug) L.e(OKHttpHelper.getUrl(url, parameter, host).toString());
        Request request = builder.build();
        OkHttpImmu.Instance().newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (Debug) L.e(call.request().toString());
                failure(new StringBuilder("抱歉，我们没能完成您的请求"));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (Debug) {
                    L.e(response.request().toString());
                    L.e(response.toString());
                }
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    String result = response.body().string();
                    success(new StringBuilder(result));
                } else {
                    failure(new StringBuilder(response.code()));
                }
            }
        });
    }
}
