package com.yun.uiframev2.activity;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yun.common.constants.Constants;
import com.yun.common.utils.elseutils.SharePreferenceUtil;
import com.yun.common.utils.elseutils.StatusBarUtil;
import com.yun.common.utils.elseutils.StatusBarUtils;
import com.yun.common.utils.permission.FanPermissionListener;
import com.yun.common.utils.permission.FanPermissionUtils;
import com.yun.uiframev2.R;
import com.yun.uiframev2.base.ActivityCollector;
import com.yun.uiframev2.base.BaseActivity;
import com.yun.uiframev2.fragment.FourthFragment;
import com.yun.uiframev2.fragment.SecondFragment;
import com.yun.uiframev2.fragment.TestFragment;
import com.yun.uiframev2.fragment.ThirdFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_tab_homepage)
    TextView tvTabFirstPage;   //首页
    @BindView(R.id.tv_tab_drug_query)
    TextView tvTabSecondPage;  //药品查询
    @BindView(R.id.tv_tab_recomment)
    TextView tvTabThirdPage;  //咨询
    @BindView(R.id.tv_tab_mine)
    TextView tvTabFourthPage;        //我的
    @BindView(R.id.iv_tab_mine)
    ImageView ivTabMine;        //我的小红点
    @BindView(R.id.rel_tab_mine)
    RelativeLayout relTabMine;      //整体的我的relative　　　
    @BindView(R.id.layout_bottom_lin)
    LinearLayout layoutBottomLin;
    @BindView(R.id.ll_main_bottom)
    LinearLayout linearBottom;
    private FragmentManager fragmentManager;
    private Integer valTab;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        setTitleBarVisibility(View.GONE);
        linearBottom.setVisibility(View.VISIBLE);
        fragmentManager = getSupportFragmentManager();
        valTab = (Integer) SharePreferenceUtil.get(this, SharePreferenceUtil.DYNAMIC_SWITCH_TAB, Constants.TAB_HOME);
        setChoiceItem(valTab);

        requestPermission();
    }

    private void requestPermission() {
        FanPermissionUtils.with(MainActivity.this)
                //添加所有你需要申请的权限
                .addPermissions(Manifest.permission.CALL_PHONE)
                .addPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .addPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .addPermissions(Manifest.permission.CALL_PHONE)
                .addPermissions(Manifest.permission.ACCESS_WIFI_STATE)
                .addPermissions(Manifest.permission.CAMERA)
                //添加权限申请回调监听 如果申请失败 会返回已申请成功的权限列表，用户拒绝的权限列表和用户点击了不再提醒的永久拒绝的权限列表
                .setPermissionsCheckListener(new FanPermissionListener() {
                    @Override
                    public void permissionRequestSuccess() {
                        //所有权限授权成功才会回调这里
//                        ((TextView) findViewById(R.id.tv_result)).setText("授权结果\n\n所有权限都授权成功\n");
                        Toast.makeText(MainActivity.this, "所有权限都授权成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void permissionRequestFail(String[] grantedPermissions, String[] deniedPermissions, String[] forceDeniedPermissions) {
                        //当有权限没有被授权就会回调这里
                        StringBuilder result = new StringBuilder("授权结果\n\n授权失败\n\n");
                        result.append("授权通过的权限：\n");
                        for (String grantedPermission : grantedPermissions) {
                            result.append(grantedPermission + "\n");
                        }
                        result.append("\n临时拒绝的权限：\n");
                        for (String deniedPermission : deniedPermissions) {
                            result.append(deniedPermission + "\n");
                        }
                        result.append("\n永久拒绝的权限：\n");
                        for (String forceDeniedPermission : forceDeniedPermissions) {
                            result.append(forceDeniedPermission + "\n");
                        }
//                        ((TextView) findViewById(R.id.tv_result)).setText(result);
                        Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
                    }
                })
                //生成配置
                .createConfig()
                //配置是否强制用户授权才可以使用，当设置为true的时候，如果用户拒绝授权，会一直弹出授权框让用户授权
                .setForceAllPermissionsGranted(true)
                //配置当用户点击了不再提示的时候，会弹窗指引用户去设置页面授权，这个参数是弹窗里面的提示内容
                .setForceDeniedPermissionTips("请前往设置->应用->【" + FanPermissionUtils.getAppName(MainActivity.this) + "】->权限中打开相关权限，否则功能无法正常运行！")
                //构建配置并生效
                .buildConfig()
                //开始授权
                .startCheckPermission();

    }

    //    private FirstFragment firstFragment;
    private TestFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FourthFragment fourthFragment;

    private void setChoiceItem(Integer index) {
        if (index < 0) {
            index = Constants.TAB_HOME;
        }

        SharePreferenceUtil.put(this, SharePreferenceUtil.DYNAMIC_SWITCH_TAB, index);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case Constants.TAB_HOME:   //1　　
                StatusBarUtils.setColor(this, getResources().getColor(R.color.color_transparent), 0);
                StatusBarUtil.darkMode(this, true);  //设置了状态栏文字的颜色
                if (firstFragment == null) {
                    firstFragment = new TestFragment();
                    transaction.add(R.id.ll_content, firstFragment);
                } else {
                    transaction.show(firstFragment);
                }
                tvTabFirstPage.setSelected(true);
                tvTabSecondPage.setSelected(false);
                tvTabThirdPage.setSelected(false);
                tvTabFourthPage.setSelected(false);
                overAnim(tvTabFirstPage);
                break;
            case Constants.TAB_DRUGS_QUERY://药品查询  //2
                StatusBarUtils.setColor(this, getResources().getColor(R.color.result_points), 0);
                StatusBarUtil.darkMode(this, true);  //设置了状态栏文字的颜色
                if (secondFragment == null) {
                    secondFragment = new SecondFragment();
                    transaction.add(R.id.ll_content, secondFragment);
                } else {
                    transaction.show(secondFragment);
                }
                tvTabFirstPage.setSelected(false);
                tvTabSecondPage.setSelected(true);
                tvTabThirdPage.setSelected(false);
                tvTabFourthPage.setSelected(false);
                overAnim(tvTabSecondPage);
                break;

            case Constants.TAB_NEWS:   //3
                StatusBarUtils.setColor(this, getResources().getColor(R.color.color_2395ff), 0);
                StatusBarUtil.darkMode(this, true);  //设置了状态栏文字的颜色
                if (thirdFragment == null) {
                    thirdFragment = new ThirdFragment();
                    transaction.add(R.id.ll_content, thirdFragment);
                } else {
                    transaction.show(thirdFragment);
                }
                tvTabFirstPage.setSelected(false);
                tvTabSecondPage.setSelected(false);
                tvTabThirdPage.setSelected(true);
                tvTabFourthPage.setSelected(false);
                overAnim(tvTabThirdPage);
                break;

            case Constants.TAB_MINE:   //4
                StatusBarUtils.setColor(this, getResources().getColor(R.color.possible_result_points), 0);
                StatusBarUtil.darkMode(this, true);  //设置了状态栏文字的颜色
                if (fourthFragment == null) {
                    fourthFragment = new FourthFragment();
                    transaction.add(R.id.ll_content, fourthFragment);
                } else {
                    transaction.show(fourthFragment);
                }
                tvTabFirstPage.setSelected(false);
                tvTabSecondPage.setSelected(false);
                tvTabThirdPage.setSelected(false);
                tvTabFourthPage.setSelected(true);
                overAnim(tvTabFourthPage);
                break;
        }
        transaction.commit();
    }

    @OnClick({R.id.tv_tab_homepage, R.id.tv_tab_drug_query, R.id.tv_tab_recomment, R.id.rel_tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tab_homepage:    //1
                setChoiceItem(Constants.TAB_HOME);
                break;
            case R.id.tv_tab_drug_query:   // 2
                setChoiceItem(Constants.TAB_DRUGS_QUERY);
                break;
            case R.id.tv_tab_recomment:    //3
                setChoiceItem(Constants.TAB_NEWS);
                break;
            case R.id.rel_tab_mine:   //4
                setChoiceItem(Constants.TAB_MINE);
                break;
            default:
        }
    }

    public static boolean isTabBiyao = false;

    // 隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction transaction) {
        if (firstFragment != null) {
            transaction.hide(firstFragment);

        }
//        if (reimbursementFragment != null) {
//            transaction.hide(reimbursementFragment);
//        }
        if (secondFragment != null) {
            transaction.hide(secondFragment);

        }
        if (thirdFragment != null) {
            transaction.hide(thirdFragment);
        }
        if (fourthFragment != null) {
            transaction.hide(fourthFragment);
        }

    }

    private void overAnim(View view) {
        final ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.8f, 1.0f);
        final ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.8f, 1.0f);
        final ObjectAnimator translationY = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 0, 0);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX, scaleY, translationY);
        set.setDuration(200);
        set.start();
    }


    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                exitTime = System.currentTimeMillis();
                showToast("再按一次退出程序");
            } else {
                SharePreferenceUtil.put(this, SharePreferenceUtil.DYNAMIC_SWITCH_TAB, 0);
                ActivityCollector.removeAll();
                System.exit(0);

             }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}