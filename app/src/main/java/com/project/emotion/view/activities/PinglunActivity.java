package com.project.emotion.view.activities;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.project.emotion.MyApplication;
import com.project.emotion.R;
import com.project.emotion.adapter.PinglunAdapter;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.entity.Pinglun;
import com.project.emotion.sqlite.PinglunDBUtils;
import com.project.emotion.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文件名：PinglunActivity
 * 作  者： 袁茏天
 * 日  期：2/24/22 11:11 AM
 * 描述：TOOD
 */
public class PinglunActivity extends BaseActivity {

    PinglunAdapter pinglunAdapter;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.rl_pinglun)
    RecyclerView rlPinglun;
    @BindView(R.id.et_pinglun)
    EditText etPinglun;
    @BindView(R.id.tv_pinglun)
    TextView tvPinglun;
    List<Pinglun> pinglunList;
    int luntan_id;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_pinglun;
    }

    @Override
    protected void init() {
        tvTitle.setText("评论");
        luntan_id = getIntent().getIntExtra("detail",0);
        pinglunList = PinglunDBUtils.getInstance(getApplicationContext()).findAllByLuntanId(luntan_id+"");
        pinglunAdapter = new PinglunAdapter(PinglunActivity.this);
        pinglunAdapter.setNewData(pinglunList);
        rlPinglun.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rlPinglun.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        pinglunAdapter.bindToRecyclerView(rlPinglun);
        pinglunAdapter.disableLoadMoreIfNotFullPage();
    }



    @OnClick({R.id.rl_back, R.id.tv_pinglun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_pinglun:
                String content = etPinglun.getText().toString();
                if (TextUtils.isEmpty(content)){
                    showToast("请输入评论内容");
                    return;
                }else {
                    Pinglun pinglun = new Pinglun();
                    pinglun.setLuntan_id(luntan_id+"");
                    pinglun.setContent(content);
                    pinglun.setTime(Utils.getCurrentTime());
                    pinglun.setUsername(MyApplication.getInstance().userBean.getName());
                    pinglun.setHead_url(MyApplication.getInstance().userBean.getHead_url());
                    PinglunDBUtils.getInstance(getApplicationContext()).insert(pinglun);
                    pinglunList.clear();
                    pinglunList = PinglunDBUtils.getInstance(getApplicationContext()).findAllByLuntanId(luntan_id+"");
                    pinglunAdapter.setNewData(pinglunList);
                    pinglunAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
