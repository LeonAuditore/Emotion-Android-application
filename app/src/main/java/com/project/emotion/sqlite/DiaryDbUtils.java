package com.project.emotion.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.project.emotion.entity.Diary;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：DiaryDbUtils
 * 作  者： 袁茏天
 * 日  期：2/15/22 4:45 PM
 * 描述：TOOD
 */
public class DiaryDbUtils {

    public static final String DB_NAME = "diary_dbname";

    public static final int VERSION = 1;

    private static DiaryDbUtils sqliteDB;

    private SQLiteDatabase db;

    private DiaryDbUtils(Context context) {
        DiaryHelper dbHelper = new DiaryHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static DiaryDbUtils getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new DiaryDbUtils(context);
        }
        return sqliteDB;
    }


    public void delete(Context context,String id) {
        DiaryHelper dbHelper = new DiaryHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getReadableDatabase();
        db.delete("Diary", "id=?", new String[] { id });
    }


    public void change(Context context, Diary diary) {
        DiaryHelper dbHelper = new DiaryHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", diary.getId());
        values.put("user_id", diary.getUser_id());
        values.put("time", diary.getTime());
        values.put("title", diary.getTitle());
        values.put("content", diary.getContent());
        values.put("weather", diary.getWeather());
        values.put("xinqing", diary.getXinqing());
        values.put("img", diary.getImg());
        values.put("ispublic", diary.getIsPublic());
        db.update("Diary", values, "id=?", new String[]{diary.getId()+""});
    }

    public void   saveUser(Diary diary) {
        try {
            db.execSQL("insert into Diary(user_id,title,content,weather,time,xinqing,ispublic,img) values(?,?,?,?,?,?,?,?) ", new String[]{
                  diary.getUser_id(),
                  diary.getTitle(),
                  diary.getContent(),
                  diary.getWeather(),
                  diary.getTime(),
                    diary.getXinqing(),
                    diary.getIsPublic(),
                    diary.getImg()
            });
        } catch (Exception e) {
            Log.d("����", e.getMessage().toString());
        }
    }



    public List<Diary> findAllByIsPublic(String ispublic) {
        List<Diary> list = new ArrayList<>();
        Cursor cursor = db
                .query("Diary", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if (ispublic.equals(cursor.getString(cursor.getColumnIndex("ispublic")))){
                    Diary user = new Diary();
                    user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    user.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
                    user.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    user.setContent(cursor.getString(cursor.getColumnIndex("content")));
                    user.setWeather(cursor.getString(cursor.getColumnIndex("weather")));
                    user.setTime(cursor.getString(cursor.getColumnIndex("time")));
                    user.setXinqing(cursor.getString(cursor.getColumnIndex("xinqing")));
                    user.setIsPublic(cursor.getString(cursor.getColumnIndex("ispublic")));
                    user.setImg(cursor.getString(cursor.getColumnIndex("img")));
                    list.add(user);
                }
            } while (cursor.moveToNext());
        }
        return list;
    }
    public List<Diary> findAllByUserId(String user_id) {
        List<Diary> list = new ArrayList<>();
        Cursor cursor = db
                .query("Diary", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if (user_id.equals(cursor.getString(cursor.getColumnIndex("user_id")))){
                    Diary user = new Diary();
                    user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    user.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
                    user.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    user.setContent(cursor.getString(cursor.getColumnIndex("content")));
                    user.setWeather(cursor.getString(cursor.getColumnIndex("weather")));
                    user.setTime(cursor.getString(cursor.getColumnIndex("time")));
                    user.setXinqing(cursor.getString(cursor.getColumnIndex("xinqing")));
                    user.setIsPublic(cursor.getString(cursor.getColumnIndex("ispublic")));
                    user.setImg(cursor.getString(cursor.getColumnIndex("img")));
                    list.add(user);
                }
            } while (cursor.moveToNext());
        }
        return list;
    }
    public List<Diary> findAllByTime(String time,String user_id) {
        List<Diary> list = new ArrayList<>();
        Cursor cursor = db
                .query("Diary", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String db_time = cursor.getString(cursor.getColumnIndex("time")).substring(0,11);
                if (time.equals(db_time) && user_id.equals(cursor.getString(cursor.getColumnIndex("user_id")))){
                    Diary user = new Diary();
                    user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    user.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
                    user.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    user.setContent(cursor.getString(cursor.getColumnIndex("content")));
                    user.setWeather(cursor.getString(cursor.getColumnIndex("weather")));
                    user.setTime(cursor.getString(cursor.getColumnIndex("time")));
                    user.setXinqing(cursor.getString(cursor.getColumnIndex("xinqing")));
                    user.setIsPublic(cursor.getString(cursor.getColumnIndex("ispublic")));
                    user.setImg(cursor.getString(cursor.getColumnIndex("img")));
                    list.add(user);
                }
            } while (cursor.moveToNext());
        }
        return list;
    }
}
