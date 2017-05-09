package com.syhelper.net;


import okhttp3.OkHttpClient;

/**
 * Created by LGL on 2016/11/15.
 */

public class OkHttpImmu {
    private static OkHttpClient client = null;

    public static OkHttpClient Instance() {
        if (client == null) {
            synchronized (OkHttpImmu.class) {
                if (client == null) {
                    client = new OkHttpClient();
                }
            }
            client = new OkHttpClient();
        }
        return client;
    }

    /**
     * Map<String,String> parameter=OkHttpImmu.getParameter();
     * @return
     */
//    public static Map<String, String> getParameter(String action) {
//        Map<String, String> parameter = new LinkedHashMap<>();
//        parameter.put(ApiConfig.action,action);
//        return parameter;
//    }

}
