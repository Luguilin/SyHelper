package com.syhelper.api;

import com.google.gson.Gson;
import com.syhelper.IDataListener;
import com.syhelper.httpBean.VideoInfo;
import com.syhelper.net.HttpCallBack;
import com.syhelper.net.HttpFactory;
import com.syhelper.tool.L;
import com.syhelper.tool.StringHelper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by LGL on 2017/4/7.
 * 视频详情API
 */

public class ApiVideoInfo {

    public void getVideoInfo(int resourceId, final IDataListener<VideoInfo> iDataListener) {
        Map<String, String> parameter = new LinkedHashMap<>();
        parameter.put("resourceId", resourceId + "");
        HttpFactory.buildClient(HttpFactory.Post).post(ApiConfig.initVideoInfo, null, parameter, new HttpCallBack() {
                    @Override
                    public void success(StringBuilder response) {
                        L.e("success==="+response);
                        if (StringHelper.isEmpty(response.toString())) {
                            iDataListener.failure("没有更多信息");
                            return;
                        }
                        VideoInfo videoInfo = new Gson().fromJson(response.toString(), VideoInfo.class);

                        if (videoInfo == null) {
                            iDataListener.failure("数据解析异常");
                            return;
                        }
                        iDataListener.attach(videoInfo);
                    }

                    @Override
                    public void failure(StringBuilder msg) {
                        iDataListener.failure(msg.toString());
                    }
                }
        );
    }
}
