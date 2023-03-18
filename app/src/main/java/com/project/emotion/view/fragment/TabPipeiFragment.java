package com.project.emotion.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;

import com.project.emotion.MyApplication;
import com.project.emotion.R;
import com.project.emotion.adapter.GridViewAdapter;
import com.project.emotion.base.LazyFragment;
import com.project.emotion.entity.UserBean;
import com.project.emotion.sqlite.SqliteDBUtils;
import com.project.emotion.view.activities.ChatDetailAct;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/3/19 9:45
 */
public class TabPipeiFragment extends LazyFragment {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.gv_age)
    GridView gvAge;
    @BindView(R.id.gv_zhiye)
    GridView gvZhiye;
    @BindView(R.id.gv_xinqing)
    GridView gvXinqing;
    @BindView(R.id.gv_chat)
    GridView gvChat;
    @BindView(R.id.btn_login)
    AppCompatTextView btnLogin;
    private List<String> mList = new ArrayList<>();
    private List<String> mZiye = new ArrayList<>();
    private List<String> mXinqing = new ArrayList<>();
    private List<String> mChat = new ArrayList<>();
    private GridViewAdapter mAdapter;
    private GridViewAdapter mZhiyeAdapter;
    private GridViewAdapter mXinqingAdapter;
    private GridViewAdapter mChatAdapter;
    int selectorPosition = 0;
    int selectorZPosition = 0;
    int selectorXPosition = 0;
    int selectorCPosition = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.tab_pipei;
    }

    @Override
    protected void loadData() {
        rlBack.setVisibility(View.GONE);
        mList.add("0~18岁");
        mList.add("18~25岁");
        mList.add("25~35岁");
        mList.add("35~50岁");
        mList.add("50岁以上");
        mAdapter = new GridViewAdapter(getActivity(), mList);
        gvAge.setAdapter(mAdapter);
        //gridView的点击事件
        gvAge.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //把点击的position传递到adapter里面去
                mAdapter.changeState(position);
                selectorPosition = position;
            }
        });

        mZiye.add("学生");
        mZiye.add("上班族");
        mZiye.add("教师");
        mZiye.add("律师");
        mZiye.add("服务业");
        mZiye.add("工人");
        mZiye.add("上班族");
        mZiye.add("其他");

        mZhiyeAdapter = new GridViewAdapter(getActivity(), mZiye);
        gvZhiye.setAdapter(mZhiyeAdapter);
        //gridView的点击事件
        gvZhiye.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //把点击的position传递到adapter里面去
                mZhiyeAdapter.changeState(position);
                selectorZPosition = position;
            }
        });


        mXinqing.add("高兴");
        mXinqing.add("悲伤");
        mXinqing.add("失望");
        mXinqing.add("羡慕");
        mXinqing.add("恐惧");
        mXinqing.add("厌恶");
        mXinqing.add("害羞");
        mXinqing.add("胆怯");
        mXinqing.add("孤独");
        mXinqingAdapter = new GridViewAdapter(getActivity(), mXinqing);
        gvXinqing.setAdapter(mXinqingAdapter);
        //gridView的点击事件
        gvXinqing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //把点击的position传递到adapter里面去
                mXinqingAdapter.changeState(position);
                selectorXPosition = position;
            }
        });

        mChat.add("工作");
        mChat.add("学业");
        mChat.add("亲情");
        mChat.add("爱情");

        mChatAdapter = new GridViewAdapter(getActivity(), mChat);
        gvChat.setAdapter(mChatAdapter);
        //gridView的点击事件
        gvChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //把点击的position传递到adapter里面去
                mChatAdapter.changeState(position);
                selectorCPosition = position;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "";
                if (selectorPosition != -1){
                    type+=mList.get(selectorPosition);
                }
                if (selectorCPosition != -1){
                    type+=mChat.get(selectorCPosition);
                }
                if (selectorZPosition != -1){
                    type+=mZiye.get(selectorZPosition);
                }
                if (selectorXPosition != -1){
                    type+=mXinqing.get(selectorXPosition);
                }
                MyApplication.getInstance().userBean.setType(type);
                SqliteDBUtils.getInstance(getActivity()).change(getActivity(), MyApplication.getInstance().userBean);
                List<UserBean> userBeanList = SqliteDBUtils.getInstance(getActivity()).findAll();
                if (userBeanList.size() == 0){
                    showToast("当前无匹配人员");
                    return;
                }
                List<UserBean> userBeans = new ArrayList<>();
                for (int i = 0; i <userBeanList.size() ; i++) {
                    if (type.equals(userBeanList.get(i).getType())){
                        userBeans.add(userBeanList.get(i));
                    }
                }
                if (userBeans.size() == 0){
                    showToast("当前无匹配人员");
                    return;
                }
                int num = (int)(1+Math.random()*(userBeans.size()));
                UserBean userBean = userBeanList.get(num -1);
                Intent intent = new Intent(getActivity(), ChatDetailAct.class);
                intent.putExtra("haoyouname",userBean.getName());
                intent.putExtra("myname",MyApplication.getInstance().userBean.getName());
                startActivity(intent);
            }
        });
    }
}
