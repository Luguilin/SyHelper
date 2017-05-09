package com.syhelper.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by LGL on 2017/3/24.
 */

public class MyGalleryViewPager extends ViewPager{

    public MyGalleryViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public MyGalleryViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        requestDisallowInterceptTouchEvent(true);
        super.onTouchEvent(ev);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        requestDisallowInterceptTouchEvent(true);
        super.dispatchTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        requestDisallowInterceptTouchEvent(true);
        super.onInterceptTouchEvent(ev);
        return true;
    }
}
