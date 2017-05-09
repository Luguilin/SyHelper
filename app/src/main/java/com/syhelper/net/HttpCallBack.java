package com.syhelper.net;

/**
 * Created by LGL on 2016/11/14.
 * 请求数据的辅助响应
 */

public interface HttpCallBack {
    void success(StringBuilder response);
    void failure(StringBuilder msg);
}
