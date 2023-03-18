package com.project.emotion.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.emotion.entity.UserBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户信息增删改查
 */
public class SqliteDBUtils {

    public static final String DB_NAME = "user_dbname";

    public static final int VERSION = 1;
 
    private static SqliteDBUtils sqliteDB;
 
    private SQLiteDatabase db;
 
    private SqliteDBUtils(Context context) {
        OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }
 
    /**
     * ��ȡSqliteDBʵ��
     * @param context
     */
    public synchronized static SqliteDBUtils getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new SqliteDBUtils(context);
        }
        return sqliteDB;
    }
 
    
    public void delete(Context context,String id) {
        OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
    	 db = dbHelper.getReadableDatabase();
    	 db.delete("User", "id=?", new String[] { id });
	}

    /**
     * 修改用户信息
     * @param context
     * @param userBean
     */
    public void change(Context context, UserBean userBean) {
    	   OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
      	 db = dbHelper.getWritableDatabase();
      	ContentValues values = new ContentValues();
      	values.put("id", userBean.getId());
        values.put("user_id", userBean.getUser_id());
        values.put("name", userBean.getName());
        values.put("password", userBean.getPassword());
        values.put("student_num", userBean.getStudent_num());
        values.put("mobile", userBean.getMobile());
        values.put("type", userBean.getType());
        values.put("head_url", userBean.getHead_url());
        db.update("User", values, "id=?", new String[]{userBean.getId()+""});
	}

    /**
     * 保存用户
     * @param user
     * @return
     */
    public int  saveUser(UserBean user) {
        if (user != null) {

            Cursor cursor = db.rawQuery("select * from User where name=?", new String[]{user.getName().toString()});
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    db.execSQL("insert into User(user_id,name,password,student_num,head_url,type,mobile) values(?,?,?,?,?,?,?) ", new String[]{
                            user.getUser_id(),
                            user.getName(),
                            user.getPassword(),
                            user.getStudent_num(),
                            user.getHead_url(),
                            user.getType(),
                            user.getMobile()
                    });
                } catch (Exception e) {
                    Log.d("����", e.getMessage().toString());
                }
                return 1;
            }
        }
        else {
            return 0;
        }
    }


    public List<UserBean> findAll(){
        List<UserBean> userBeanList = new ArrayList<>();
        Cursor cursor = db
                .query("User", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                   UserBean user = new UserBean();
                    user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    user.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
                    user.setName(cursor.getString(cursor.getColumnIndex("name")));
                    user.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
                    user.setStudent_num(cursor.getString(cursor.getColumnIndex("student_num")));
                    user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                    user.setHead_url(cursor.getString(cursor.getColumnIndex("head_url")));
                    user.setType(cursor.getString(cursor.getColumnIndex("type")));
                    userBeanList.add(user);
            } while (cursor.moveToNext());
        }
        return userBeanList;
    }
    public UserBean select(String mobile){
        UserBean user = null;
        Cursor cursor = db
                .query("User", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if(mobile.equals(cursor.getString(cursor.getColumnIndex("name")))){
                    user = new UserBean();
                    user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    user.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
                    user.setName(cursor.getString(cursor.getColumnIndex("name")));
                    user.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
                    user.setStudent_num(cursor.getString(cursor.getColumnIndex("student_num")));
                    user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                    user.setHead_url(cursor.getString(cursor.getColumnIndex("head_url")));
                    user.setType(cursor.getString(cursor.getColumnIndex("type")));
                    return user;
                }
            } while (cursor.moveToNext());
        }
        return user;
    }

 
    public int Quer(String pwd,String name)
    {

        Cursor cursor =db.rawQuery("select * from User where name=?", new String[]{name});
        if (cursor.getCount()>0)
        {
            Cursor pwdcursor =db.rawQuery("select * from User where name=? and password=?",new String[]{name,pwd});
            if (pwdcursor.getCount()>0)
            {
                return 1;
            }
            else {
                return -1;
            }
        }
        else {
            return 0;
        }
 
 
    }

}