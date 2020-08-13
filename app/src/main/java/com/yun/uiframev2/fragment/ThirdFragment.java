package com.yun.uiframev2.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.yun.uiframev2.R;
import com.yun.uiframev2.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Lovelin on 2019/4/27
 * <p>
 * Describe:
 */
public class ThirdFragment extends BaseFragment {

    @BindView(R.id.fake_status_bar)
    View mFakeStatusBar;
    @Override
    public int getContentViewId() {
        return R.layout.fragment_03;
    }

    @Override
    public void init(ViewGroup rootView) {
        setTitleBarVisibility(View.VISIBLE);
        setTitleLeftBtnVisibility(View.GONE);
        setTitleName("03");
        setPageStateView();
        showError();

    }


}
