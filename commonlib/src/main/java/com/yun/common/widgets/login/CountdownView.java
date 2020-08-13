package com.yun.common.widgets.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 验证码倒计时
 */
@SuppressLint("AppCompatCustomView")
public final class CountdownView extends TextView implements Runnable {

    /** 倒计时秒数 */
    private int mTotalSecond = 60;
    /** 秒数单位文本 */
    private static final String TIME_UNIT = "S";

    /** 当前秒数 */
    private int mCurrentSecond;
    /** 记录原有的文本 */
    private CharSequence mRecordText;
    /** 标记是否重置了倒计控件 */
    private boolean mFlag;

    public CountdownView(Context context) {
        super(context);
    }

    public CountdownView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置倒计时总秒数
     */
    public void setTotalTime(int totalTime) {
        this.mTotalSecond = totalTime;
    }

    /**
     * 重置倒计时控件
     */
    public void resetState() {
        mFlag = true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 设置点击的属性
        setClickable(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        // 移除延迟任务，避免内存泄露
        removeCallbacks(this);
        super.onDetachedFromWindow();
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        mRecordText = getText();
        setEnabled(false);
        mCurrentSecond = mTotalSecond;
        post(this);
        return click;
    }

    /**
     * {@link Runnable}
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void run() {
        if (mCurrentSecond == 0 || mFlag) {
            setText(mRecordText);
            setEnabled(true);
            mFlag = false;
        } else {
            mCurrentSecond--;
            setText(mCurrentSecond + " " + TIME_UNIT);
            postDelayed(this, 1000);
        }
    }
}