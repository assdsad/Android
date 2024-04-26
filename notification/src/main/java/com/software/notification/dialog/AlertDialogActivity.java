package com.software.notification.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.software.notification.R;
import com.software.notification.toast.ToastActivity;

import java.util.Calendar;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_exit;
    private Button btn_date;
    private TextView tv_date;
    private Button btn_time;
    private TextView tv_time;
    private Button btn_custom;
    private Button btn_jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        initViews();//初始化views里的控件
        initEvents();//创建事件
    }

    //初始化事件
    private void initEvents() {//根据接口来设置点击事件
        btn_exit.setOnClickListener(this);
        btn_date.setOnClickListener(this);
        btn_time.setOnClickListener(this);
        btn_custom.setOnClickListener(this);
        btn_jump.setOnClickListener(this);
    }

    //初始化控件
    private void initViews() {
        btn_exit = findViewById(R.id.btn_exit);
        //日期
        btn_date = findViewById(R.id.btn_date);
        tv_date = findViewById(R.id.tv_date);

        //时间
        btn_time = findViewById(R.id.btn_time);
        tv_time = findViewById(R.id.tv_time);

        btn_custom = findViewById(R.id.btn_custom);//自定义按钮

        btn_jump = findViewById(R.id.btn_jump);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_exit) {
            //点击之后，创建对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //设置对话框属性
            builder.setIcon(R.mipmap.message);
            builder.setTitle("温馨提示");
            builder.setMessage("您确定要退出系统吗？");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //退出应用程序
                    AlertDialogActivity.this.finish();
                }
            });//确定按钮

            builder.setNegativeButton("取消", null);//取消按钮
            builder.setCancelable(false);//点空白的地方无法退出
            //生成对话框
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if(id == R.id.btn_date) {
            Calendar calendar = Calendar.getInstance();
            //创建时间对话框
            DatePickerDialog dialog = new DatePickerDialog(
                    this,
                    new DatePickerDialog.OnDateSetListener() {//也可在类中实现接口，这里换成this就行
                        //日期选中后要做的事情
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            //放到文本框中
                            String desc = String.format("您选择的日期是：%d年%d月%d日", year, month + 1, dayOfMonth);//year代替第一个%d,依次类推，，，，代替了拼接字符串
                            tv_date.setText(desc);//把选择的日期放入TextView文本框中
                        }
                    },
                    //得到年月日
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            dialog.show();//显示出来
        } else if(id == R.id.btn_time) {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog dialog = new TimePickerDialog(
                    this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            String desc = String.format("您选择的日期是：%d时%d分", hourOfDay, minute);//hourOfDay代替第一个%d,依次类推，，，，代替了拼接字符串
                            tv_time.setText(desc);//把选择的时间放入TextView文本框中
                        }
                    },
                    //得到小时，分钟
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),//下方为true是24小时制， 为false是12小时制
                    true
            );
            dialog.show();
        } else if(id == R.id.btn_custom) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //构建自己自定义的布局
            builder.setTitle("留作业");

            //相当于message部分
            //获取自定义的布局文件
            View view = getLayoutInflater().inflate(R.layout.dialog_custom, null);
            //给自定义的视图控件绑定事件，需要使用View.findViewById()
            Button btn_ok = view.findViewById(R.id.btn_ok);
            btn_ok.setOnClickListener(w -> {
                TextView tv_homework = view.findViewById(R.id.tv_homework);//得到组件
                tv_homework.setText("放假了");//修改文本
            });

            builder.setView(view);//message部分是自己定义的布局

            //按钮   与第一个“退出系统”相同

            AlertDialog dialog = builder.create();
            dialog.show();
        } else if(id == R.id.btn_jump){
            startActivity(new Intent(this, ToastActivity.class));
        }
    }
}