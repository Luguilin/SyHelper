package com.syhelper.httpBean;

import com.syhelper.bean.Video;
import com.syhelper.bean.VideoItem;

/**
 * Created by LGL on 2017/4/7.
 */

public class VideoInfo {

    boolean result;
    Video video;
    VideoItem resource;

    public boolean isResult() {
        return result;
    }

    public Video getVideo() {
        return video;
    }

    public VideoItem getResource() {
        return resource;
    }
}
