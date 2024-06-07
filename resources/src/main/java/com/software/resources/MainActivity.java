package com.software.resources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_state;
    private ImageView iv_raw;
    private Button btn_raw;
    private ImageView iv_assets;
    private Button btn_assets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initEvents();
    }

    private void initEvents() {
        iv_state.setOnClickListener(v -> {

        });

        //raw 和 assets都是原生资源，需要通过流来读取
        btn_raw.setOnClickListener(v -> {
            //读取raw文件
            //1、获取资源管理器
            Resources rs = getResources();

            //2、获取图片资源，通过流的形式。会简化流式操作
            InputStream is = rs.openRawResource(R.raw.avatar1);

            //3、显示在ImageView控件上
            Bitmap bitmap = BitmapFactory.decodeStream(is);//将流转换成图片
            iv_raw.setImageBitmap(bitmap);

            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btn_assets.setOnClickListener(v -> {
            //读取assets文件
            //1、获取AssetsManager资源管理器
            AssetManager assets = getAssets();

            try {
                //2、获取图片资源，流的形式。会简化流式操作
                InputStream is = assets.open("avatar2.png");//默认到assets目录下找
                //3、显示在ImageView控件上
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                iv_assets.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
    }

    private void initViews() {
        iv_state = findViewById(R.id.iv_state);
        btn_raw = findViewById(R.id.btn_raw);
        iv_raw = findViewById(R.id.iv_raw);
        btn_assets = findViewById(R.id.btn_assets);
        iv_assets = findViewById(R.id.iv_assets);

    }
}