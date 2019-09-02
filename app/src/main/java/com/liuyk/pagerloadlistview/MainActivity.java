package com.liuyk.pagerloadlistview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.liuyk.pagerlist.widget.BasePageListView;
import com.liuyk.pagerlist.widget.PagerListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BasePageListView.OnPageListener {
    private PagerListView mListView;
    private DataAdapter mAdapter;
    private int mPagerNo = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mListView = findViewById(R.id.lit_view);
        mListView.setEmptyText("Empty Data");
        mListView.setLoadingText("Loading Data");
        mListView.setErrorText("Error Data");
        mAdapter = new DataAdapter(this);
        mAdapter.setItems(getData());
        mListView.setAdapter(mAdapter);
        mListView.setOnPageListener(this);
    }

    private List<Data> getData() {
        List<Data> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Data data = new Data();
            data.setTitle("标题" + i);
            items.add(data);
        }
        return items;
    }

    @Override
    public void onLoadMoreItems(int pageNo) {
        if (mPagerNo > pageNo) {
            mListView.setState(BasePageListView.LoadState.STATE_LOADING);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mListView.setState(BasePageListView.LoadState.STATE_IDLE);
                    mAdapter.addMore(getData());
                    mAdapter.notifyDataSetChanged();
                }
            }, 1000);
        } else {
            mListView.setState(BasePageListView.LoadState.STATE_FINISH);
        }
    }

}
