package com.syhelper.tool;


import android.content.Context;
import android.telephony.TelephonyManager;

;import com.syhelper.MyApplication;

/**
 * Created by LGL on 2017/3/29.
 */

public class SystemTool {

    /**
     * 获取设备ID
     * @return
     */
    public static String getDeviceId() {
        TelephonyManager TelephonyMgr = (TelephonyManager) MyApplication.getContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        String device = TelephonyMgr.getDeviceId();
        return device;
    }
}
