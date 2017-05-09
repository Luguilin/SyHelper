package com.syhelper.api;

import com.google.gson.Gson;
import com.syhelper.IDataListener;
import com.syhelper.httpBean.RouteInfoResponse;
import com.syhelper.net.HttpCallBack;
import com.syhelper.net.HttpFactory;
import com.syhelper.tool.StringHelper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by LGL on 2017/4/10.
 * 路线详情接口
 */

public class ApiRouteInfo {

    public void getRouteDetail(int routeResultId, final IDataListener<RouteInfoResponse> iDataListener) {
        Map<String, String> parameter = new LinkedHashMap<>();
        parameter.put("resourceId", routeResultId + "");
        HttpFactory.buildClient(HttpFactory.Post).post(ApiConfig.intoRouteInfo, null, parameter, new HttpCallBack() {
            @Override
            public void success(StringBuilder response) {
                if (StringHelper.isEmpty(response.toString())) {
                    iDataListener.failure("没有更多信息");
                    return;
                }
                RouteInfoResponse routeInfoResponse = new Gson().fromJson(response.toString(), RouteInfoResponse.class);
                if (routeInfoResponse == null) {
                    iDataListener.failure("数据解析异常");
                    return;
                }
                iDataListener.attach(routeInfoResponse);
            }

            @Override
            public void failure(StringBuilder msg) {
                iDataListener.failure(msg.toString());
            }
        });
    }
}
