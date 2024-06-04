package com.software.avatartest.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_login_phone;
    private EditText edt_login_password;
    private Button btn_login;
    private TextView tv_register;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //获取控件
        initViews();
        //绑定事件
        initEvents();

        initHandler();

    }

    private void initHandler() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what == 101) {
                    Result<Userinfo> result = (Result<Userinfo>) msg.obj;
                    if(result.getCode() != 200) {//登录失败
                        Toast.makeText(
                                LoginActivity.this,
                                result.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }else{//登录成功
                        Toast.makeText(
                                LoginActivity.this,
                                result.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();

                        //并跳转到  我的  页面
                        gotoMine(result.getData());
                    }
                }
            }
        };
    }

    private void gotoMine(Userinfo userinfo) {
        Intent intent = new Intent(
                LoginActivity.this,
                MineActivity.class
        );
        //登录成功后，点击退出直接退出app
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        Bundle bundle = new Bundle();
        bundle.putString("phone", userinfo.getPhone());
        bundle.putString("password", userinfo.getPassword());
        bundle.putString("avatar", userinfo.getAvatar());
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void initEvents() {
        //点击注册跳转到注册页面
        tv_register.setOnClickListener(v -> {
            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );
            startActivity(intent);
        });

        //点击登录按钮，将获取到的用户名和密码转发给servlet
        btn_login.setOnClickListener(v -> {
            String phone = edt_login_phone.getText().toString();
            String password = edt_login_password.getText().toString();
            new Thread(){
                @Override
                public void run() {
                    login(phone, password);
                }
            }.start();
        });
    }

    private void login(String phone, String password) {
        String path = HostUtil.PATH + "/login?phone=" + phone + "&password=" + password;

        InputStream is = null;
        BufferedReader br = null;
        try {
            URL url = new URL(path);
            //由于在servelt中使用doPost方法，所以在此处要指明
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            is = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            //转换成对象
            Gson gson = new Gson();
            Result<Userinfo> result = gson.fromJson(line, new TypeToken<Result<Userinfo>>(){}.getType());

            //使用handler方式转到主线程
            Message message = handler.obtainMessage();
            message.what = 101;
            message.obj = result;
            handler.sendMessage(message);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void initViews() {
        edt_login_phone = findViewById(R.id.edt_login_phone);
        edt_login_password = findViewById(R.id.edt_login_password);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);
    }
}