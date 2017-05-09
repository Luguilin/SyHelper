package com.syhelper.httpBean;

import com.syhelper.bean.Video;
import com.syhelper.bean.VideoItem;

import java.util.List;
import java.util.Map;

/**
 * Created by LGL on 2017/4/7.
 */

public class VideosResponse {

    int nowIndex;
    int amount;
    int sumPage;
    List<VideoItem> data;
    Map<String, Video> videos;
    int nowPage;

    public int getNowIndex() {
        return nowIndex;
    }

    public int getAmount() {
        return amount;
    }

    public int getSumPage() {
        return sumPage;
    }

    public List<VideoItem> getData() {
        return data;
    }

    public Map<String, Video> getVideos() {
        return videos;
    }

    public int getNowPage() {
        return nowPage;
    }
}
