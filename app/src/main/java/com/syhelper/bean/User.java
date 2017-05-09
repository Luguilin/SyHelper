package com.syhelper.bean;

/**
 * Created by LGL on 2017/3/22.
 */

public class User {

    /**
     * userId : 1
     * userName : 小乐
     * isSuccess : true
     * hashOtherUser : 0
     */

    private String userId;
    private String userName;
    private String isSuccess;
    private int hashOtherUser;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getHashOtherUser() {
        return hashOtherUser;
    }

    public void setHashOtherUser(int hashOtherUser) {
        this.hashOtherUser = hashOtherUser;
    }
}
