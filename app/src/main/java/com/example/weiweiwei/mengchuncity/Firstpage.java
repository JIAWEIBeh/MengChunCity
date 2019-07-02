package com.example.weiweiwei.mengchuncity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Firstpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        SharedPreferences sharedPreferences = getSharedPreferences("isok",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("ifok",true)){//判断ifok的值 如果没有ifok 则返回true
            startActivity(new Intent(Firstpage.this,Viewpage.class));
            finish();
        }else{
            startActivity(new Intent(Firstpage.this,MainActivity.class));
            finish();
        }
    }
}
