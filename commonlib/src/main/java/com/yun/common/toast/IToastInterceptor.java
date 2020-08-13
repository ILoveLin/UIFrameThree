package com.yun.common.toast;

/**
 * Created by Lovelin on 2020/1/18
 * <p>
 * Describe:
 */

import android.widget.Toast;

/**
 * desc   : Toast 拦截器接口
 */
public interface IToastInterceptor {

    /**
     * 根据显示的文本决定是否拦截该 Toast
     */
    boolean intercept(Toast toast, CharSequence text);
}