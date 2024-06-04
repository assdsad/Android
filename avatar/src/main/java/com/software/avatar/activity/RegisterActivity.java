package com.software.avatar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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
import java.net.URL;


public class RegisterActivity extends AppCompatActivity {

    private EditText edt_register_phone;
    private EditText edt_register_password;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();

        initEvents();
    }

    private void initEvents() {
        //注册事件
        btn_register.setOnClickListener(v -> {
            //获取注册的用户名和密码
            String phone = edt_register_phone.getText().toString();
            String password = edt_register_password.getText().toString();

            //发送请求到Servlet(网络请求，需要开启权限)
            //<application> 中加android:usesCleartextTraffic="true"
            //<manifest 下加  <uses-permission android:name="android.permission.INTERNET" />
            new Thread(){
                @Override
                public void run() {
                    register(phone, password);
                }
            }.start();

            //接收Servlet返回的结果
        });
    }

    private void register(String phone, String password) {
        InputStream is = null;
        try {
            //把用户名和密码写到web服务器中，再由流去读取，得到用户名和密码
            URL url = new URL(HostUtil.HOST + "/register?phone=" + phone + "&password=" + password);
            is = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String json = br.readLine();
//            Log.i("RegisterActivity", json);
            //返回来的是一个对象，，所以要把json转成对象   需要增加依赖 build.gradle中implementation 'com.google.code.gson:gson:2.8.6'
            Gson gson = new Gson();
            Result result = gson.fromJson(json, new TypeToken<Result<UserInfo>>(){}.getType());
            if(result.getCode() == 500) {
                //用户名为空或密码为空
                //在主线程中进行提示
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                RegisterActivity.this,
                                result.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
            } else if(result.getCode() == 200){
                //提示注册成功并跳到登录页面
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                RegisterActivity.this,
                                result.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();

                        gotoLogin();//跳转到登录页面
                    }
                });
            }else if(result.getCode() == 409) {
                //提示用户名已存在，并跳回到注册页面
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this,
                                result.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();

                        gotoRegister();
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null) {
                try {
                    is.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void gotoRegister() {
        Intent intent = new Intent(
                RegisterActivity.this,
                RegisterActivity.class
        );
        startActivity(intent);
    }
    public void gotoLogin() {
        Intent intent = new Intent(
                RegisterActivity.this,
                LoginActivity.class
        );
        startActivity(intent);
    }
    private void initViews() {
        edt_register_phone = findViewById(R.id.edt_register_phone);
        edt_register_password = findViewById(R.id.edt_register_password);
        btn_register = findViewById(R.id.btn_register);
    }
}