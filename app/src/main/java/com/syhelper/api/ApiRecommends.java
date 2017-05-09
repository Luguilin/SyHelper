package com.syhelper.api;

import com.google.gson.Gson;
import com.syhelper.IDataListener;
import com.syhelper.httpBean.RecommendResponse;
import com.syhelper.net.HttpCallBack;
import com.syhelper.net.HttpFactory;
import com.syhelper.tool.StringHelper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by LGL on 2017/5/3.
 */

public class ApiRecommends {

    public void fetch(int startIndex, int count, final IDataListener<RecommendResponse> iDataListener) {
        Map<String, String> parameter = new LinkedHashMap<>();
        parameter.put("startIndex", "" + startIndex);
        parameter.put("count", "" + count);
        HttpFactory.buildClient(HttpFactory.Post).post(ApiConfig.getRecommends, null, parameter, new HttpCallBack() {
            @Override
            public void success(StringBuilder response) {
                if (StringHelper.isEmpty(response.toString())) {
                    iDataListener.failure("没有更多信息");
                    return;
                }
                RecommendResponse recommendResponse = new Gson().fromJson(response.toString(), RecommendResponse.class);
                if (recommendResponse == null) {
                    failure(new StringBuilder("解析数据失败"));
                    return;
                }
                iDataListener.attach(recommendResponse);
            }

            @Override
            public void failure(StringBuilder msg) {
                iDataListener.failure(msg.toString());
            }
        });
    }
}
