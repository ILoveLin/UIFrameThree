package com.yun.uiframev2.activity.login;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.ChineseCalendar;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yun.common.utils.elseutils.CommonUtils;
import com.yun.common.utils.elseutils.GetJsonDataUtil;
import com.yun.common.utils.elseutils.SharePreferenceUtil;
import com.yun.common.widgets.SettingBar;
import com.yun.common.widgets.login.CountdownView;
import com.yun.common.widgets.login.PasswordEditText;
import com.yun.common.widgets.login.RegexEditText;
import com.yun.uiframev2.R;
import com.yun.uiframev2.base.BaseActivity;
import com.yun.uiframev2.global.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Lovelin on 2020/2/18
 * <p>
 * Describe:
 */
public class RegisterAnimatorActivity extends BaseActivity {

    @BindView(R.id.register_relative)
    RelativeLayout register_relative;
    @BindView(R.id.et_register_password1)
    PasswordEditText et_register_password1;
    @BindView(R.id.et_register_password2)
    PasswordEditText et_register_password2;
    @BindView(R.id.cv_register_countdown)
    CountdownView cv_register_countdown;
    @BindView(R.id.tv_title_register)
    TextView tv_title_register;
    @BindView(R.id.btn_register_commit)
    Button btn_register_commit;
    @BindView(R.id.et_register_phone)   //手机号码
            RegexEditText et_register_phone;
    @BindView(R.id.et_register_code)   //手机号码
            EditText et_register_code;
    private int StatueSex;
    private String currentBirthday;
    private Thread thread;
    private boolean isLoaded = false;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private String token;
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private String province;
    private String city;
    private String password02;
    private String password01;
    private String phoneType = "1";

    private String title;
    private String type;

    @Override
    public int getContentViewId() {
        return R.layout.activity_register_anim;
    }

    @Override
    public void init() {
        initView();
    }

    private void initView() {
        setTitleBarVisibility(View.VISIBLE);
        setTitleLeftBtnVisibility(View.VISIBLE);
        setTitleRightBtnVisibility(View.GONE);
        setTitleName("");
        setPageStateView();
    }

//
//    @OnClick({R.id.bar_setting_birthday, R.id.bar_setting_address, R.id.bar_setting_sex,
//            R.id.btn_register_commit, R.id.cv_register_countdown})
//    public void multipleOnclick(View view) {
//        switch (view.getId()) {
//            case R.id.bar_setting_birthday: //生日
//                showInDialog();
//                break;
//            case R.id.bar_setting_address:  //地址
//                ShowPickerView();
//                break;
//            case R.id.bar_setting_sex: //性别   性别 0 女 1男
//                showUpPop(register_relative);
//                break;
//            case R.id.cv_register_countdown: //倒计时,校验手机是否已经注册过了
//                checkPhoneIsRegister();
//
////                CommonUtils.closeKeyboard(RegisterAnimatorActivity.this);
////                checkData();
//                break;
//            case R.id.btn_register_commit: //提交
//                CommonUtils.closeKeyboard(RegisterAnimatorActivity.this);
//                checkData();
//                break;
//
//        }
//    }


}
