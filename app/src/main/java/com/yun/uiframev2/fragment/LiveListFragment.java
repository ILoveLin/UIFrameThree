package com.yun.uiframev2.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yun.common.utils.elseutils.StatusBarUtil;
import com.yun.common.utils.elseutils.StatusBarUtils;
import com.yun.uiframev2.R;
import com.yun.uiframev2.adapter.TextAdapter;
import com.yun.uiframev2.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lovelin on 2019/8/22
 * <p>
 * Describe:列表界面
 */
public class LiveListFragment extends BaseFragment {


    @BindView(R.id.tv_name)
    TextView tvName;
    Unbinder unbinder;
    @BindView(R.id.live_recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout mRefreshLayout;
    private ClassicsHeader mClassicsHeader;
    private Drawable mDrawableProgress;
    private static boolean isFirstEnter = true;
    private List<String> mListData;
    private TextAdapter mAdapter;
    private String tabString;

    public static LiveListFragment getInstance(String tabString) {
        Bundle bundle = new Bundle();
        bundle.putString("tabString", tabString);
        LiveListFragment liveListFragment = new LiveListFragment();
        liveListFragment.setArguments(bundle);
        return liveListFragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_livelist;
    }


    @Override
    protected void init(ViewGroup rootView) {
        initView(rootView);
        responseListener();
    }

    private void initView(ViewGroup rootView) {
        setTitleBarVisibility(View.GONE);
        setTitleLeftBtnVisibility(View.GONE);
        setPageStateView();
        showContent();
        setTitleName("直播列表");
        //设置状态栏颜色
        StatusBarUtils.setColor(getActivity(), getResources().getColor(R.color.color_transparent), 0);
        StatusBarUtil.darkMode(getActivity(), true);  //设置了状态栏文字的颜色
        tabString = getArguments().getString("tabString");
//        setTitleName("" + tabString);
        tvName.setText("类型是:" + tabString);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mListData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mListData.add(i + "  item  ");
        }
        mAdapter = new TextAdapter((ArrayList<String>) mListData, getActivity());
        recyclerView.setAdapter(mAdapter);
        //设置 Header 为 贝塞尔雷达 样式
//        mRefreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true));
//        设置 Footer 为 球脉冲 样式
//        mRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));

//        if (isFirstEnter) {
//            isFirstEnter = false;
//            //触发自动刷新
//            mRefreshLayout.autoRefresh();
//        }

    }

    private int refreshTime = 0;
    private int times = 0;
    final int itemLimit = 8;

    private void responseListener() {

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mListData.clear();
//                        for (int i = 0; i < itemLimit; i++) {
//                            mListData.add("item  " + i + "--第-- " + refreshTime + "--次-刷新");
//                        }
                        mAdapter.notifyDataSetChanged();
                        refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败


                    }
                }, 1000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshlayout) {

                if (times < 2) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < itemLimit; i++) {
                                mListData.add("item  " + (1 + mListData.size()));
                            }
                            mAdapter.notifyDataSetChanged();
                            refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败

                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < itemLimit; i++) {
                                mListData.add("item  " + (1 + mListData.size()));
                            }
                            refreshlayout.finishLoadMoreWithNoMoreData();//设置之后，将不会再触发加载事件
                        }
                    }, 1000);
                }
                times++;


//                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
