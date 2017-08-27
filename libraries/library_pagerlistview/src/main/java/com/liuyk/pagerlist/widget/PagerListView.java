package com.liuyk.pagerlist.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.liuyk.pagerlist.R;

/**
 * 分页ListView
 * <p>
 * @author Liuyak
 */
public class PagerListView extends BasePageListView {

    public PagerListView(Context context) {
        super(context);
    }

    public PagerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PagerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected View getLoadingFailView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.pager_loading_fail_layout, this, false);
    }

    @Override
    protected View getLoadingView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.pager_loading_layout, this, false);
    }

    protected View getEmptilyContentView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.pager_empty_content_view, this, false);
    }
}
