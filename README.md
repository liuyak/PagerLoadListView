# 用法
1. 在APP目录下build.gradle下添加

implementation 'com.liuyk.pagerlist:pagerlistview:1.0.0'

2. 在工程根目录build.gradle目录下添加

maven {

      url "https://raw.githubusercontent.com/liuyak/PagerListViewLibrary/master"
 
 }

# 案例代码

public class MainActivity extends AppCompatActivity implements BasePageListView.OnPageListener {
    
    private PagerListView mListView;
    private DataAdapter mAdapter;
    private int mPagerNo = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mListView = (PagerListView) findViewById(R.id.lit_view);
        mAdapter = new DataAdapter(this);
        mAdapter.setItems(getData());
        mListView.setAdapter(mAdapter);
        mListView.setOnPageListener(this);
    }

    private List<Data> getData(){
        List<Data> items = new ArrayList<>();
        for(int i=0; i<10; i++){
            Data data = new Data();
            data.setTitle("标题" + i);
            items.add(data);
        }
        return items;
    }

    @Override
    public void onLoadMoreItems(int pageNo) {
        if(mPagerNo > pageNo){
            mListView.setState(BasePageListView.LoadState.STATE_LOADING);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mListView.setState(BasePageListView.LoadState.STATE_IDLE);
                    mAdapter.addMore(getData());
                    mAdapter.notifyDataSetChanged();
                }
            }, 1000);
        }
        else{
            mListView.setState(BasePageListView.LoadState.STATE_FINISH);
        }
    }


<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"

    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.liuyk.pagerlist.widget.PagerListView
        android:id="@+id/lit_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:dividerHeight="0.1px"
        android:paddingLeft="20dp"
        android:paddingRight="20dp" />

</LinearLayout>


# PagerLoadListView
分页加载ListView
![image](Screenshots.gif)
