package com.software.threadafterclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button btn_visit;
    private TextView tv_visit_content;
    private ImageView iv_avatar;
    private Button btn_avatar;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                //图像与控件绑定
                if(msg.what == 1) {
                    Bitmap bitmap = (Bitmap) msg.obj;
                    iv_avatar.setImageBitmap(bitmap);
                }
            }
        };

        btn_visit.setOnClickListener(v -> {
            new Thread(){//创建一个子线程
                @Override
                public void run() {
                    visit();
                }
            }.start();
        });

        btn_avatar.setOnClickListener(v -> {
            new Thread(){
                @Override
                public void run() {
                    getImage("https://img2.baidu.com/it/u=1968668429,2104382916&fm=253&fmt=auto&app=138&f=JPEG?w=507&h=500");
                }
            }.start();
        });

    }

    public void getImage(String imgUrl) {
        URL url = null;
        InputStream is = null;
        try {
            //获取图片链接
            url = new URL(imgUrl);
            is = url.openStream();

            //通过BitmapFactory来构建bitmap图像
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            //设置控件图像
            //获取message, 建议使用获取的，这个是安卓提供的，池化的概念
            //有空闲的自动获取， 没有则安卓创建新的， 类似于线程池的概念，连接池
            //通过handler传输图片信息
            Message message = handler.obtainMessage();//获取线程中的message
            message.what = 1;
            message.obj = bitmap;//将图像存入obj中

            handler.sendMessage(message);//向消息队列发送message

        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if(is != null) {
                try{
                    is.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public void visit(){
        URL url = null;
        InputStream is = null;
        try {
            //创建需要访问网站的url，并在manifest文件中增加网络权限  //<uses-permission android:name="android.permission.INTERNET" />
            url = new URL("https://www.bilibili.com/");
            //获取输入流
            is = url.openStream();

            //通过循环读取输入流中的内容
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String read = null;//read字符串用来一行一行地读取内容
            StringBuffer sb = new StringBuffer();//将read中每次读取到的内容拼接在一起
            while((read = br.readLine()) != null) {
                sb.append(read);
            }

            //将读取到的所有内容放入TextView中
//            tv_visit_content.setText(sb.toString());//直接写程序崩溃，要放入子线程中，整个访问过程都要在子线程中执行，且子线程要与主线程通信

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_visit_content.setText(sb.toString());//实现主、子线程通信
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(is != null){
                try{
                    is.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void initViews() {
        btn_visit = findViewById(R.id.btn_visit);
        tv_visit_content = findViewById(R.id.tv_visit_content);

        btn_avatar = findViewById(R.id.btn_avatar);
        iv_avatar = findViewById(R.id.iv_avatar);
    }
}