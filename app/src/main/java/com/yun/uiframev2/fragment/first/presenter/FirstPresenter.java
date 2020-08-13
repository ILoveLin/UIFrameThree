package com.yun.uiframev2.fragment.first.presenter;

import android.content.Context;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Lovelin on 2019/4/27
 * <p>
 * Describe:
 */
public class FirstPresenter {

    private FirstView mView;
    private Context mContext;


    public FirstPresenter(Context mContext, FirstView mView) {
        this.mView = mView;
        this.mContext = mContext;
    }

    public void sendRequest() {
        mView.showLoadingView();
        OkHttpUtils.post()
                .url("http://sport.jxsccm.com/api/Mine/login")
                .addParams("mobile", "18270720637")
                .addParams("password", "123456")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mView.showErrorView();
                        mView.showToast("请求返回错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            Log.e("Net", "login==response===" + response);
                            mView.showContentView();
                            refreshData(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                });
    }

    private void refreshData(String response) {

        mView.getTextView().setText("11111111" + response);


    }


}
