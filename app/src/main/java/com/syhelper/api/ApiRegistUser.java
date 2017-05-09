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
 * 用户注册接口
 */

public class ApiRegistUser {

    /**
     *
     * @param userMobileNum 手机号码
     * @param email 邮箱
     * @param userPwd
     * @param userName
     * @param iDataListener 返回 false 注册失败 true 注册成功
     */
    public void RegistUser(String userMobileNum,String email,String userPwd,String userName
            , final IDataListener<Boolean> iDataListener) {
        Map<String, String> parameter = new LinkedHashMap<>();
        parameter.put("userMobileNum", userMobileNum);
        parameter.put("Email", email);
        parameter.put("userPwd", userPwd);
        HttpFactory.buildClient(HttpFactory.Post).post(ApiConfig.registUser, null, parameter, new HttpCallBack() {
            @Override
            public void success(StringBuilder response) {
                if (StringHelper.isEmpty(response.toString())) {
                    failure(null);
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    boolean isSuccess = jsonObject.optBoolean("isSuccess", false);
                    iDataListener.attach(isSuccess);
                } catch (JSONException e) {
                    failure(null);
                }
            }

            @Override
            public void failure(StringBuilder msg) {
                iDataListener.failure("抱歉，数据解析失败");
                iDataListener.attach(false);
            }
        });
    }

}
