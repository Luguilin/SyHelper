package com.syhelper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by LGL on 2017/5/1.
 */

public class RefreshListView extends ListView implements AbsListView.OnScrollListener {

    public RefreshListView(Context context) {
        this(context, null);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && lastItemVisible) {
            canLoadMore = true;
        } else if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && fastItemVisible) {
            canRefresh = true;
        } else {
            canRefresh = false;
            canLoadMore = false;
        }
    }

    boolean canRefresh = false;
    boolean canLoadMore = false;

    public boolean isCanRefresh() {
        return canRefresh;
    }

    public boolean isCanLoadMore() {
        return canLoadMore;
    }

    boolean lastItemVisible = false;
    boolean fastItemVisible = false;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItemVisible = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount - 1);
        fastItemVisible = (totalItemCount > 0) && firstVisibleItem == 0 && getScaleY() <= 2;
    }
}
