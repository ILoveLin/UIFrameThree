package com.yun.uiframev2.activity.login;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yun.uiframev2.R;
import com.yun.uiframev2.base.BaseActivity;
import com.yun.uiframev2.base.help.InputTextHelper;
import com.yun.uiframev2.base.help.KeyboardWatcher;
import com.yun.uiframev2.utils.NetworkUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Lovelin on 2020/2/18
 * <p>
 * Describe:带动画的登入界面
 */
public class LoginAnimatorActivity extends BaseActivity implements KeyboardWatcher.SoftKeyboardStateListener {
    @BindView(R.id.iv_login_logo)
    ImageView mLogoView;
    @BindView(R.id.ll_login_body)
    LinearLayout mBodyLayout;
    @BindView(R.id.et_login_phone)
    EditText mPhoneView;
    @BindView(R.id.et_login_password)
    EditText mPasswordView;
    @BindView(R.id.btn_login_commit)
    Button mCommitView;
    @BindView(R.id.v_login_blank)
    View mBlankView;
    @BindView(R.id.ll_login_other)
    View mOtherView;
    @BindView(R.id.btn_login_wechat)
    View btn_login_wechat;
    /**
     * logo 缩放比例
     */
    private final float mLogoScale = 0.8f;
    /**
     * 动画时间
     */
    private final int mAnimTime = 300;
    private String username;
    private String password;
    private String comeFrom02;

    @Override
    public int getContentViewId() {
        return R.layout.activity_login_ainm;
    }

    @Override
    public void init() {
        initView();
        initData();
    }


    private void initView() {
        setTitleBarVisibility(View.VISIBLE);
        setTitleLeftBtnVisibility(View.VISIBLE);
        setTitleRightBtnVisibility(View.INVISIBLE);
        setTitleName("");
        setPageStateView();
//        mPresenter = new NameDetailsPresenter(this, this);
//        //默认选择男
//        selectorSexThreeBoy.setSelected(true);
//        KeyBoardUtils.closeKeybord(tv_addname_name01, this);
//
//        tv_addname_name01.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
//        tv_addname_name02.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});

        comeFrom02 = getIntent().getStringExtra("02") + "";
        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(mPasswordView)
                .setMain(mCommitView)
                .setListener(new InputTextHelper.OnInputTextListener() {

                    @Override
                    public boolean onInputChange(InputTextHelper helper) {
                        return mPhoneView.getText().toString().length() == 11 &&
                                mPasswordView.getText().toString().length() >= 6;
                    }
                })
                .build();

        post(new Runnable() {

            @Override
            public void run() {
                // 因为在小屏幕手机上面，因为计算规则的因素会导致动画效果特别夸张，所以不在小屏幕手机上面展示这个动画效果
                if (mBlankView.getHeight() > mBodyLayout.getHeight()) {
                    // 只有空白区域的高度大于登录框区域的高度才展示动画
                    KeyboardWatcher.with(LoginAnimatorActivity.this)
                            .setListener(LoginAnimatorActivity.this);
                }
            }
        });


    }

    public void initData() {
//        // 判断用户当前有没有安装微信
//        if (!isWeixinAvilible(this)) {
//            btn_login_wechat.setVisibility(View.GONE);
//        }

    }


    /**
     * 无网络的时候 刷新数据
     */
    @Override
    protected void onClickRetry() {
        super.onClickRetry();
        if (NetworkUtil.CheckConnection(this)) {
//            sendRequest();
        } else {
            showToast("请打开网络链接");
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.tv_login_forget, R.id.btn_login_commit, R.id.btn_login_wechat, R.id.btn_register_commit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_forget:
//                startActivity(PasswordActivity.class);
                break;
            case R.id.btn_register_commit:
                Bundle bundle = new Bundle();
                bundle.putString("title", "注册");
                bundle.putString("type", "");
                openActivity(RegisterAnimatorActivity.class, bundle);
                break;
            case R.id.btn_login_commit:
                if (mPhoneView.getText().toString().length() != 11) {
                    showToast("手机号输入不正确");
                } else {
                    checkData();

//                    postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            showContent();
//                            // 处理登录
//                        }
//                    }, 2000);
                }
                break;
            case R.id.btn_login_wechat:
//                toast("记得改好第三方 AppID 和 AppKey，否则会调不起来哦");
                switch (v.getId()) {
                    case R.id.btn_login_wechat:
                        wxLogin();

                        break;
                    default:
                        throw new IllegalStateException("are you ok?");
                }
                break;
            default:
                break;
        }
    }


    public void wxLogin() {

    }


    private void checkData() {
        username = mPhoneView.getText().toString().trim();
        password = mPasswordView.getText().toString().trim();
        if ("".equals(username) && TextUtils.isEmpty(username)) {
            showToast("用户名--不能为空哦~");
            return;
        } else if ("".equals(password) && TextUtils.isEmpty(password)) {
            showToast("密码--不能为空哦~");
            return;
        } else {
//            sendRequest();
        }


    }

    /**
     * 实现方法
     *
     * @param keyboardHeight 软键盘高度
     */

    @Override
    public void onSoftKeyboardOpened(int keyboardHeight) {
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int[] location = new int[2];
        // 获取这个 View 在屏幕中的坐标（左上角）
        mBodyLayout.getLocationOnScreen(location);
        //int x = location[0];
        int y = location[1];
        int bottom = screenHeight - (y + mBodyLayout.getHeight());
        if (keyboardHeight > bottom) {
            // 执行位移动画
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBodyLayout, "translationY", 0, -(keyboardHeight - bottom));
            objectAnimator.setDuration(mAnimTime);
            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimator.start();

            // 执行缩小动画
            mLogoView.setPivotX(mLogoView.getWidth() / 2f);
            mLogoView.setPivotY(mLogoView.getHeight());
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(mLogoView, "scaleX", 1.0f, mLogoScale);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(mLogoView, "scaleY", 1.0f, mLogoScale);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(mLogoView, "translationY", 0.0f, -(keyboardHeight - bottom));
            animatorSet.play(translationY).with(scaleX).with(scaleY);
            animatorSet.setDuration(mAnimTime);
            animatorSet.start();
        }

    }

    @Override
    public void onSoftKeyboardClosed() {

        // 执行位移动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBodyLayout, "translationY", mBodyLayout.getTranslationY(), 0);
        objectAnimator.setDuration(mAnimTime);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();

        if (mLogoView.getTranslationY() == 0) {
            return;
        }
        // 执行放大动画
        mLogoView.setPivotX(mLogoView.getWidth() / 2f);
        mLogoView.setPivotY(mLogoView.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mLogoView, "scaleX", mLogoScale, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mLogoView, "scaleY", mLogoScale, 1.0f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mLogoView, "translationY", mLogoView.getTranslationY(), 0);
        animatorSet.play(translationY).with(scaleX).with(scaleY);
        animatorSet.setDuration(mAnimTime);
        animatorSet.start();

    }


    /**
     * 延迟执行
     */
    public final boolean post(Runnable r) {
        return postDelayed(r, 0);
    }

    /**
     * 延迟一段时间执行
     */
    public final boolean postDelayed(Runnable r, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return postAtTime(r, SystemClock.uptimeMillis() + delayMillis);
    }


    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    public final Object mHandlerToken = hashCode();

    /**
     * 在指定的时间执行
     */
    public final boolean postAtTime(Runnable r, long uptimeMillis) {
        // 发送和这个 Activity 相关的消息回调
        return HANDLER.postAtTime(r, mHandlerToken, uptimeMillis);
    }


}
