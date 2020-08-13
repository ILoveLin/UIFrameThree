package com.yun.uiframev2.fragment.first;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.uiframev2.R;
import com.yun.uiframev2.base.BaseFragment;
import com.yun.uiframev2.fragment.first.presenter.FirstPresenter;
import com.yun.uiframev2.fragment.first.presenter.FirstView;
import com.yun.uiframev2.utils.NetworkUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lovelin on 2019/4/27
 * <p>
 * Describe:
 */
public class FirstFragment extends BaseFragment implements FirstView {


    @BindView(R.id.tv_01)
    TextView tv01;
    @BindView(R.id.fake_status_bar)
    View mFakeStatusBar;
    Unbinder unbinder;
    private FirstPresenter mPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_01;
    }

    @Override
    public void init(ViewGroup rootView) {
        initTitle();
        mPresenter = new FirstPresenter(getActivity(), this);
        responseListener();

    }


    @Override
    protected void onClickRetry() {
        super.onClickRetry();
        if (NetworkUtil.CheckConnection(getActivity())) {
            mPresenter.sendRequest();
        }
    }

    private void initTitle() {
        setTitleBarVisibility(View.VISIBLE);
        setTitleName("01");
        setTitleLeftBtnVisibility(View.GONE);
        setPageStateView();


    }

    private void responseListener() {
        mPresenter.sendRequest();
    }

    @Override
    public void showLoadingView() {
        showLoading();
    }

    @Override
    public void showContentView() {
        showContent();
    }

    @Override
    public void showEmptyView() {
        showEmpty();
    }

    @Override
    public void showErrorView() {
        showError();
    }

    @Override
    public TextView getTextView() {
        return tv01;
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
        unbinder.unbind();
    }
}
