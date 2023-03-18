package com.project.emotion.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.project.emotion.MyApplication;
import com.project.emotion.R;
import com.project.emotion.base.LazyFragment;
import com.project.emotion.sqlite.SqliteDBUtils;
import com.project.emotion.view.activities.AboutActivity;
import com.project.emotion.view.activities.ChangeUserInfoActivity;
import com.project.emotion.view.activities.UserInfoActivity;
import com.project.emotion.widget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/3/17 21:47
 */
public class TabMeFragment extends LazyFragment {
    @BindView(R.id.image_head)
    CircleImageView imageHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_qianming)
    TextView tvQianming;
    @BindView(R.id.rl_userinfo)
    RelativeLayout rlUserinfo;
    @BindView(R.id.rl_change_pwd)
    RelativeLayout rlChangePwd;
    @BindView(R.id.rl_about)
    RelativeLayout rlAbout;
    @BindView(R.id.rl_join)
    RelativeLayout rlJoin;
    @BindView(R.id.rl_restart)
    RelativeLayout rlRestart;
    @BindView(R.id.rl_zhuxiao)
    RelativeLayout rlZhuxiao;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.tab_me_fragment;
    }

    @Override
    protected void loadData() {
        if (imageHead != null) {
            if (MyApplication.getInstance().userBean != null) {
                tvName.setText(MyApplication.getInstance().userBean.getName());
                if (!TextUtils.isEmpty(MyApplication.getInstance().userBean.getHead_url())) {
                    Glide.with(getActivity()).load(MyApplication.getInstance().userBean.getHead_url()).into(imageHead);
                }else {
                    Glide.with(getActivity()).load(R.mipmap.head_default).into(imageHead);
                }
                tvQianming.setText(MyApplication.getInstance().userBean.getStudent_num());
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (imageHead != null) {
            if (MyApplication.getInstance().userBean != null) {
                tvName.setText(MyApplication.getInstance().userBean.getName());
                if (!TextUtils.isEmpty(MyApplication.getInstance().userBean.getHead_url())) {
                    Glide.with(getActivity()).load(MyApplication.getInstance().userBean.getHead_url()).into(imageHead);
                }else {
                    Glide.with(getActivity()).load(R.mipmap.head_default).into(imageHead);
                }
                tvQianming.setText(MyApplication.getInstance().userBean.getStudent_num());
            }
        }
    }
    @OnClick({R.id.rl_userinfo, R.id.rl_change_pwd, R.id.rl_about, R.id.rl_zhuxiao, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_userinfo:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.rl_change_pwd:
                startActivity(new Intent(getActivity(), ChangeUserInfoActivity.class));
                break;
            case R.id.rl_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.rl_zhuxiao:
                SqliteDBUtils.getInstance(getActivity()).delete(getActivity(), MyApplication.getInstance().userBean.getId() + "");
                reStartApp();
                break;
            case R.id.tv_login:
                dialog();
                break;
        }
    }

    public void reStartApp() {
        Intent intent = getActivity().getBaseContext().getPackageManager().getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//与正常页面跳转一样可传递序列化数据,在Launch页面内获得
        intent.putExtra("REBOOT", "reboot");
        startActivity(intent);
        getActivity().finish();
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                try {
                    //正常退出
                    Process.killProcess(Process.myPid());
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
