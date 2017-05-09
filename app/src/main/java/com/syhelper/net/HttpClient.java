package com.syhelper.net;

import java.util.Map;

public interface HttpClient {
    /**
     * 发送Post请求
     * @param pathSegment   资源路径   URL的路径   用  /   分割
     * @param parameter   key-values参数Map
     * @param callback   回调
     * @param extra keu-values请求体参数，如果是File请直接Map中放绝对路径
     * @param hostAndPort  主机地址和端口号
     */
    void post(String pathSegment, Map<String, String> parameter, Map<String, String> extra
            , HttpCallBack callback, String... hostAndPort);

    /**
     * 发送Get请求
     * @param pathSegment   资源路径   URL的路径   用  /   分割
     * @param parameter   key-values参数Map
     * @param callback   回调
     * @param hostAndPort  主机地址和端口号
     */
    void get(String pathSegment, Map<String, String> parameter, HttpCallBack callback
            , String... hostAndPort);
}
