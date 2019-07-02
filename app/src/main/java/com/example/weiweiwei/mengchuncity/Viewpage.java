package com.example.weiweiwei.mengchuncity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Viewpage extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ImageView[] dots;
    private int[] ids = { R.id.dian1, R.id.dian2, R.id.dian3 };
    private Button kaishi;
    ViewPager viewpage;
    List<View> list = new ArrayList<View>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);

        list.add(getLayoutInflater().inflate(R.layout.one, null));
        list.add(getLayoutInflater().inflate(R.layout.two, null));
        list.add(getLayoutInflater().inflate(R.layout.three, null));
        dots = new ImageView[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }//循环创建选择点
        viewpage = (ViewPager) findViewById(R.id.me_page);
        viewpage.setAdapter(new PageAdapter());
        kaishi = (Button) list.get(2).findViewById(R.id.kaishi);
        kaishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Viewpage.this,MainActivity.class));
                SharedPreferences sharedPreferences = getSharedPreferences("isok",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();//初始化写的操作
                editor.putBoolean("ifok",false);//将ifok = false 写入SharedPreferences
                editor.commit();//执行
                finish();
            }
        });
        viewpage.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < ids.length; i++) {
            if (position == i) {
                dots[i].setImageResource(R.drawable.login_point_selected);
            } else {
                dots[i].setImageResource(R.drawable.login_point);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class PageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            (container).addView(list.get(position));
            return list.get(position);
        }
    }
}
