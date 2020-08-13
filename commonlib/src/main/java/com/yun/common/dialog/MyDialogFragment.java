package com.yun.common.dialog;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;

import com.yun.common.toast.ToastUtils;

import butterknife.ButterKnife;

/**
 * Created by Lovelin on 2020/1/18
 * <p>
 * Describe:项目中的 Dialog 基类
 */
public class MyDialogFragment {
    public static class Builder<B extends MyDialogFragment.Builder>
            extends BaseDialogFragment.Builder<B> {

        public Builder(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public B setContentView(@NonNull View view) {
            // 使用 ButterKnife 注解
            ButterKnife.bind(this, view);
            return super.setContentView(view);
        }

        /**
         * 显示吐司
         */
        public void toast(CharSequence text) {
            ToastUtils.show(text);
        }

        public void toast(@StringRes int id) {
            ToastUtils.show(id);
        }

        public void toast(Object object) {
            ToastUtils.show(object);
        }
    }
}
