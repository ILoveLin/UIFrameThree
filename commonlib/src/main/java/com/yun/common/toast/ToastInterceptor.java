package com.yun.common.toast;

import android.widget.Toast;

/**
 * Created by Lovelin on 2020/1/18
 * <p>
 *    desc   : Toast 默认拦截器
 */
public class ToastInterceptor implements IToastInterceptor {

    @Override
    public boolean intercept(Toast toast, CharSequence text) {
        // 如果是空对象或者空文本就进行拦截
        return text == null || "".equals(text.toString());
    }
}