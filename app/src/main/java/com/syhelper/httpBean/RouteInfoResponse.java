package com.syhelper.httpBean;

import com.syhelper.bean.Resource;
import com.syhelper.bean.Route;

/**
 * Created by LGL on 2017/4/10.
 */

public class RouteInfoResponse {

    boolean result;
    Route route;
    Resource resource;

    public boolean isResult() {
        return result;
    }

    public Route getRoute() {
        return route;
    }

    public Resource getResource() {
        return resource;
    }
}
