package com.software.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


private Button jump;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取view中的用户名的编辑框，给它设置值
        EditText edt_user = findViewById(R.id.edt_user);
        edt_user.setText("15727589528");//给用户名编辑框设置值

//        EditText button = (EditText)findViewById(R.id.btn2);//密码文本框
        Button button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               edt_user.setText("12312412541");//在按钮处显示括号内的文本
//                Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_LONG);
            }
        });
//        findView();
        jump = findViewById(R.id.jump);
        jump.setOnClickListener(this);


    }
//    private void findView() {
//        jump = findViewById(R.id.jump);
//    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.jump){
            startActivity(new Intent(this, WeixinActivity.class));
        }
    }

//    public void jump(View view){
//        startActivity(new Intent(this, LoginActivity.class));
//    }
}