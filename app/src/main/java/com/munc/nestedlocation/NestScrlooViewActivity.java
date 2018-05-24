package com.munc.nestedlocation;

import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NestScrlooViewActivity extends AppCompatActivity {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.mnestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_scrloo_view);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initData(1);
        initView();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        Snackbar.make(fab, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    /**
     * 刷新ScrollView的内容
     */
    @BindView(R.id.ll_sc_content)
    LinearLayout layout;
    private void setScrollViewContent() {
        //NestedScrollView下的LinearLayout
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
        tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        tab.setTabTextColors(getResources().getColor(R.color.black),getResources().getColor(R.color.red));
        for (int i = 0; i <titles.length ; i++) {
            tab.addTab(tab.newTab().setText(titles[i]));
        }
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setScrollViewContent();
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Toast.makeText(getApplicationContext(), "" + scrollY, Toast.LENGTH_SHORT).show();
                if (scrollY>0&&scrollY<getChildHeight(1)){
                    tab.setScrollPosition(0,0,true);
                }else if (scrollY>getChildHeight(1)&&scrollY<getChildHeight(2)){
                    tab.setScrollPosition(0,1,true);
                }else if (scrollY>getChildHeight(2)&&scrollY<getChildHeight(3)){
                    tab.setScrollPosition(0,2,true);
                }else if (scrollY>getChildHeight(3)&&scrollY<getChildHeight(4)){
                    tab.setScrollPosition(0,3,true);
                }else if (scrollY>getChildHeight(4)&&scrollY<getChildHeight(5)){
                    tab.setScrollPosition(0,4,true);
                }else if (scrollY>getChildHeight(5)&&scrollY<getChildHeight(6)){
                    tab.setScrollPosition(0,5,true);
                }else if (scrollY>getChildHeight(6)&&scrollY<getChildHeight(7)){
                    tab.setScrollPosition(0,6,true);
                }else if (scrollY>getChildHeight(7)&&scrollY<getChildHeight(8)){
                    tab.setScrollPosition(0,7,true);
                }else if (scrollY>getChildHeight(8)&&scrollY<getChildHeight(9)){
                    tab.setScrollPosition(0,8,true);
                }else if (scrollY>getChildHeight(9)&&scrollY<getChildHeight(10)){
                    tab.setScrollPosition(0,9,true);
                }

            }
        });

    }
    //计算向下滑动的距离
    int count;
    int height;
    private int getChildHeight(int i) {
        count=0;height=0;
        for (int a = 0; a < i; a++) {
            height = layout.getChildAt(a).getHeight();
            count = height + count;
        }
        return count;
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
