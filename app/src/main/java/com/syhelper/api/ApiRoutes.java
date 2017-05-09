package com.syhelper.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.syhelper.IDataListener;
import com.syhelper.bean.Resource;
import com.syhelper.bean.Route;
import com.syhelper.httpBean.RoutesResponse;
import com.syhelper.net.HttpCallBack;
import com.syhelper.net.HttpFactory;
import com.syhelper.tool.StringHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LGL on 2017/3/29.
 */

public class ApiRoutes {
    public void getRoutes(int startIndex, int count, final IDataListener<RoutesResponse> iDataListener) {

        Map<String, String> parameter = new LinkedHashMap<>();
        parameter.put("startIndex", startIndex + "");
        parameter.put("count", count + "");

        HttpFactory.buildClient(HttpFactory.Post).post(ApiConfig.getRoutes, null, parameter, new HttpCallBack() {
            @Override
            public void success(StringBuilder response) {

                if (StringHelper.isEmpty(response.toString())) {
                    iDataListener.failure("没有更多信息");
                    return;
                }


                RoutesResponse routesResponse = null;
                try {
                    routesResponse = analyze(response.toString());
                } catch (JSONException e) {
                    routesResponse = null;
                }

                if (routesResponse == null) {
                    iDataListener.failure("数据解析异常");
                    return;
                }

                iDataListener.attach(routesResponse);
            }

            @Override
            public void failure(StringBuilder msg) {
                iDataListener.failure(msg.toString());
            }
        });
    }

    private RoutesResponse analyze(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);

        RoutesResponse routesResponse = new RoutesResponse();

        routesResponse.setNowIndex(jsonObject.optInt("nowIndex", 0));
        routesResponse.setAmount(jsonObject.optInt("amount", 0));
        routesResponse.setSumPage(jsonObject.optInt("sumPage", 0));
        routesResponse.setNowPage(jsonObject.optInt("nowPage", 0));

        Map<String, Route> routes = new LinkedHashMap<>();

        JSONObject routes_obj = jsonObject.optJSONObject("routes");
        if (routes_obj != null) {
            Iterator<String> iterator = routes_obj.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                if (StringHelper.isEmpty(key)) continue;

                JSONObject routes_number = routes_obj.optJSONObject(key);
                Route route = new Gson().fromJson(routes_number.toString(), Route.class);
                routes.put(key, route);
            }
        }
        routesResponse.setRoutes(routes);

        JSONArray jsonArray_data = jsonObject.optJSONArray("data");

        List<Resource> data = null;
        if (jsonArray_data != null) {
            data = new Gson().fromJson(jsonArray_data.toString(), new TypeToken<List<Resource>>() {
            }.getType());
        }


        //处理数据
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                String rescontrn = data.get(i).getRescontrnId() + "";
                if (routes.keySet().contains(rescontrn)) {
                    routes.get(rescontrn).setResource(data.get(i));
                }
            }
            routesResponse.setData(data);//多余的设置
        }
        return routesResponse;
    }
}
