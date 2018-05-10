package com.munc.nestedlocation;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.nestedScrollView)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.tab_home)
    TabLayout mTabLayout;
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    private String[] tab = {"便民生活", "财富管理", "资金往来", "购物娱乐", "教育公益", "其他服务"};
    private int totalDy;
    private int i1;
    private int a = 0;
    private int count;
    private int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTab();
    }

    private void initTab() {
        for (int i = 0; i < tab.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tab[i]));
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                a = 1;
                int position;
                position = tab.getPosition();
                resetHeight();
                if (position > 0) {
                    for (int i = 0; i <= position - 1; i++) {
                        int count = llMain.getChildAt(i).getHeight();
                        i1 = count + i1;
                    }
                    //这里会执行onScrollChange 不让其执行
                    mNestedScrollView.smoothScrollTo(0, i1);


                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        a = 0;
                    }
                }, 500);//为了和scrollChange错开

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                totalDy = totalDy + scrollY;
                if (a == 0) {
                    if (scrollY >= 0 && scrollY < getChildHeight(1)) {//tab1
                        mTabLayout.setScrollPosition(0, 0, true);
                    } else if (scrollY >= getChildHeight(1) && scrollY < getChildHeight(2)) {//tab2
                        mTabLayout.setScrollPosition(0, 1, true);
                    } else if (scrollY >=  getChildHeight(2) && scrollY <  getChildHeight(3)) {//tab3
                        mTabLayout.setScrollPosition(0, 2, true);
                    } else if (scrollY >=  getChildHeight(3) && scrollY <  getChildHeight(4)) {
                        mTabLayout.setScrollPosition(0, 3, true);
                    } else if (scrollY >=  getChildHeight(4) && scrollY <  getChildHeight(5)) {
                        mTabLayout.setScrollPosition(0, 4, true);
                    } else {
                        mTabLayout.setScrollPosition(0, 5, true);
                    }
                }
            }
        });
    }
    private int getChildHeight(int i) {
        count=0;height=0;
        for (int a = 0; a < i; a++) {
            height = llMain.getChildAt(a).getHeight();
            count = height + count;
        }
        return count;
    }

    private void resetHeight() {
        mNestedScrollView.scrollTo(0, -totalDy);
        i1 = 0;
    }

    @OnClick(R.id.tv_header)
    public void onViewClicked() {
        Toast.makeText(this,"头部",Toast.LENGTH_LONG);
    }
}
