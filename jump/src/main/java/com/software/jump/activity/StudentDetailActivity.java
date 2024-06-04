package com.software.jump.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.software.jump.R;
import com.software.jump.entity.Student;

import java.io.Serializable;

public class StudentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        //接收StudentListActivity跳转来时的信息
        //获取Intent
        Intent intent = getIntent();
        //第一种方式
//        String name = intent.getStringExtra("name");

        //第二种方式
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
//        int position = bundle.getInt("position");
//        String pwd = bundle.getString("pwd");

        //第三种接收方式
//        Student student = (Student) intent.getSerializableExtra("student");

        //获取控件
        EditText edt_student_name = findViewById(R.id.edt_student_name);
        //控件绑定数据   对应第一、二种方式
        edt_student_name.setText(name);
//        edt_student_name.setText(pwd);
//        edt_student_name.setText(String.valueOf(position));

        //对应第三种接收方式
//        edt_student_name.setText(student.getName());

        //获取返回按钮并绑定事件
        Button btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(v -> {
            //返回数据
            Intent back = new Intent();
            //将更改后的文本框的内容返回给StudentListActivity
//            back.putExtra("newStudentName", edt_student_name.getText().toString());//只返回name

            //更新bundle中的name信息
            bundle.putString("name", edt_student_name.getText().toString());
            back.putExtras(bundle);
            setResult(201, back);//第一个参数时状态码
            finish();
        });


        //打开另一个APP的Activity
        Button btn_other = findViewById(R.id.btn_other);
        btn_other.setOnClickListener(v -> {
            Intent other = new Intent();
            ComponentName componentName = new ComponentName(
                    "com.software.threadtext",
                    "com.software.threadtext.MainActivity"
            );
            other.setComponent(componentName);//通过类名加载Activity
            startActivity(other);
        });
    }
}