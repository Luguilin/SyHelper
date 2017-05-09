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
 */

public class ApiGetPhotoByMobileNum {
    /**
     * @param userMobileNum 手机号码
     * @param iDataListener 如果有头像将返回，没有将不返回
     */
    public void GetPhotoByMobileNum(String userMobileNum, final IDataListener<String> iDataListener) {
        Map<String, String> parameter = new LinkedHashMap<>();
        parameter.put("userMobileNum", userMobileNum);
        HttpFactory.buildClient(HttpFactory.Post).post(ApiConfig.loginGetPhotoByMobileNum, null, parameter, new HttpCallBack() {
            @Override
            public void success(StringBuilder response) {//{"userPhoto":"/syhelp/images/route/1485277968347441828.jpeg","userId":46}
                //{"errMes":"用户不存在","userPhoto":"","errCode":304,"userId":"0"}
//                {"errMes":"用户还没有上传头像","userPhoto":"","errCode":309,"userId":71}
                if (StringHelper.isEmpty(response.toString())) {
                    iDataListener.failure("没有更多信息");
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    iDataListener.attach(jsonObject.optString("userPhoto", ""));
                } catch (JSONException e) {
                    failure(new StringBuilder("数据解析失败"));
                }
            }

            @Override
            public void failure(StringBuilder msg) {
                iDataListener.failure(msg.toString());
            }
        });
    }
}
