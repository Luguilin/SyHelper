package com.syhelper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.syhelper.DataListener;
import com.syhelper.MyApplication;
import com.syhelper.R;
import com.syhelper.api.ApiLoginUser;
import com.syhelper.bean.User;
import com.syhelper.httpBean.UserResponse;
import com.syhelper.tool.L;
import com.syhelper.tool.StringHelper;
import com.syhelper.tool.T;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_face)
    ImageView ivFace;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_login)
    public void onViewClicked() {
        String phoneNumber = etPhone.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        if (StringHelper.isEmpty(phoneNumber) || StringHelper.isEmpty(pwd)) {
            T.showShort("请输入正确的账号密码");
        }
        ShowLoading();
        ApiLoginUser apiLoginUser = new ApiLoginUser();
        apiLoginUser.LoginUser(phoneNumber, pwd, new DataListener<UserResponse>() {
            @Override
            public void attach(UserResponse object) {
                DismissLoading();
                if (object != null && object.isSuccess()) {
                    T.showShort("登陆成功");
                    MyApplication.setUser(new User());//假装有数据
                    L.e("=getUserId===" + object.getUserId());
                    setResult(Activity.RESULT_OK);
                    finish();
                } else {
                    T.showShort("登陆失败");
                }
            }

            @Override
            public void failure(String msg) {
                DismissLoading();
                T.showShort(msg);
            }
        });
    }

    @OnClick(R.id.tv_back)
    public void CallBack() {
        onBackPressed();
    }

    @OnClick(R.id.tv_forget_password)
    public void ForgetPassword() {
        startActivity(new Intent(mContext, ForgetPasswordActivity.class));
    }

    @OnClick(R.id.tv_register)
    public void Register() {
        startActivity(new Intent(mContext, RegisterActivity.class));

    }
}
