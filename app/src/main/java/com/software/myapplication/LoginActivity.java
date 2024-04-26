package com.software.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_login;
    private Button btn_register;
    private EditText edt_name;
    private EditText edt_pwd;
    private Button jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //设置背景图透明度
        LinearLayout layout_bg = findViewById(R.id.layou_bg);
        layout_bg.getBackground().setAlpha(100);

        //获取控件，按钮，文本框
        findView();

////        jdk1.8之后，可采用Lamda表达式
//        btn_login.setOnClickListener(v -> {
//            String name = edt_name.getText().toString();
//            String pwd = edt_pwd.getText().toString();
//
//            //Toast用于对话框提示
//            //第一个参数是上下文，用于显示在哪个Activity实例
//            //第二个参数是显示的内容
//            //第三个参数是显示的时长
//            Toast.makeText(LoginActivity.this, name + ":" + pwd, Toast.LENGTH_SHORT).show();
//        });
//
//        //点击注册按钮
//        btn_register.setOnClickListener(v -> {
//            Toast.makeText(LoginActivity.this, "欢迎注册我的APP", Toast.LENGTH_SHORT).show();
//        });



        //第一种绑定方式使用匿名内部类方式绑定
//        btn_login.setOnClickListener(new View.OnClickListener(){//实现一个接口
//
//            @Override
//            public void onClick(View v) {
//                //获取用户名和密码的内容
//                String name = edt_name.getText().toString();
//                String pwd = edt_pwd.getText().toString();
//
//                //对话框提示
//                //第一个参数是上下文，用于显示在哪个Activity实例
//                //第二个参数是显示的内容
//                //第三个参数是显示的时长
//                Toast.makeText(LoginActivity.this, name + ":" + pwd, Toast.LENGTH_SHORT).show();
//            }
//        });

//        //第二种绑定方式,成员内部类
//        //创建一个对象,与成员内部类使用
//        LoginListener listener = new LoginListener();
//        btn_login.setOnClickListener(listener);
//        btn_register.setOnClickListener(listener);


        //第三种绑定方式
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        jump.setOnClickListener(this);
    }



    private void findView() {
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        edt_name = findViewById(R.id.edt_name);
        edt_pwd = findViewById(R.id.edt_pwd);
        jump = findViewById(R.id.jump);
    }

    //按钮多时可采用这种方式，只创建一个对象就行
    //第二种绑定方式
    class LoginListener implements View.OnClickListener{//成员内部类

        @Override
        public void onClick(View v) {
            //View v 参数，代表就是用户点击的那一个按钮
            int id = v.getId();//获取点击的那个按钮的id
            //用户点击控件的id与资源文件中的id进行对比，就知道是哪一个
            if(id == R.id.btn_login){
                String name = edt_name.getText().toString();
                String pwd = edt_pwd.getText().toString();
                //对话框提示
                //第一个参数是上下文，用于显示在哪个Activity实例
                //第二个参数是显示的内容
                //第三个参数是显示的时长
                Toast.makeText(LoginActivity.this, name + ":" + pwd, Toast.LENGTH_SHORT).show();
            }else if(id == R.id.btn_register){
                Toast.makeText(LoginActivity.this, "欢迎注册我的APP", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //第三种绑定方式，主类直接实现View.OnClickListener接口
    @Override
    public void onClick(View v) {
        int id = v.getId();//获取点击的那个按钮的id
        //用户点击控件的id与资源文件中的id进行对比，就知道是哪一个
        if(id == R.id.btn_login){
            String name = edt_name.getText().toString();
            String pwd = edt_pwd.getText().toString();
            //对话框提示
            //第一个参数是上下文，用于显示在哪个Activity实例
            //第二个参数是显示的内容
            //第三个参数是显示的时长
            Toast.makeText(LoginActivity.this, name + ":" + pwd, Toast.LENGTH_SHORT).show();
        }else if(id == R.id.btn_register){
            Toast.makeText(LoginActivity.this, "欢迎注册", Toast.LENGTH_SHORT).show();
            System.out.println("我注册了一个用户");
            Log.i("LoginActivity", "用户注册了一个");//安卓的日志工具
        } else if(id == R.id.jump){
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}