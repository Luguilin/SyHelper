package com.syhelper.api;

import com.syhelper.IDataListener;
import com.syhelper.net.HttpCallBack;
import com.syhelper.net.HttpFactory;
import com.syhelper.tool.StringHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by LGL on 2017/4/24.
 * 注册时检查用户是否存在的接口
 */

public class ApiCheckUserIsVaild {
    /**
     *
     * @param userMobileNum 手机号码
     * @param iDataListener false 是没有注册的手机号码
     */
    public void CheckUserIsVaild(String userMobileNum, final IDataListener<Boolean> iDataListener) {
        Map<String, String> parameter = new LinkedHashMap<>();
        parameter.put("userMobileNum", userMobileNum + "");
        HttpFactory.buildClient(HttpFactory.Post).post(ApiConfig.checkUserIsVaild, null, parameter, new HttpCallBack() {
            @Override
            public void success(StringBuilder response) {
                if (StringHelper.isEmpty(response.toString())) {
                    failure(null);
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    boolean isExist = jsonObject.optBoolean("isExist", false);
                    iDataListener.attach(isExist);
                } catch (JSONException e) {
                    failure(null);
                }
            }

            @Override
            public void failure(StringBuilder msg) {
                iDataListener.failure("抱歉，我们未能完成您的请求");
                iDataListener.attach(false);
            }
        });
    }
}
