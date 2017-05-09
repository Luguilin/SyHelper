package com.syhelper.bean;

/**
 * Created by LGL on 2017/4/7.
 */

public class Video {

    /**
     * id : 45
     * videoURL : /syhelp/file/video/video_1b78mnf3rnc01fjf107t186h12050.mp4
     * videoImageURL : /syhelp/file/video/tmp_video_1b78mnf3rnc01fjf107t186h12050.jpg
     * explanation : 康佳相机使用规范康佳相机使用规相机使用规范
     * videoTypeId : 1
     * videoTypeName : 教学视频
     */

    private int id;
    private String videoURL;
    private String videoImageURL;
    private String explanation;
    private int videoTypeId;
    private String videoTypeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getVideoImageURL() {
        return videoImageURL;
    }

    public void setVideoImageURL(String videoImageURL) {
        this.videoImageURL = videoImageURL;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getVideoTypeId() {
        return videoTypeId;
    }

    public void setVideoTypeId(int videoTypeId) {
        this.videoTypeId = videoTypeId;
    }

    public String getVideoTypeName() {
        return videoTypeName;
    }

    public void setVideoTypeName(String videoTypeName) {
        this.videoTypeName = videoTypeName;
    }
}
