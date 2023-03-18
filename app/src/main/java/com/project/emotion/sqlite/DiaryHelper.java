package com.project.emotion.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 文件名：DiaryHelper
 * 作  者： 袁茏天
 * 日  期：2/15/22 3:36 PM
 * 描述：TOOD
 */
public class DiaryHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER = "create table Diary ("
            + "id integer primary key autoincrement, "
            + "user_id text, "
            + "title text, "
            + "content text, "
            + "weather text, "
            + "time text, "
            + "xinqing text, "
            + "ispublic text, "
            + "img text)";

    public DiaryHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
