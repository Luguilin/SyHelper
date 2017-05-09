package com.syhelper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.syhelper.R;
import com.syhelper.tool.T;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_read)
    CheckBox cbRead;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("注册");
        tvBack.setText("返回登录");
    }

    @OnClick({R.id.btn_next, R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                T.showShort("下一步");
                break;
            case R.id.tv_back:
                onBackPressed();
                break;
        }
    }
}
