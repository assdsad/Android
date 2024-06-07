package com.software.finaltest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.software.finaltest.R;
import com.software.finaltest.entity.UserInfo;
import com.software.finaltest.util.HostUtil;
import com.software.finaltest.util.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {

    private EditText edt_register_username;
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
        btn_register.setOnClickListener(v -> {
            String username = edt_register_username.getText().toString();
            String password = edt_register_password.getText().toString();

            //启动子线程，将请求发送给服务器端
            new Thread(){
                @Override
                public void run() {
                    register(username, password);
                }
            }.start();
        });
    }

    public void register(String username, String password) {
        InputStream is = null;
        //后端 把用户名和密码输出在浏览器页面上，根据url去页面上读取
        try {
            URL url = new URL(HostUtil.PATH + "/register?username=" + username + "&password=" + password);
            is = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            //后端返回的是JSONUtil.toJsonStr(result)对象，先把line转换成对象
            Gson gson = new Gson();
            Result result = gson.fromJson(line, new TypeToken<Result<UserInfo>>(){}.getType());

            //判断result中的code，判断是否注册成功
            if(result.getCode() == 200) {//注册成功
                //在主线程中提示注册成功， 并跳转到登录页面
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                RegisterActivity.this,
                                result.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();

                        gotoLogin();
                    }
                });
            } else if(result.getCode() == 500) {//用户名或密码为空
                //在主线程中提示，并跳转回注册页面
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                RegisterActivity.this,
                                result.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();

                        gotoRegister();
                    }
                });
            } else if(result.getCode() == 409) {//用户名已存在
                //在主线程中提示，并跳转回注册页面
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

        } catch (Exception e) {
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

    private void gotoLogin() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }
    private void gotoRegister() {
        startActivity(new Intent(RegisterActivity.this, RegisterActivity.class));
    }

    private void initViews() {
        edt_register_username = findViewById(R.id.edt_register_username);
        edt_register_password = findViewById(R.id.edt_register_password);
        btn_register = findViewById(R.id.btn_register);
    }
}