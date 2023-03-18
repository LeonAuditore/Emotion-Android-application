package com.project.emotion.view.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.emotion.MyApplication;
import com.project.emotion.R;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.entity.XiaoxiBean;
import com.project.emotion.utils.MyDateUtils;
import com.project.emotion.utils.PreferencesUtils;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatDetailAct extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.tv_send)
    TextView tv_send;



    private PlanAdapter mPlanAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_detail;
    }

    @Override
    protected void init() {
        tv_name.setText(getIntent().getStringExtra("haoyouname"));
        mPlanAdapter = new PlanAdapter(this);
        recycleview.setAdapter(mPlanAdapter);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        try {
            List<XiaoxiBean> loginBeanList = MyApplication.dbManager.findAll(XiaoxiBean.class);
            List<XiaoxiBean> planBeanList = new ArrayList<>();
            if (loginBeanList != null && loginBeanList.size() > 0) {
                int size = loginBeanList.size();
                for (int i = 0; i < size; i++) {
                    if (loginBeanList.get(i).getHaoyouname().equals(
                            getIntent().getStringExtra("haoyouname")) &&
                            loginBeanList.get(i).getMyname().equals(
                                    getIntent().getStringExtra("myname"))
                            || loginBeanList.get(i).getMyname().equals(
                            getIntent().getStringExtra("haoyouname")) &&
                            loginBeanList.get(i).getHaoyouname().equals(
                                    getIntent().getStringExtra("myname"))) {
                        planBeanList.add(loginBeanList.get(i));
                    }
                }
            }
            mPlanAdapter.setData(planBeanList);
            if (mPlanAdapter.getItemCount() == planBeanList.size()) {

            } else {
                mPlanAdapter.setData(planBeanList);
                recycleview.scrollToPosition(planBeanList.size() - 1);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        initEvent();
    }


    public void initEvent() {
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et_content.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(ChatDetailAct.this, "请输入聊天内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                String shijian = MyDateUtils.getTime();
                XiaoxiBean xiaoxiBean = new XiaoxiBean();
                xiaoxiBean.setId(System.currentTimeMillis() + "");
                xiaoxiBean.setContent(content);
                xiaoxiBean.setTime(shijian);
                xiaoxiBean.setHaoyouname(getIntent().getStringExtra("haoyouname"));
                xiaoxiBean.setMyname(getIntent().getStringExtra("myname"));
                try {
                    MyApplication.dbManager.save(xiaoxiBean);
                    init();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                et_content.setText("");
            }
        });
    }

    public int ITEM_TYPE_LEFT = 1;
    public int ITEM_TYPE_RIGHT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public class PlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context context;
        private List<XiaoxiBean> dataBeans = new ArrayList<>();

        public PlanAdapter(Context context) {
            this.context = context;
        }

        public void setData(List list) {
            dataBeans = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            if (MyApplication.getInstance().userBean.getName().equals(dataBeans.get(position).getMyname())) {
                return ITEM_TYPE_RIGHT;
            } else {
                return ITEM_TYPE_LEFT;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == ITEM_TYPE_RIGHT) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_right, parent, false);
                return new RightViewHolder(view);
            } else {
                View view = LayoutInflater.from(context).inflate(R.layout.item_left, parent, false);
                return new LeftViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof RightViewHolder) {
                ((RightViewHolder) holder).chat_item_content_text.setText(dataBeans.get(position).getContent());
                ((RightViewHolder) holder).tv_time.setText(dataBeans.get(position).getTime());
            } else {
                ((LeftViewHolder) holder).chat_item_content_text.setText(dataBeans.get(position).getContent());
                ((LeftViewHolder) holder).tv_time.setText(dataBeans.get(position).getTime());
            }
        }

        public void removeList(int position) {
            dataBeans.remove(position);//删除数据源,移除集合中当前下标的数据
            notifyItemRemoved(position);//刷新被删除的地方
            notifyItemRangeChanged(position, getItemCount()); //刷新被删除数据，以及其后面的数据
        }

        @Override
        public int getItemCount() {
            if (dataBeans != null && dataBeans.size() > 0) {
                return dataBeans.size();
            } else {
                return 0;
            }
        }

        class RightViewHolder extends RecyclerView.ViewHolder {
            private TextView chat_item_content_text;
            private TextView tv_time;

            public RightViewHolder(View itemView) {
                super(itemView);
                chat_item_content_text = itemView.findViewById(R.id.chat_item_content_text);
                tv_time = itemView.findViewById(R.id.tv_time);
            }
        }

        class LeftViewHolder extends RecyclerView.ViewHolder {
            private TextView chat_item_content_text;
            private TextView tv_time;

            public LeftViewHolder(View itemView) {
                super(itemView);
                chat_item_content_text = itemView.findViewById(R.id.chat_item_content_text);
                tv_time = itemView.findViewById(R.id.tv_time);
            }
        }
    }
}
