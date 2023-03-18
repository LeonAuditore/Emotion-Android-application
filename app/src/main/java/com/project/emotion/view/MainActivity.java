package com.project.emotion.view;

import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.project.emotion.R;
import com.project.emotion.adapter.MainPagerAdapter;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.view.fragment.TabCircleFragment;
import com.project.emotion.view.fragment.TabHomeFragment;
import com.project.emotion.view.fragment.TabMeFragment;
import com.project.emotion.view.fragment.TabPipeiFragment;
import com.project.emotion.view.fragment.TabShuxinFragment;
import com.project.emotion.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.main_pager)
    NoScrollViewPager mainPager;
    @BindView(R.id.tab_home)
    RadioButton tabHome;
    @BindView(R.id.tab_dynamic)
    RadioButton tabDynamic;
    @BindView(R.id.tab_rank)
    RadioButton tabRank;
    @BindView(R.id.tab_me)
    RadioButton tabMe;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.tab_xin)
    RadioButton tabXin;

    private boolean isStartLogin;
    private long clickTime;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TabHomeFragment());
        fragments.add(new TabPipeiFragment());
        fragments.add(new TabShuxinFragment());
        fragments.add(new TabCircleFragment());
        fragments.add(new TabMeFragment());

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        mainPager.setOffscreenPageLimit(2);
        mainPager.setAdapter(adapter);
        radioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.tab_home:
                mainPager.setCurrentItem(0, false);
                break;
            case R.id.tab_dynamic:
                mainPager.setCurrentItem(1, false);
                break;
            case R.id.tab_xin:
                mainPager.setCurrentItem(2, false);
                break;
            case R.id.tab_rank:
                mainPager.setCurrentItem(3, false);
                break;
            case R.id.tab_me:
                mainPager.setCurrentItem(4, false);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(this, "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            try {
                //正常退出
                Process.killProcess(Process.myPid());
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}