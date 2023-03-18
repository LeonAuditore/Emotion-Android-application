package com.project.emotion.view.activities;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatTextView;


import com.project.emotion.MyApplication;
import com.project.emotion.R;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.sqlite.SqliteDBUtils;
import com.project.emotion.view.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文件名：LoginActivity
 * 描述：TOOD
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_login)
    AppCompatTextView btnLogin;
    @BindView(R.id.btn_register)
    AppCompatTextView btnRegister;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {

    }


    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Login();
                break;
            case R.id.btn_register:
                showActivity(LoginActivity.this,RegisterActivity.class);
                break;
        }
    }


    private void Login() {
        String name = inputName.getText().toString();
        String pwd = inputPassword.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToast("请输入用户名");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }
        int i = SqliteDBUtils.getInstance(getApplicationContext()).Quer(pwd, name);
        if (i == 1) {
            MyApplication.getInstance().userBean = SqliteDBUtils.getInstance(getApplicationContext()).select(name);
            Log.e("用户信息",MyApplication.getInstance().userBean.toString());
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
        } else if (i == 0) {
            showToast("账号不存在");
            return;
        } else {
            showToast("登录失败,请检查输入信息是否正确");
        }


    }

}
