package com.example.weiweiwei.mengchuncity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView mTextMessage;
    private LinearLayout one;
    private LinearLayout two;
    private LinearLayout three;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    one.setVisibility(View.VISIBLE);
                    two.setVisibility(View.INVISIBLE);
                    three.setVisibility(View.INVISIBLE);
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    one.setVisibility(View.INVISIBLE);
                    two.setVisibility(View.VISIBLE);
                    three.setVisibility(View.INVISIBLE);
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    one.setVisibility(View.INVISIBLE);
                    two.setVisibility(View.INVISIBLE);
                    three.setVisibility(View.VISIBLE);
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        one = findViewById(R.id.oneF);
        two = findViewById(R.id.twoF);
        three = findViewById(R.id.threeF);
        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
