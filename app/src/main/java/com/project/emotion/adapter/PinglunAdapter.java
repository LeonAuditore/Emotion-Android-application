package com.project.emotion.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.project.emotion.R;
import com.project.emotion.entity.Pinglun;


/**
 * 文件名：PinglunAdapter
 * 作  者： 袁茏天
 * 日  期：2/24/22 11:17 AM
 * 描述：TOOD
 */
public class PinglunAdapter extends BaseQuickAdapter<Pinglun, BaseViewHolder> {
    private Context context;

    public PinglunAdapter(Context context) {
        super(R.layout.item_pinglun);
        this.context =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Pinglun item) {
        helper.setText(R.id.tv_name,item.getUsername());
        helper.setText(R.id.tv_content,item.getContent());
        helper.setText(R.id.tv_time,item.getTime());
        if (!TextUtils.isEmpty(item.getHead_url())){
            Glide.with(context).load(item.getHead_url()).into((ImageView) helper.getView(R.id.image_head));
        }
    }
}
