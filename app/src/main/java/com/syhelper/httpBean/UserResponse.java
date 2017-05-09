package com.syhelper.httpBean;

/**
 * Created by LGL on 2017/3/29.
 */

public class UserResponse {

    /**
     * hadOtherUser : 1
     * userName : Lu
     * userId : 72
     * isSuccess : true
     */

    private int hadOtherUser;
    private String userName;
    private String errMes;//错误信息
    private String errCode;//错误代码：详情见请看附件二错误代码表
    private int userId;
    private boolean isSuccess;

    public String getErrMes() {
        return errMes;
    }

    public String getErrCode() {
        return errCode;
    }

    public int getHadOtherUser() {
        return hadOtherUser;
    }

    public void setHadOtherUser(int hadOtherUser) {
        this.hadOtherUser = hadOtherUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
