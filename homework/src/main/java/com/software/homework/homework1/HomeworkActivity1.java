package com.software.homework.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.software.homework.R;

public class HomeworkActivity1 extends AppCompatActivity implements View.OnClickListener{

    private EditText username;
    private EditText password;
    private TextView forget;
    private Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework1);

        findView();

        //设置点击事件
        forget.setOnClickListener(this);
        login_btn.setOnClickListener(this);
    }

    public void findView(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forget = findViewById(R.id.forget);
        login_btn = findViewById(R.id.login_btn);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        int usernameLength = username.getText().toString().length();
        int passwordLength = password.getText().toString().length();
        if(id == R.id.login_btn && usernameLength != 0 && passwordLength != 0 && passwordLength >= 6){
            Toast.makeText(this,
                    "恭喜，登录成功!",
                    Toast.LENGTH_SHORT).show();
            System.out.println("恭喜，登录成功!");
        } else if(id == R.id.login_btn && usernameLength != 0 && passwordLength < 6) {
            Toast.makeText(this,
                    "密码长度小于6!",
                    Toast.LENGTH_SHORT).show();
            System.out.println("密码长度小于6!");
        } else {
            Toast.makeText(this,
                    "用户名或密码不能为空!",
                    Toast.LENGTH_SHORT).show();
            System.out.println("用户名或密码不能为空!");
        }
    }
}