package com.liuyk.pagerloadlistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * adapter
 * <p>
 * @author Liuyak
 */
public class DataAdapter extends AppBaseAdapter<Data> {

    public DataAdapter(Context context) {
        super(context);
    }

    public void addMore(List<Data> items){
        mItems.addAll(items);
    }

    @Override
    protected int getResources(int position) {
        return R.layout.list_item;
    }

    @Override
    protected View appGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if(holder == null){
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        Data data = getData(position);
        holder.title.setText(data.getTitle());
        return convertView;
    }

    private class ViewHolder{
        private TextView title;

        ViewHolder(View convertView){
            title = (TextView) convertView.findViewById(R.id.data_title);
        }
    }
}
