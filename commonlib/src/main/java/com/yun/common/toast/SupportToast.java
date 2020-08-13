package com.yun.common.toast;

import android.app.Application;

/**
 * Created by Lovelin on 2020/1/18
 * <p>
 * desc   : Toast 无通知栏权限兼容
 */
final class SupportToast extends BaseToast {

    /**
     * 吐司弹窗显示辅助类
     */
    private final ToastHelper mToastHelper;

    SupportToast(Application application) {
        super(application);
        mToastHelper = new ToastHelper(this, application);
    }

    @Override
    public void show() {
        // 显示吐司
        mToastHelper.show();
    }

    @Override
    public void cancel() {
        // 取消显示
        mToastHelper.cancel();
    }
}