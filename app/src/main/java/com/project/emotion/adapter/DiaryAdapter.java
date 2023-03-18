package com.project.emotion.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.emotion.R;
import com.project.emotion.entity.Diary;
import com.project.emotion.view.activities.DiaryDetailActivit;


import java.util.List;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/2/15 20:32
 */
public class DiaryAdapter extends BaseAdapter {
    private Context context;
    private List<Diary> diaryList;

    public DiaryAdapter(Context context, List<Diary> diaryList){
        this.context = context;
        this.diaryList = diaryList;
    }
    @Override
    public int getCount() {
        return diaryList.size();
    }

    @Override
    public Object getItem(int i) {
        return diaryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_diary,null);
            view.setTag(holder);
            holder.img = view.findViewById(R.id.img);
            holder.ll = view.findViewById(R.id.ll);
            holder.tv_title = view.findViewById(R.id.tv_title);
            holder.tv_time = view.findViewById(R.id.tv_time);
            holder.tv_xinqing = view.findViewById(R.id.tv_xinqing);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        if (TextUtils.isEmpty(diaryList.get(i).getImg())){
            holder.img.setVisibility(View.GONE);
        }else {        Glide.with(context).load(diaryList.get(i).getImg()).into(holder.img);}

        holder.tv_time.setText(diaryList.get(i).getTime());
        holder.tv_title.setText(diaryList.get(i).getTitle());
        holder.tv_xinqing.setText(diaryList.get(i).getXinqing());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DiaryDetailActivit.class);
                intent.putExtra("detail",diaryList.get(i));
                context.startActivity(intent);
            }
        });
        return view;
    }

     class ViewHolder{
        LinearLayout ll;
        ImageView img;
        TextView tv_title,tv_time,tv_xinqing;
    }
}
