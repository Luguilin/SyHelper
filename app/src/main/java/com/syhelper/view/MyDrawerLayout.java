package com.syhelper.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

/**
 * Created by LGL on 2017/3/29.
 */

public class MyDrawerLayout extends DrawerLayout {
    public MyDrawerLayout(Context context) {
        super(context);
    }

    public MyDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isDrawerOpen(Gravity.LEFT)) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

//    @Override
//    public boolean onInterceptHoverEvent(MotionEvent event) {
//        if (isDrawerOpen(Gravity.LEFT)) {
//            return super.onInterceptHoverEvent(event);
//        } else {
//            return false;
//        }
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isDrawerOpen(Gravity.LEFT)) {
            return super.dispatchTouchEvent(ev);
        } else {
            return false;
        }
    }
}
