package com.coderpig.drysisters.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.coderpig.drysisters.R;
import com.r0adkll.slidr.Slidr;

/**
 * 图片详情页面
 */
public class PictureDetailActivity extends AppCompatActivity {

    private ImageView img_picture;
    private String picUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        /**
         * Slidr框架，一行代码实现右滑退出Activity
         * 让Activity随着向右的滑动手势退出，使用非常简单，最少只用一行代码就搞定。
         */
        Slidr.attach(this);
        initData();
        initView();
    }

    private void initData(){
        picUrl = getIntent().getStringExtra("pic_url");
    }

    private void initView(){
        img_picture = findViewById(R.id.img_picture);
        if(null != picUrl) {
            Glide.with(this).load(picUrl).into(img_picture);
        }
    }
}
