package com.project.emotion.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.components.ImmersionFragment;

import com.project.emotion.R;
import com.project.emotion.entity.EventMessage;
import com.trello.rxlifecycle2.components.support.RxFragment;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by 袁茏天 on 2022/2/28.
 */
public class BaseFragment extends ImmersionFragment {




    /**
     * fragment处理back事件的方法
     * return  true表示要处理，false表示不处理
     */
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


        //移除所有的粘性事件
        EventBus.getDefault().removeAllStickyEvents();
        //取消注册
        EventBus.getDefault().unregister(this);

    }

    /**
     * fragment intent跳转
     */
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.start_in, R.anim.start_out);
    }


    public void skipActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.start_in, R.anim.start_out);
    }

    /**
     * 在要接收消息的页面,注册EventBus
     */
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 在要接收消息页面的onDestroy方法中,反注册EventBus
     */
    public void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MainThread, sticky = true)
    public void onEvent(EventMessage msg) {

    }

    @Subscribe(threadMode = ThreadMode.MainThread, sticky = true)
    public void onEventSticky(EventMessage msg) {

    }

    @Override
    public void initImmersionBar() {

    }
}

