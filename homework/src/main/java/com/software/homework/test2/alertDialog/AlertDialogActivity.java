package com.software.homework.test2.alertDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.software.homework.R;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_exit;
    private Button btn_custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        findViews();

        initEvents();

    }

    private void initEvents() {
        btn_exit.setOnClickListener(this);
        btn_custom.setOnClickListener(this);
    }

    private void findViews() {
        btn_exit = findViewById(R.id.btn_exit);
        btn_custom = findViewById(R.id.btn_custom);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_exit) {
            //点击之后创建对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.user);
            builder.setTitle("温馨提示");
            builder.setMessage("确定要退出吗");
            //设置确定按钮
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //退出
                    AlertDialogActivity.this.finish();
                }
            });
            //设置取消按钮
            builder.setNegativeButton("取消", null);
            //创建对话框
            AlertDialog dialog = builder.create();
            dialog.show();
        }else if(id == R.id.btn_custom) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.pwd);
            builder.setTitle("留作业");

            //获取自定义控件
            View view = getLayoutInflater().inflate(R.layout.layout_custom, null);
            Button btn_confirm = view.findViewById(R.id.btn_confirm);
            btn_confirm.setOnClickListener(w -> {
                TextView tv_hmw = view.findViewById(R.id.tv_hmw);
                tv_hmw.setText("放假了，不用写作业");
            });

            builder.setView(view);

            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }
}