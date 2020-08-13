package com.yun.common.toast;

import android.widget.Toast;

/**
 * Created by Lovelin on 2020/1/18
 * <p>
 * Describe:Toast 处理策略
 */
public interface IToastStrategy {
    /** 短吐司显示的时长 */
    int SHORT_DURATION_TIMEOUT = 2000;
    /** 长吐司显示的时长 */
    int LONG_DURATION_TIMEOUT = 3500;

    /**
     * 绑定 Toast 对象
     */
    void bind(Toast toast);

    /**
     * 显示 Toast
     */
    void show(CharSequence text);

    /**
     * 取消 Toast
     */
    void cancel();
}
