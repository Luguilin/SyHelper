package com.syhelper.api;

/**
 * Created by LGL on 2017/3/23.
 */

public class ApiConfig {

    public static final String IP = "112.74.49.238";
    public static final int Port = 8081;

    public static final String basePath = "syhelp";
    public static final String imageBaseUrl = "http://112.74.49.238:8081";



    /**
     * 注册时检查用户是否存在
     */
    public static final String checkUserIsVaild = "mobile/user/checkUserIsVaild";

    /**
     * 用户注册接口
     */
    public static final String registUser = "mobile/user/registUser";

    /**
     * 用户登录接口
     */
    public static final String loginUser = "mobile/user/loginUser";

    /**
     * 注册时检查用户是否存在
     */
    public static final String isLogin = "mobile/user/isLogin";

    /**
     * 根据手机号获取账号头像接口
     */
    public static final String loginGetPhotoByMobileNum = "mobile/user/loginGetPhotoByMobileNum";

    /**
     * 测试服务器是否连接 接口
     */
    public static final String testConnection = "mobile/testConnection";

    /**
     * 名家
     */
    public static final String getMasters = "mobile/master/getMasters";
    /**
     * 推荐接口
     */
    public static final String getRecommends = "recommend/mobile/getRecommends";

    /**
     * 加载路线资源
     */
    public static final String getRoutes = "mobile/route/getRoutes";

    /**
     * 4.2路线资源详情
     */
    public static final String intoRouteInfo = "mobile/route/intoRouteInfo";

    /**
     * 4.关注接口说明
     */
    public static final String addCollection = "collection/addCollection";

    /**
     * 5.2取消关注
     */
    public static final String deleteCollection = "collection/deleteCollection";

    /**
     * 6.评论接口说明
     */
    public static final String addConment = "conment/addConment";

    /**
     * 6.2获取评论
     */
    public static final String getConmentsByResourceId = "mobile/getConmentsByResourceId";

    /**
     * 7.视频模块接口
     */
    public static final String getVideos = "mobile/video/getVideos";

    /**
     * 获取视频详情数据
     */
    public static final String initVideoInfo = "mobile/video/initVideoInfo";


}
