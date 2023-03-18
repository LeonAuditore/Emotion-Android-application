package com.project.emotion.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.project.emotion.R;
import com.project.emotion.base.LazyFragment;
import com.project.emotion.utils.GlideImageLoader;
import com.project.emotion.view.activities.CeshiActivity;
import com.project.emotion.view.activities.DiaryListActivity;
import com.project.emotion.view.activities.GameActivity;
import com.project.emotion.view.activities.Mp3ListActivity;
import com.project.emotion.view.activities.StoryListActivity;
import com.project.emotion.view.activities.TodayActivity;
import com.project.emotion.view.activities.VideoListActivity;
import com.project.emotion.view.activities.AsmrListActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/3/17 21:47
 */
public class TabHomeFragment extends LazyFragment {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rl_game)
    RelativeLayout rlGame;
    @BindView(R.id.rl_day)
    RelativeLayout rlDay;
    @BindView(R.id.rl_gushi)
    RelativeLayout rlGushi;
    @BindView(R.id.rl_video)
    RelativeLayout rlVideo;
    @BindView(R.id.rl_riji)
    RelativeLayout rlRiji;
    @BindView(R.id.rl_asmr)
    RelativeLayout rlAsmr;
    @BindView(R.id.rl_mp3)
    RelativeLayout rlMp3;
    @BindView(R.id.rl_ceshi)
    RelativeLayout rlCeshi;



    @Override
    protected int getLayoutId() {
        return R.layout.tab_home_fragment;
    }

    @Override
    protected void loadData() {
        bannerPicture();
    }

    private void bannerPicture() {
        List imageList = new ArrayList<>();
        imageList.add("https://c-ssl.duitang.com/uploads/item/201304/30/20130430203259_yYNMy.thumb.1000_0.jpeg");
        imageList.add("https://up.enterdesk.com/edpic_360_360/7e/5b/66/7e5b66d4a3a2319c8c44cfc28e6581db.jpg");
        imageList.add("https://up.enterdesk.com/edpic_360_360/5b/81/af/5b81af7d17cce65fb122aa8008a7786d.jpg");


        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @OnClick({R.id.rl_game, R.id.rl_day, R.id.rl_gushi, R.id.rl_video, R.id.rl_riji,R.id.rl_asmr,R.id.rl_mp3,R.id.rl_ceshi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_game://解压游戏
                startActivity(new Intent(getActivity(), GameActivity.class));
                break;
            case R.id.rl_day:
                startActivity(new Intent(getActivity(), TodayActivity.class));
                break;
            case R.id.rl_gushi:
                startActivity(new Intent(getActivity(), StoryListActivity.class));
                break;
            case R.id.rl_video:
                startActivity(new Intent(getActivity(), VideoListActivity.class));
                break;
            case R.id.rl_asmr:
                startActivity(new Intent(getActivity(),AsmrListActivity.class));
                break;
            case R.id.rl_riji:
                startActivity(new Intent(getActivity(), DiaryListActivity.class));
                break;
            case R.id.rl_mp3:
                startActivity(new Intent(getActivity(), Mp3ListActivity.class));
                break;
            case R.id.rl_ceshi:
                startActivity(new Intent(getActivity(), CeshiActivity.class));
        }
    }
}
