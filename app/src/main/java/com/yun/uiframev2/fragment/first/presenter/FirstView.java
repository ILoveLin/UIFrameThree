package com.yun.uiframev2.fragment.first.presenter;

import android.widget.TextView;

/**
 * Created by Lovelin on 2019/4/27
 * <p>
 * Describe:
 */
public interface FirstView {

    void showLoadingView();
    void showContentView();
    void showEmptyView();
    void showErrorView();
    void showToast(String string);

    TextView getTextView();
}
