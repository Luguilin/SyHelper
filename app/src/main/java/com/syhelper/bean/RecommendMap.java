package com.syhelper.bean;

import com.syhelper.ResType;

import java.util.List;

/**
 * Created by LGL on 2017/5/9.
 */

public class RecommendMap {

    ResType type;

    Route route;

    Video video;

    MasterShow masterShow;

    List<ShowImage> showImages;

    Recommend recommend;


    public MasterShow getMasterShow() {
        return masterShow;
    }

    public void setMasterShow(MasterShow masterShow) {
        this.masterShow = masterShow;
    }

    public Recommend getRecommend() {
        return recommend;
    }

    public void setRecommend(Recommend recommend) {
        this.recommend = recommend;
    }

    public ResType getType() {
        return type;
    }

    public void setType(ResType type) {
        this.type = type;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public List<ShowImage> getShowImages() {
        return showImages;
    }

    public void setShowImages(List<ShowImage> showImages) {
        this.showImages = showImages;
    }
}
