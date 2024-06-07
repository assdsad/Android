package com.software.glide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv_avatar = findViewById(R.id.iv_avatar);

        //使用Glide获取网络图片
        Glide.with(this)
                .load("https://pic2.zhimg.com/v2-0783cd2406b2d8fd4d595a2ff35463e9_r.jpg")
                .transform(new CircleCrop())//将图片转换成圆形的
                .into(iv_avatar);
        //with 在哪个activity load 获取的图片  into放到哪个控件
    }
}