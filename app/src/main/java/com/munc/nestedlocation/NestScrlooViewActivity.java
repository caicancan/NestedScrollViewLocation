package com.munc.nestedlocation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NestScrlooViewActivity extends AppCompatActivity {

    private TabLayout tab;
    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_scrloo_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initData(1);
        initView();
    }
    /**
     * 刷新ScrollView的内容
     */
    private void setScrollViewContent() {
        //NestedScrollView下的LinearLayout
        LinearLayout layout = (LinearLayout) findViewById(R.id.ll_sc_content);
        layout.removeAllViews();
        for (int i = 0; i < mData.size(); i++) {
            View view = View.inflate(this, R.layout.item_layout, null);
            ((TextView) view.findViewById(R.id.tv_info)).setText(mData.get(i));
            //动态添加 子View
            layout.addView(view, i);
        }
    }
    private String[] titles={"1","2","3","4","5","6","7","8","9","10"};
    private void initView() {
        //添加tab
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        tab = (TabLayout) findViewById(R.id.tab);
        tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        tab.setTabTextColors(getResources().getColor(R.color.black),getResources().getColor(R.color.red));
        for (int i = 0; i <titles.length ; i++) {
            tab.addTab(tab.newTab().setText(titles[i]));
        }
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initData(tab.getPosition() + 1);
                setScrollViewContent();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setScrollViewContent();
//        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//            }
//        });

    }

    //加载右边的menu布局
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private List<String> mData;
    private void initData(int pager) {
        mData = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            mData.add("pager" + pager + " 第" + i + "个item");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
