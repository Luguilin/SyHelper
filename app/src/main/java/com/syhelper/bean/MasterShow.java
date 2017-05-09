package com.syhelper.bean;

/**
 * Created by LGL on 2017/5/9.
 */

public class MasterShow {

    /**
     * id : 1
     * provinceId : 1
     * provinceName : 北京市
     * masterPhoto : /syhelp/images/master/photo/1492937453647484689.png
     * masterPhotoThumbnail : data:image/jpeg;base64,/9j/4AAE//Z
     * masterName : 何乐聪
     * masterExplanation : <p>这是一个有名的人</p>
     * userId : 69
     * workCount : 0
     * state : 1
     */

    private int id;
    private int provinceId;
    private String provinceName;
    private String masterPhoto;
    private String masterPhotoThumbnail;
    private String masterName;
    private String masterExplanation;
    private int userId;
    private int workCount;
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getMasterPhoto() {
        return masterPhoto;
    }

    public void setMasterPhoto(String masterPhoto) {
        this.masterPhoto = masterPhoto;
    }

    public String getMasterPhotoThumbnail() {
        return masterPhotoThumbnail;
    }

    public void setMasterPhotoThumbnail(String masterPhotoThumbnail) {
        this.masterPhotoThumbnail = masterPhotoThumbnail;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterExplanation() {
        return masterExplanation;
    }

    public void setMasterExplanation(String masterExplanation) {
        this.masterExplanation = masterExplanation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWorkCount() {
        return workCount;
    }

    public void setWorkCount(int workCount) {
        this.workCount = workCount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
