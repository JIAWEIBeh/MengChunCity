package com.example.weiweiwei.mengchuncity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.shijian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");
                startActivity(Intent.createChooser(textIntent, "分享"));*/
                Intent i = new Intent();
                i.setClass(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }

}
