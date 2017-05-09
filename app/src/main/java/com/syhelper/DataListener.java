package com.syhelper;

import java.util.List;

/**
 * Created by LGL on 2017/1/5.
 */

public abstract class DataListener<T> implements IDataListener<T> {

    @Override
    public void attach(List<T> objects) {

    }

    @Override
    public void attach(T object) {

    }
}
