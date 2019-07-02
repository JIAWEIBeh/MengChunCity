package com.example.weiweiwei.mengchuncity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class Myutil {
    Context context;
    public Myutil(Context context){
        this.context = context;
    }
    public static void gethttp(final String path, final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json;
                    URL url=new URL(path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    InputStream in=conn.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
                    json=bufferedReader.readLine();
                    Message message=handler.obtainMessage();
                    message.obj=json;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public static void hideStatusBar(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }//隐藏状态栏
    public static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    public SQLiteDatabase creat_open_sql(){
        String dirPath="/data/data/"+context.getPackageName()+"/databases/";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dirPath+"word.db",null);
        return db;
    }//创建/打开数据库
    public void createdir(){
        String dirPath= "/data/data/com.example.bishe/databases/";
        File dir=new File(dirPath);
        if(!dir.exists())
            dir.mkdirs();//如果没有目录则创建不了数据库
    }
    public void createTable(SQLiteDatabase db){
        //创建表SQL语句
        String stu_table="create table word_me(id integer primary key autoincrement,entry text,explain text)";
        //执行SQL语句
        db.execSQL(stu_table);

        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);//获取共享首选项
        SharedPreferences.Editor editor = sharedPreferences.edit();//编辑器
        editor.putBoolean("log",false);
        editor.putString("color","#ffffff");//写
        editor.commit();//执行命令
        //sharedPreferences.getString("log",null);

    }//创建数据表
    public void insert(SQLiteDatabase db,ContentValues cValue){

        //调用insert()方法插入数据
        db.insert("word_me",null,cValue);
    }//插入1
    public void insert2(SQLiteDatabase db){
        //插入数据SQL语句
        String stu_sql="insert into word_me(entry,explain) values('xiaoming','01005')";
        //执行SQL语句
        db.execSQL(stu_sql);
    }//插入2
    public void delete(SQLiteDatabase db) {
        //删除条件
        String whereClause = "id=?";
        //删除条件参数
        String[] whereArgs = {String.valueOf(2)};
        //执行删除
        db.delete("word_me",whereClause,whereArgs);
    }//删除1
    public void delete2(SQLiteDatabase db,int id) {
        //删除SQL语句
        String sql = "delete from word_me where id = "+id;
        //执行SQL语句
        db.execSQL(sql);
    }//删除2
    public void update(SQLiteDatabase db) {
        //实例化内容值
        ContentValues values = new ContentValues();
        //在values中添加内容
        values.put("explain","101003");
        //修改条件
        String whereClause = "id=?";
        //修改添加参数
        String[] whereArgs={String.valueOf(1)};
        //修改
        db.update("word_me",values,whereClause,whereArgs);
    }//更新1
    public void update2(SQLiteDatabase db){
        //修改SQL语句
        String sql = "update word_me set explain = 654321 where id = 1";
        //执行SQL
        db.execSQL(sql);
    }//更新2


    public void deletetable(SQLiteDatabase db){
        //删除表的SQL语句
        String sql ="DROP TABLE word_me";
        //执行SQL
        db.execSQL(sql);
    }//删除表
    public static void restartApplication(Context contt) {
        final Intent intent = contt.getPackageManager().getLaunchIntentForPackage(contt.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        contt.startActivity(intent);
    }//重启
}
