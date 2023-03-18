package com.project.emotion.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.emotion.R;
import com.project.emotion.adapter.LuntanAdapter;
import com.project.emotion.adapter.AsmrAdaper;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.entity.AsmrBean;
import com.project.emotion.inter.OnAsmrListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsmrListActivity extends BaseActivity implements OnAsmrListener{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.rl_asmr)
    RecyclerView rlAsmr;

    private AsmrAdaper asmrAdaper;
    @Override
    protected  int getLayoutId(){return  R.layout.asmr_list;}

    @Override
    protected void init() {
        List<AsmrBean> asmrBeanList = new ArrayList<>();
        asmrBeanList.add(new AsmrBean("asmr助眠 | 声控敲瓶子","asmr01"));
        asmrBeanList.add(new AsmrBean("asmr助眠 | 碗中世界","asmr02"));
        asmrBeanList.add(new AsmrBean("asmr助眠 | 水中祥音","asmr03"));
        asmrBeanList.add(new AsmrBean("asmr助眠 | 指甲控与盒子","asmr04"));
        asmrBeanList.add(new AsmrBean("asmr助眠 | 墨粉水晶","asmr05"));
        asmrBeanList.add(new AsmrBean("asmr助眠 | 小恐龙哄你睡觉","asmr06"));
        asmrBeanList.add(new AsmrBean("asmr助眠 | 形象拟音","asmr07"));

        asmrAdaper = new AsmrAdaper(this);
        asmrAdaper.setNewData(asmrBeanList);
        rlAsmr.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rlAsmr.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        asmrAdaper.bindToRecyclerView(rlAsmr);
        asmrAdaper.disableLoadMoreIfNotFullPage();
    }

    @Override
    public  void onClick(AsmrBean asmrBean) {
        Intent intent=new Intent(AsmrListActivity.this,AsmrActivity.class);
        intent.putExtra("detail",asmrBean.getUrl());
        startActivity(intent);
    }
}
