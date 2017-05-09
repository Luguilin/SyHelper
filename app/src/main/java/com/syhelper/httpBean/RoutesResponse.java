package com.syhelper.httpBean;

import com.syhelper.bean.Resource;
import com.syhelper.bean.Route;

import java.util.List;
import java.util.Map;

/**
 * Created by LGL on 2017/3/29.
 */

public class RoutesResponse {

    int amount;//数据总数
    Map<String, Route> routes;
    List<Resource> data;
    int nowIndex;//当前数据中最后一条记录的下标（注：获取新数据可以通过该属性，配合count来获取之后的数据）
    int sumPage;//总页数，不需要分页的话请不要使用
    int nowPage;//当前页数，不需要分页的话请不要使用

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Map<String, Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Map<String, Route> routes) {
        this.routes = routes;
    }

    public List<Resource> getData() {
        return data;
    }

    public void setData(List<Resource> data) {
        this.data = data;
    }

    public int getNowIndex() {
        return nowIndex;
    }

    public void setNowIndex(int nowIndex) {
        this.nowIndex = nowIndex;
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }
}
