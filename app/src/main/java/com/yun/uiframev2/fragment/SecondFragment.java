package com.yun.uiframev2.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yun.uiframev2.R;
import com.yun.uiframev2.activity.login.LoginAnimatorActivity;
import com.yun.uiframev2.activity.login.RegisterAnimatorActivity;
import com.yun.uiframev2.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lovelin on 2019/4/27
 * <p>
 * Describe:
 */
public class SecondFragment extends BaseFragment {

    @BindView(R.id.fake_status_bar)
    View mFakeStatusBar;
    @BindView(R.id.btn_login_commit)
    Button btn_login_commit;
    @BindView(R.id.btn_register_commit)
    Button btn_register_commit;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_02;
    }

    @Override
    public void init(ViewGroup rootView) {

        setTitleBarVisibility(View.VISIBLE);
        setTitleLeftBtnVisibility(View.GONE);
        setTitleName("02");
        setPageStateView();
        showContent();


    }


    @OnClick({R.id.btn_login_commit, R.id.btn_register_commit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register_commit:
                openActivity(RegisterAnimatorActivity.class);
                break;
            case R.id.btn_login_commit:
                openActivity(LoginAnimatorActivity.class);


            default:
                break;
        }
    }

    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(getActivity(), cls));
    }

}
