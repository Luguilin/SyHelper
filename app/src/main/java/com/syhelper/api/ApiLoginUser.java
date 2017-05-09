package com.syhelper.api;

import com.google.gson.Gson;
import com.syhelper.IDataListener;
import com.syhelper.httpBean.UserResponse;
import com.syhelper.net.HttpCallBack;
import com.syhelper.net.HttpFactory;
import com.syhelper.tool.StringHelper;
import com.syhelper.tool.SystemTool;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by LGL on 2017/3/29.
 */

public class ApiLoginUser {

    public void LoginUser(String phoneNumber, String pwd, final IDataListener<UserResponse> iDataListener) {
        Map<String, String> parameter = new LinkedHashMap<>();
        parameter.put("userMobileNum", phoneNumber);
        parameter.put("userPwd", pwd);
        parameter.put("uuId", SystemTool.getDeviceId());
        HttpFactory.buildClient(HttpFactory.Post).post(ApiConfig.loginUser, null, parameter, new HttpCallBack() {
            @Override
            public void success(StringBuilder response) {
                if (StringHelper.isEmpty(response.toString())) {
                    iDataListener.failure("");
                    return;
                }
                UserResponse userResponse = new Gson().fromJson(response.toString(), UserResponse.class);
                if (userResponse==null||!userResponse.isSuccess()) {
                    iDataListener.failure(userResponse.getErrMes());
                    return;
                }
                iDataListener.attach(userResponse);
            }

            @Override
            public void failure(StringBuilder msg) {
                iDataListener.failure(msg.toString());
            }
        });
    }
}
