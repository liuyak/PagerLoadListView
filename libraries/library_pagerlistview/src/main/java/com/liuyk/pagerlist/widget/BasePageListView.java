package com.liuyk.pagerlist.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * 分页加载ListView
 * <p/>
 * @author Liuyak
 */
public abstract class BasePageListView extends ListView implements AbsListView.OnScrollListener, View.OnClickListener {

    /**
     * 当前的状态
     */
    public enum LoadState {
        STATE_LOADING,
        STATE_LOAD_FAIL,
        STATE_IDLE,
        STATE_FINISH
    }

    private OnPageListener onPageListener;
    private OnScrollListener mOnScrollListener;
    private View loadingView;
    private View loadingFailView;
    private View loadEmptilyContentView;

    /**
     * 页数，从1开始计算
     */
    private int pageNo;

    /**
     * 当前状态
     */
    private LoadState state;

    public BasePageListView(Context context) {
        this(context, null);
    }

    public BasePageListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasePageListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnPageListener(OnPageListener onPageListener) {
        this.onPageListener = onPageListener;
    }

    private void init() {
        loadingView = getLoadingView();
        loadingFailView = getLoadingFailView();
        loadEmptilyContentView = getEmptilyContentView();
        loadingFailView.setOnClickListener(this);

        final LinearLayout footerViewWrapper = new LinearLayout(getContext());
        footerViewWrapper.setOrientation(LinearLayout.VERTICAL);
        footerViewWrapper.addView(loadingView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        footerViewWrapper.addView(loadingFailView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        footerViewWrapper.addView(loadEmptilyContentView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        addFooterView(footerViewWrapper);
        reset();
        super.setOnScrollListener(this);
    }

    protected abstract View getLoadingFailView();

    protected abstract View getLoadingView();

    protected abstract View getEmptilyContentView();

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mOnScrollListener != null) {
            mOnScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
        //判断是否滑到了底部
        final int count = firstVisibleItem + visibleItemCount;
        if (state == LoadState.STATE_IDLE && count == totalItemCount) {
            if (onPageListener != null) {
                pageNo++;
                onPageListener.onLoadMoreItems(pageNo);
            }
        }
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mOnScrollListener = l;
    }

    /**
     * 重置
     */
    public void reset() {
        pageNo = 1;
        setState(LoadState.STATE_IDLE);
    }

    /**
     * 获取当前的状态
     *
     * @return
     */
    public LoadState getState() {
        return state;
    }

    public void setState(LoadState state) {
        this.state = state;
        switch (state) {
            case STATE_IDLE:
                //闲置状态
                idleState();
                break;

            case STATE_LOADING:
                //正在载入的状态
                loadingState();
                break;

            case STATE_LOAD_FAIL:
                //加载失败的状态
                failState();
                break;

            case STATE_FINISH:
                //无更多内容的状态
                finishState();
                break;
        }
    }

    private void finishState(){
        loadingView.setVisibility(GONE);
        loadingFailView.setVisibility(GONE);
        loadEmptilyContentView.setVisibility(VISIBLE);
    }

    private void failState(){
        loadingFailView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        loadEmptilyContentView.setVisibility(GONE);
    }

    private void loadingState(){
        loadingView.setVisibility(VISIBLE);
        loadingFailView.setVisibility(GONE);
        loadEmptilyContentView.setVisibility(GONE);
    }

    private void idleState(){
        loadingView.setVisibility(GONE);
        loadingFailView.setVisibility(GONE);
        loadEmptilyContentView.setVisibility(GONE);
    }

    @Override
    public void onClick(View v) {
        if (v == loadingFailView) {
            if (onPageListener != null) {
                onPageListener.onLoadMoreItems(pageNo);
            }
        }
    }

    public interface OnPageListener {
        void onLoadMoreItems(int pageNo);
    }

}
