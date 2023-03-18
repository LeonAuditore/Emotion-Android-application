package com.project.emotion.view.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.project.emotion.R;
import com.project.emotion.base.LazyFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/3/17 23:11
 */
public class TabShuxinFragment extends LazyFragment {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_login)
    AppCompatTextView btnLogin;

    private static final int SEND_SMS = 100;
    @Override
    protected int getLayoutId() {
        return R.layout.tab_shuxin_fragment;
    }

    @Override
    protected void loadData() {
        rlBack.setVisibility(View.GONE);
        requestPermission();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  phone = etPhone.getText().toString();
                String content = etContent.getText().toString();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(content)){
                    showToast("请检查输入信息");
                    return;
                }
                sendSMSS(phone,content);
            }
        });
    }

    private void requestPermission() {
        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, SEND_SMS);
                return;
            } else {

                //已有权限
            }
        } else {
            //API 版本在23以下
        }
    }

    /**
     * 注册权限申请回调
     *
     * @param requestCode  申请码
     * @param permissions  申请的权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case SEND_SMS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "CALL_PHONE Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //发送短信
    private void sendSMSS(String phone,String content) {
        if (!TextUtils.isEmpty(content) && !TextUtils.isEmpty(phone)) {
            SmsManager manager = SmsManager.getDefault();
            ArrayList<String> strings = manager.divideMessage(content);
            for (int i = 0; i < strings.size(); i++) {
                manager.sendTextMessage(phone, null, content, null, null);
            }
            Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "手机号或内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

    }


}
