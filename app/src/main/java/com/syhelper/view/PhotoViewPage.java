package com.syhelper.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by LGL on 2017/4/20.
 */

public class PhotoViewPage extends ViewPager {

    public PhotoViewPage(Context context) {
        super(context);
    }

    public PhotoViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        try {
            return super.onInterceptTouchEvent(event);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
