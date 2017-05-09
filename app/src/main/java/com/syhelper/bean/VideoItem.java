package com.syhelper.bean;

/**
 * Created by LGL on 2017/4/7.
 */

public class VideoItem {

    /**
     * id : 159  //傻瓜用这个当详情ID使用了
     * title : 西蓝花的多种做法
     * type : recVideo
     * rescontrnId : 46
     * userId : 2
     * uploaderName : 何乐聪
     * conmentCount : 1
     * likedCount : 3
     * skimedCount : 16
     * collectionCount : 0
     * uploadTime : 1485277305000
     * updateTime : 1485277305000
     * isSetTop : 0
     * isShowRec : 0
     * setTopTime : 1485277305000
     * showRecTime : 1485277305000
     * state : 1
     */

    private int id;
    private String title;
    private String type;
    private int rescontrnId;
    private int userId;
    private String uploaderName;
    private int conmentCount;
    private int likedCount;
    private int skimedCount;
    private int collectionCount;
    private long uploadTime;
    private long updateTime;
    private int isSetTop;
    private int isShowRec;
    private long setTopTime;
    private long showRecTime;
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRescontrnId() {
        return rescontrnId;
    }

    public void setRescontrnId(int rescontrnId) {
        this.rescontrnId = rescontrnId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    public int getConmentCount() {
        return conmentCount;
    }

    public void setConmentCount(int conmentCount) {
        this.conmentCount = conmentCount;
    }

    public int getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(int likedCount) {
        this.likedCount = likedCount;
    }

    public int getSkimedCount() {
        return skimedCount;
    }

    public void setSkimedCount(int skimedCount) {
        this.skimedCount = skimedCount;
    }

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsSetTop() {
        return isSetTop;
    }

    public void setIsSetTop(int isSetTop) {
        this.isSetTop = isSetTop;
    }

    public int getIsShowRec() {
        return isShowRec;
    }

    public void setIsShowRec(int isShowRec) {
        this.isShowRec = isShowRec;
    }

    public long getSetTopTime() {
        return setTopTime;
    }

    public void setSetTopTime(long setTopTime) {
        this.setTopTime = setTopTime;
    }

    public long getShowRecTime() {
        return showRecTime;
    }

    public void setShowRecTime(long showRecTime) {
        this.showRecTime = showRecTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
