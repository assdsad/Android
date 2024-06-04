package com.software.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button btn_baidu;
    private TextView tv_baidu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        //绑定访问百度的事件
        btn_baidu.setOnClickListener(v -> {
            new Thread(){//匿名内部类

                @Override
                public void run() {
                    visitBaidu();//访问网络需要在子线程中运行
                }
            }.start();
        });
    }

    private void visitBaidu(){
        URL url = null;
        InputStream is = null;
        try{
            //1、创建URL
            //访问网络需要获取系统权限   在manifest中加
            url = new URL("https://www.baidu.com");

            //2、获得流（输入流）
            is = url.openStream();

            //3、通过循环把流中的内容读取
            BufferedReader br = new BufferedReader(new InputStreamReader(is));//字符流
//            br.readLine()//如果流已结束而没有读取到任何字符，则为null
            String read = null;
            StringBuffer sb = new StringBuffer();//创建一个字符串来接收内容
            while((read = br.readLine()) != null) {
                sb.append(read);//将内容存入字符串中
            }

            //4、将读取的内容放入到TextView中   把sb放入TextView中   把子线程中的内容放入主线程中
            //需要进行主子线程通讯
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_baidu.setText(sb.toString());
                }
            });
            

        }catch(IOException e){
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }




    }


    private void initViews() {
        btn_baidu = findViewById(R.id.btn_baidu);
        tv_baidu = findViewById(R.id.tv_baidu);
    }
}