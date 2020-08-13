package com.yun.uiframev2.activity;

import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.yun.common.utils.elseutils.SharePreferenceUtil;
import com.yun.common.utils.elseutils.StatusBarUtil;
import com.yun.common.utils.elseutils.StatusBarUtils;
import com.yun.uiframev2.R;
import com.yun.uiframev2.base.BaseActivity;

import com.yun.uiframev2.global.Constants;


/**
 * Created by Lovelin on 2019/3/27
 * <p>
 * Describe:启动页
 */
public class SplashActivity extends BaseActivity {

    private Boolean isFirstIn;
    private ImageView ivSplash;
    private Boolean isLogined;
    private Gson mGson = new Gson();
    public static String[] stringTabArray;

    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void initView() {
        setTitleBarVisibility(View.GONE);
        StatusBarUtils.setColor(this, getResources().getColor(R.color.color_transparent), 0);
        StatusBarUtil.darkMode(this, true);  //设置了状态栏文字的颜色

        ivSplash = findViewById(R.id.iv_splash);
        //是否第一次进入app

        isFirstIn = (Boolean) SharePreferenceUtil.get(this, Constants.SP_IS_FIRST_IN,
                true);
        //是否登入
        isLogined = (Boolean) SharePreferenceUtil.get(this, Constants.Is_Logined, false);

        // 从浅到深,从百分之10到百分之百
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(1500);// 设置动画时间
        ivSplash.setAnimation(aa);// 给image设置动画
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    public void initData() {
        switchGoing();

    }


    //判断进入那个activity
    private void switchGoing() {
        if (isFirstIn) {
//            第一次进入-- 走引导页，否则进入MainActivity
            SharePreferenceUtil.put(SplashActivity.this, Constants.SP_IS_FIRST_IN,
                    false);
            Intent intent = new Intent();
            intent.setClass(SplashActivity.this, GuideActivity.class);
            startActivity(intent);

            finish();

        } else {
//            if (!isLogined) {  //登入成功
//                Intent intent = new Intent();
//                intent.setClass(SplashActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            } else {
            Intent intent = new Intent();
            intent.setClass(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
//            }

        }
    }


//    private void toArray() {
//
//        ArrayList<String> mTitleList = new ArrayList<String>();
//        for (int i = 0; i < tabSportsList.size(); i++) {
//            mTitleList.add(tabSportsList.get(i).getTitle_0());
//        }
//        String[] strings = new String[mTitleList.size()];
//
//        stringTabArray = mTitleList.toArray(strings);
//        for (int i = 0; i < stringTabArray.length; i++) {
//            LogUtils.e("测试===============" + stringTabArray[i]);
//        }
//    }


}
