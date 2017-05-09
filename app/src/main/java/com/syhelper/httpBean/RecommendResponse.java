package com.syhelper.httpBean;

import com.syhelper.bean.MasterShow;
import com.syhelper.bean.Recommend;
import com.syhelper.bean.Route;
import com.syhelper.bean.ShowImage;
import com.syhelper.bean.Video;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by LGL on 2017/5/3.
 */

public class RecommendResponse {

    int nowIndex;
    int amount;
    int sumPage;
    List<Recommend> data;
    //我也不知道这里是啥
    Object masterWorks;

    JSONObject imageShows;
    Map<String,Video> videos;
    Map<String,List<ShowImage>> imageShowImages;
    Map<String,MasterShow> masterShows;
    Map<String,com.syhelper.bean.Route> routes;
    int nowPage;

    public int getNowIndex() {
        return nowIndex;
    }

    public int getNowPage() {
        return nowPage;
    }

    public List<Recommend> getData() {
        return data;
    }

    public Map<String,Video> getVideos() {
        return videos;
    }

    public Map<String, List<ShowImage>> getImageShowImages() {
        return imageShowImages;
    }

    public JSONObject getImageShows() {
        return imageShows;
    }

    public Map<String, MasterShow> getMasterShows() {
        return masterShows;
    }

    public Object getMasterWorks() {
        return masterWorks;
    }

    public Map<String, Route> getRoutes() {
        return routes;
    }
}
