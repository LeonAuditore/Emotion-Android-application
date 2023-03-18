package com.project.emotion.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.project.emotion.R;
import com.project.emotion.adapter.LuntanAdapter;
import com.project.emotion.base.LazyFragment;
import com.project.emotion.entity.EventMessage;
import com.project.emotion.entity.Luntan;
import com.project.emotion.inter.OnLuntanListener;
import com.project.emotion.sqlite.LuntanDBUtils;
import com.project.emotion.view.activities.PinglunActivity;
import com.project.emotion.view.activities.SendLuntanActivity;

import java.util.List;

import butterknife.BindView;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/3/17 23:01
 */
public class TabCircleFragment extends LazyFragment implements OnLuntanListener {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.rl_home)
    RecyclerView rlHome;
    @BindView(R.id.swiper)
    SwipeRefreshLayout swipeRefresh;
    LuntanAdapter luntanAdapter;
    List<Luntan> luntanList;
    @Override
    protected int getLayoutId() {
        return R.layout.tab_circle_fragment;
    }

    @Override
    protected void loadData() {
        rlBack.setVisibility(View.GONE);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setBackgroundResource(R.mipmap.add);
        luntanList = LuntanDBUtils.getInstance(getActivity()).findAll();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
            }
        });
        luntanAdapter = new LuntanAdapter(getActivity(),this);
        luntanAdapter.setNewData(luntanList);
        rlHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlHome.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        luntanAdapter.bindToRecyclerView(rlHome);
        luntanAdapter.disableLoadMoreIfNotFullPage();
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SendLuntanActivity.class));
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (rlHome != null && luntanAdapter!= null){
            luntanList.clear();
            luntanList = LuntanDBUtils.getInstance(getActivity()).findAll();
            luntanAdapter.setNewData(luntanList);
            luntanAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onEvent(EventMessage msg) {
        super.onEvent(msg);
        luntanList.clear();
        luntanList = LuntanDBUtils.getInstance(getActivity()).findAll();
        luntanAdapter.setNewData(luntanList);
        luntanAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCommentClick(Luntan luntan) {
        Intent intent = new Intent(getActivity(), PinglunActivity.class);
        intent.putExtra("detail",luntan.getId());
        startActivity(intent);
    }

    @Override
    public void onPraiseClick(Luntan luntan) {
        int zan = Integer.parseInt(luntan.getZan()) +1;
        luntan.setZan(zan+"");
        LuntanDBUtils.getInstance(getActivity()).change(getActivity(),luntan);
        luntanList.clear();
        luntanList = LuntanDBUtils.getInstance(getActivity()).findAll();
        luntanAdapter.setNewData(luntanList);
        luntanAdapter.notifyDataSetChanged();
    }
}
