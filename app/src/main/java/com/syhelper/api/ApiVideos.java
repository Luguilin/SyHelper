package com.syhelper.api;

import com.google.gson.Gson;
import com.syhelper.IDataListener;
import com.syhelper.httpBean.VideosResponse;
import com.syhelper.net.HttpCallBack;
import com.syhelper.net.HttpFactory;
import com.syhelper.tool.StringHelper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by LGL on 2017/4/7.
 */

public class ApiVideos {
    public void getVideos(int startIndex, int count, int viceoTypeId, final IDataListener<VideosResponse> iDataListener) {
        Map<String, String> parameter = new LinkedHashMap<>();
        parameter.put("startIndex", startIndex + "");
        parameter.put("count", count + "");
        parameter.put("viceoTypeId", viceoTypeId + "");

        HttpFactory.buildClient(HttpFactory.Post).post(ApiConfig.getVideos, null, parameter, new HttpCallBack() {
            @Override
            public void success(StringBuilder response) {
                if (StringHelper.isEmpty(response.toString())) {
                    iDataListener.failure("没有更多信息");
                    return;
                }

                VideosResponse videosResponse = new Gson().fromJson(response.toString(), VideosResponse.class);

                if (videosResponse == null) {
                    iDataListener.failure("数据解析异常");
                    return;
                }
                iDataListener.attach(videosResponse);
            }

            @Override
            public void failure(StringBuilder msg) {
                iDataListener.failure(msg.toString());
            }
        });
    }
}
