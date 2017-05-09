package com.syhelper.net;


import com.syhelper.api.ApiConfig;

import java.util.Map;

import okhttp3.HttpUrl;

/**
 * Created by LGL on 2016/10/26.
 */

public class OKHttpHelper {
    //    HttpUrl httpUrl=new HttpUrl.Builder().scheme("http").host(ConfigAPI.HOST).addPathSegments("/tyre-wechat/api/login").addQueryParameter("username","tom").addQueryParameter("password","123456").build();
    public static HttpUrl getUrl(String pathSegments, Map<String, String> ParameterMap) {
//        HttpUrl.Builder builder = new HttpUrl.Builder();
//        builder.scheme("http").host(ApiConfig.IP).addPathSegment("tyre-wechat").addPathSegment("api");

        HttpUrl.Builder builder = new HttpUrl.Builder();

        builder.scheme("http").host(ApiConfig.IP).port(ApiConfig.Port);//.addPathSegment("tyre-wechat").addPathSegment("api")


        builder.addPathSegment(ApiConfig.basePath);

        String[] path_Segment = pathSegments.split("/");
        for (String path : path_Segment) {
            builder.addPathSegment(path);
        }
        if (ParameterMap != null) {
            for (String key : ParameterMap.keySet()) {
                builder.addQueryParameter(key, ParameterMap.get(key));
            }
        }
        return builder.build();
    }

    /**
     * @param pathSegments 路径深度
     * @param ParameterMap URL后接参数
     * @param host         没有就是默认的Host
     * @return
     */
    public static HttpUrl getUrl(String pathSegments, Map<String, String> ParameterMap, String... host) {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        if (host.length > 0) {
            builder.scheme("http").host(host[0]).port(Integer.parseInt(host[1]));
        } else {
            builder.scheme("http").host(ApiConfig.IP).port(ApiConfig.Port);//.addPathSegment("tyre-wechat").addPathSegment("api")
        }

        builder.addPathSegment(ApiConfig.basePath);

        String[] path_Segment = pathSegments.split("/");
        for (String path : path_Segment) {
            builder.addPathSegment(path);
        }
        if (ParameterMap != null) {
            for (String key : ParameterMap.keySet()) {
                builder.addQueryParameter(key, ParameterMap.get(key));
            }
        }
        return builder.build();
    }
}
