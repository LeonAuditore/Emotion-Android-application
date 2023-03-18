package com.project.emotion;

import android.app.Application;


import com.project.emotion.entity.UserBean;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class  MyApplication extends Application {

    private static MyApplication instance;

    public static String TAG = "-------";

    public UserBean userBean = null;

    public static DbManager dbManager;//数据库存储


    static DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbVersion(1)
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                }
            });
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        x.Ext.init(this);
        //x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        if (dbManager == null) {
            dbManager = x.getDb(daoConfig);
        }

    }


    public static MyApplication getInstance() {
        return instance;
    }
}
