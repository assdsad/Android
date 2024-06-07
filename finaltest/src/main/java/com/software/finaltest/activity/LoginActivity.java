package com.software.finaltest.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.software.finaltest.DB.GoodsInfoDB;
import com.software.finaltest.R;
import com.software.finaltest.entity.Goods;
import com.software.finaltest.entity.GoodsInfo;
import com.software.finaltest.entity.UserInfo;
import com.software.finaltest.util.HostUtil;
import com.software.finaltest.util.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_login_username;
    private EditText edt_login_password;
    private Button btn_login;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                        Toast.makeText(
                                LoginActivity.this,
                                result.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    } else {
                        Toast.makeText(
                                LoginActivity.this,
                                result.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();

                        gotoShopping(result.getData());
                    }
                }
            }
        };
    }
    public void gotoShopping(UserInfo userInfo){
        GoodsInfoDB.goodsInfoList = userInfo.getGoodsInfoList();//获取从后端传过来的所有蛋糕的信息
        Intent intent = new Intent(
                LoginActivity.this,
                ShoppingActivity.class
        );
        startActivity(intent);

    }

    private void initEvents() {
        btn_login.setOnClickListener(v -> {
            String username = edt_login_username.getText().toString();
            String password = edt_login_password.getText().toString();

            new Thread(){
                @Override
                public void run() {
                    login(username, password);
                }
            }.start();
        });
    }

    public void login(String username, String password) {
        String path = HostUtil.PATH + "/login?username=" + username + "&password=" + password;

        InputStream is = null;
        BufferedReader br = null;
        try {
            URL url = new URL(path);
            //在Servlet中使用doPost方法
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);

            is = con.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();

            Gson gson = new Gson();
            Result<UserInfo> result = gson.fromJson(line, new TypeToken<Result<UserInfo>>(){}.getType());

            //使用handler方式转换到主线程
            Message message = handler.obtainMessage();
            message.what = 101;
            message.obj = result;
            handler.sendMessage(message);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initViews() {
        edt_login_username = findViewById(R.id.edt_login_username);
        edt_login_password = findViewById(R.id.edt_login_password);
        btn_login = findViewById(R.id.btn_login);
    }
}