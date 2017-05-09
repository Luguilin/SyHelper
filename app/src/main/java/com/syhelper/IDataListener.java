package com.syhelper;

import java.util.List;

/**
 * Created by LGL on 2017/1/3.
 */

public interface IDataListener<T> {
    void attach(List<T> objects);
    void attach(T object);
    void failure(String msg);
}
