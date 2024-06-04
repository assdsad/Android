package com.software.threadtext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button btn_baidu;
    private TextView tv_baidu;
    private Button btn_img;
    private ImageView iv_img;
    private Handler handler;//利用handler处理多线程，此是用来获取baidu中的图片
    private EditText edt_name;
    private EditText edt_pwd;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        //获取baidu中的一张图片
        handler = new Handler(Looper.getMainLooper()){
            //重写handlerMessage
            @Override
            public void handleMessage(@NonNull Message msg) {
                //处理图像与控件绑定
                if(msg.what == 2) {
                    Bitmap bitmap = (Bitmap) msg.obj;
                    //传输btimap需要在Object
                    iv_img.setImageBitmap(bitmap);
                }
            }
        };

        //绑定访问百度的事件
        btn_baidu.setOnClickListener(v -> {
            new Thread(){//匿名内部类

                @Override
                public void run() {
                    visitBaidu();//访问网络需要在子线程中运行
                }
            }.start();
        });

        //绑定下载图片的事件（到百度中下载）
        btn_img.setOnClickListener(v -> {
            new Thread(){
                @Override
                public void run() {
                    downloadImg("https://img2.baidu.com/it/u=1968668429,2104382916&fm=253&fmt=auto&app=138&f=JPEG?w=507&h=500");
                }
            }.start();
        });

        //模拟登录
        btn_login.setOnClickListener(v -> {
            String name = edt_name.getText().toString();
            String pwd = edt_pwd.getText().toString();
            Toast.makeText(this, "btn clicked!", Toast.LENGTH_SHORT).show();
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setTitle("正在请求网络");
            dialog.show();

            if(name.equals("zhangsan") && pwd.equals("123456")) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            dialog.cancel();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
            }else {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            dialog.cancel();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                Toast.makeText(this, "错误", Toast.LENGTH_SHORT).show();
            }
            });
    }
    public void downloadImg(String imgUrl) {
        URL url = null;
        InputStream is = null;
        try{
            url = new URL(imgUrl);
            is = url.openStream();

            //BitMapFactory 来构建BitMap图像
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            //设置控件图像
            //获取message, 建议使用获取的，这个是安卓提供的，池化的概念
            //有空闲的自动获取， 没有则安卓创建新的， 类似于线程池的概念，连接池
            Message message = handler.obtainMessage();//获取线程中的message
            message.what = 2;
            //将bitmap存入object中
            message.obj = bitmap;
            //向消息队列发送消息
            handler.sendMessage(message);


        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    //此方法要在子线程中运行
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
            while((read = br.readLine()) != null) {//一行一行地读入
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
        btn_img = findViewById(R.id.btn_img);
        iv_img = findViewById(R.id.iv_img);
        edt_name = findViewById(R.id.edt_name);
        edt_pwd = findViewById(R.id.edt_pwd);
        btn_login = findViewById(R.id.btn_login);
    }
}