package com.liuyk.pagerloadlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 基类adapter
 * <p>
 * @author liuyk
 */
public abstract class AppBaseAdapter<T> extends BaseAdapter {

    protected List<T> mItems;

    protected Context mContext;

    public AppBaseAdapter(Context context) {
        this.mContext = context;
        mItems = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setItems(List<T> items) {
        this.mItems = items;
    }

    public List<T> getItems() {
        return mItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(getResources(position), parent, false);
        }
        return appGetView(position, convertView, parent);
    }

    public T getData(int position) {
        return mItems.get(position);
    }

    protected abstract int getResources(int position);

    protected abstract View appGetView(int position, View convertView, ViewGroup parent);

}
