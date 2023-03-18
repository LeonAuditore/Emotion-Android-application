package com.project.emotion.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.project.emotion.R;
import com.project.emotion.entity.Luntan;
import com.project.emotion.inter.OnLuntanListener;


/**
 * @author 袁茏天
 * @description:首页论坛适配器
 * @date :2022/2/23 23:09
 */
public class LuntanAdapter extends BaseQuickAdapter<Luntan, BaseViewHolder> {
    private OnLuntanListener listener;
    private Context context;
    public LuntanAdapter(Context context,OnLuntanListener luntanListener) {
        super(R.layout.item_home_luntan);
        this.context = context;
        this.listener = luntanListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final Luntan item) {
        helper.setText(R.id.tv_name,item.getUsername());
        if (!TextUtils.isEmpty(item.getHead_url())){
            Glide.with(context).load(item.getHead_url()).into((ImageView) helper.getView(R.id.image_head));
        }
        if (!TextUtils.isEmpty(item.getPic())){
            helper.getView(R.id.iv_pic).setVisibility(View.VISIBLE);
            Glide.with(context).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_pic));
        }
        helper.setText(R.id.tv_content,item.getContent());
        helper.setText(R.id.tv_zan,"点赞"+item.getZan());
        helper.getView(R.id.ll_pl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onCommentClick(item);
                }
            }
        });
        helper.getView(R.id.ll_zan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onPraiseClick(item);
                }
            }
        });
    }
}
