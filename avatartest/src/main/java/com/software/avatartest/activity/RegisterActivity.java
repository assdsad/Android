package com.software.avatartest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.software.avatartest.R;
import com.software.avatartest.entity.Userinfo;
import com.software.avatartest.utils.HostUtil;
import com.software.avatartest.utils.Result;

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
        btn_register.setOnClickListener(v -> {
            //点击注册按钮后，获取到用户名和密码
            String phone = edt_register_phone.getText().toString();
            String password = edt_register_password.getText().toString();

            //将获取到的用户名和密码通过网络转发给后端服务器
            new Thread(){
                @Override
                public void run() {
                    register(phone, password);
                }
            }.start();
        });
    }

    private void register(String phone, String password) {
        InputStream is = null;
        try {
            //后端程序把用户名和密码输出到页面上，此时根据url去页面上读取数据
            URL url = new URL(HostUtil.PATH + "/register?phone=" + phone + "&password=" + password);
            is  = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String json = br.readLine();
            Log.i("RegisterActivity", json);
            //在后端程序中，不论是否注册成功，返回的都是JSONUtil.toJsonStr(result)对象,,所以要把json转换成对象
            Gson gson = new Gson();
            Result result = gson.fromJson(json, new TypeToken<Result<Userinfo>>(){}.getType());

            //判断result中的状态码，以得到是否注册成功
            if(result.getCode() == 200) {//注册成功
                //在主线程中提示注册成功，并跳转到登陆页面
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
            } else if(result.getCode() == 409) {//用户名一存在
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

            }
        } catch (IOException e) {
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

    private void gotoRegister() {
        Intent intent = new Intent(
                RegisterActivity.this,
                RegisterActivity.class
        );
        startActivity(intent);
    }

    private void gotoLogin() {
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