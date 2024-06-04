package com.software.avatar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.software.avatar.R;
import com.software.avatar.entity.UserInfo;
import com.software.avatar.util.HostUtil;
import com.software.avatar.util.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_phone;
    private EditText edt_password;
    private Button btn_login;
    private TextView tv_register;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //获取控件
        initViews();

        initEvents();

        initHandler();
    }

    private void initHandler() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what == 101) {
                    Result<UserInfo> result = (Result<UserInfo>) msg.obj;
                    if(result.getCode() != 200) {
                        Toast.makeText(LoginActivity.this,
                                result.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        //登录成功
                        Toast.makeText(LoginActivity.this,
                                "登录成功",//result.getMessage()
                                Toast.LENGTH_SHORT).show();
                        //跳转到 我的 页面
                        gotoMine(result.getData());
                    }
                }
            }
        };
    }

    private void gotoMine(UserInfo userInfo) {
        Intent intent = new Intent(
                LoginActivity.this,
                MineActivity.class
        );
        Bundle bundle = new Bundle();
        bundle.putString("phone", userInfo.getPhone());
        bundle.putString("password", userInfo.getPassword());
        bundle.putString("avatar", userInfo.getAvatar());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void initEvents() {

        //点击注册 跳转到注册页面
        tv_register.setOnClickListener(v -> {
            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );
            startActivity(intent);
        });

        btn_login.setOnClickListener(v -> {

            //发送请求到Servlet
            new Thread(){
                @Override
                public void run() {
                    //获取输入的用户名和密码
                    String phone = edt_phone.getText().toString();
                    String password = edt_password.getText().toString();
                    login(phone, password);
                }
            }.start();
        });

    }

    //在post方法中
    public void login(String phone, String password) {
        String path = String.format(
                HostUtil.HOST + "login?phone=%s&password=%s",
                phone,
                password
        );
        try{
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式
            conn.setDoInput(true);//设置输入
            conn.setDoOutput(true);//设置输出
            conn.setRequestMethod("POST");//指明方法
            InputStream is = conn.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            Gson gson = new Gson();
            Result<UserInfo> result = gson.fromJson(line, new TypeToken<Result<UserInfo>>(){}.getType());

            Message message = handler.obtainMessage();
            message.what = 101;
            message.obj = result;
            handler.sendMessage(message);
//            handler.sendMessage(handler.obtainMessage(101, result));


            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }

    //在get方法中
//    public void login(String phone, String password) {
//        InputStream is = null;
//        try{
//            //把用户名和密码写到web服务器中，再由流去读取，得到用户名和密码
//            URL url = new URL(HostUtil.HOST + "/login?phone=" + phone + "&password=" + password);
//            is = url.openStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String json = br.readLine();
//            //返回来的是一个对象，，所以要把json转成对象
//            Gson gson = new Gson();
//            //得到由servlet传回来的result对象
//            Result result = gson.fromJson(json, new TypeToken<Result<UserInfo>>(){}.getType());
//
//            //登录成功，跳转到用户界面
//            if(result.getCode() == 301) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(LoginActivity.this,
//                                result.getMessage(),
//                                Toast.LENGTH_SHORT
//                        ).show();
//                    }
//                });
//
//                gotoUsercenter();
//            }else if(result.getCode() == 302) {//登录失败
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(LoginActivity.this,
//                                result.getMessage(),
//                                Toast.LENGTH_SHORT
//                        ).show();
//
//                        //登录失败，跳转到登录界面
//                        gotoLogin();
//                    }
//                });
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try{
//                if (is != null) {
//                    is.close();
//                }
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void gotoLogin() {
        Intent intent = new Intent(
                LoginActivity.this,
                LoginActivity.class
        );
        startActivity(intent);
    }
    public void gotoUsercenter(){
        Intent intent = new Intent(
                LoginActivity.this,
                UsercenterActivity.class
        );
        startActivity(intent);
    }

    private void initViews() {
        edt_phone = findViewById(R.id.edt_phone);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);
    }
}