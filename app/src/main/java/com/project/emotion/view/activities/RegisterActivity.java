package com.project.emotion.view.activities;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatTextView;


import com.project.emotion.R;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.entity.UserBean;
import com.project.emotion.sqlite.SqliteDBUtils;

import butterknife.BindView;

/**
 * 文件名：RegisterActivity
 * 描述：注册界面
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_id_card)
    EditText inputIdCard;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.input_password_again)
    EditText inputPasswordAgain;
    @BindView(R.id.btn_register)
    AppCompatTextView btnRegister;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String mobile = inputPhone.getText().toString();
                String no = inputIdCard.getText().toString();
                String pwd = inputPassword.getText().toString();
                String pwd_2 = inputPasswordAgain.getText().toString();
                if (TextUtils.isEmpty(no) || TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd_2)) {
                    showToast("请完善信息");
                    return;
                }

                if (!pwd.equals(pwd_2)) {
                    showToast("两次密码不一致");
                    return;
                }
                UserBean userBean = new UserBean();
                userBean.setMobile(mobile);
                userBean.setUser_id(System.currentTimeMillis()+""); //登录名
                userBean.setName(name);
                userBean.setPassword(pwd);
                userBean.student_num = no;
                userBean.setStudent_num(no);
                int i = SqliteDBUtils.getInstance(getApplicationContext()).saveUser(userBean);
                if (i == 1) {
                    showToast("注册成功");
                    finish();
                } else {
                    showToast("注册失败");
                }
            }
        });
    }

}
