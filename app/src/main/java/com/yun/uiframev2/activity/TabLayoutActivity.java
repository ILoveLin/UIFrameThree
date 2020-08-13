package com.yun.uiframev2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.yun.uiframev2.R;
import com.yun.uiframev2.adapter.ChannelPagerAdapter;
import com.yun.uiframev2.base.BaseActivity;
import com.yun.uiframev2.fragment.LiveListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Lovelin on 2019/8/22
 * <p>
 * Describe:这个是一个activity+fragment+SlidingTabLayout+viewpager
 */
public class TabLayoutActivity extends BaseActivity {
    @BindView(R.id.tab_channel)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.vp_content)
    ViewPager mViewPager;
    @BindView(R.id.linear_main)
    LinearLayout linear_main;
    private String[] mChannelArray = {"全部", "热门", "足球", "篮球", "电竞", "网球", "棒球",
            "橄榄球/美式足球", "冰球", "赛马", "排球", "桌球", "乒乓球", "羽毛球", "高尔夫"};

    private List<String> mChannelList = new ArrayList<>();
    private List<LiveListFragment> mFragmentList = new ArrayList<>();
    private ChannelPagerAdapter mChannelPagerAdapter;
    private ImageButton ib_left;

    @Override
    public int getContentViewId() {
        return R.layout.activity_tablayout;
    }

    @Override
    public void init() {
        ib_left = findViewById(R.id.ib_left);
        initFragment();
        initListener();
    }
    private void initListener() {
        ib_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             onPause();
            }
        });
        mChannelPagerAdapter = new ChannelPagerAdapter(getSupportFragmentManager(), mFragmentList, mChannelList);
        mViewPager.setAdapter(mChannelPagerAdapter);
        mTabLayout.setViewPager(mViewPager,mChannelArray);
        mViewPager.setOffscreenPageLimit(mChannelList.size());
    }


    private void initFragment() {
        for (int i = 0; i < mChannelArray.length; i++) {
            String stringName = mChannelArray[i];
            LiveListFragment fragment = LiveListFragment.getInstance(stringName);
            mChannelList.add(stringName);
            mFragmentList.add(fragment);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
