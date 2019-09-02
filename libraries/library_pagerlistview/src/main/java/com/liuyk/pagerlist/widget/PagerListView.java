package com.liuyk.pagerlist.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.liuyk.pagerlist.R;

/**
 * 分页ListView
 * <p>
 *
 * @author Liuyak
 */
public class PagerListView extends BasePageListView {
    private TextView mEmptyText;
    private TextView mLoadingText;
    private TextView mErrorText;

    public PagerListView(Context context) {
        this(context, null);
    }

    public PagerListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mEmptyText = findViewById(R.id.tv_empty);
        mLoadingText = findViewById(R.id.tv_loading);
        mErrorText = findViewById(R.id.tv_error);
    }

    public void setEmptyText(String msg) {
        if (mEmptyText != null) {
            mEmptyText.setText(msg);
        }
    }

    public void setErrorText(String msg) {
        if (mErrorText != null) {
            mErrorText.setText(msg);
        }
    }

    public void setLoadingText(String msg) {
        if (mLoadingText != null) {
            mLoadingText.setText(msg);
        }
    }

    public TextView getEmptyText() {
        return mEmptyText;
    }

    public TextView getErrorText() {
        return mErrorText;
    }

    public TextView getLoadingText() {
        return mLoadingText;
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
