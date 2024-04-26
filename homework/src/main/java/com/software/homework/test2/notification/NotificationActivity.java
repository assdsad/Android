package com.software.homework.test2.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.software.homework.R;
import com.software.homework.test2.utils.NotifyUtil;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edt_message;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findViews();
        initEvents();
    }

    private void initEvents() {
        btn_send.setOnClickListener(this);
    }

    private void findViews() {
        edt_message = findViewById(R.id.edt_message);
        btn_send = findViewById(R.id.btn_send);
    }

    @Override
    public void onClick(View v) {
//        //发送消息
//
//        //获取通知管理器
//        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//
//        //创建渠道
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(
//                    "wechat",
//                    "weixin",
//                    NotificationManager.IMPORTANCE_DEFAULT
//            );
//            manager.createNotificationChannel(channel);//设置同步管理器
//        }
//        //获取builder
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "wechat");
//            //设置通知的内容
//        builder.setSmallIcon(R.mipmap.message);
//        builder.setContentTitle("微信");
//        builder.setContentText(edt_message.getText());
//        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.msg));
//        //构建通知
//        Notification notification = builder.build();
//        //发送通知
//        manager.notify(R.string.app_name, notification);

        NotifyUtil notifyUtil = new NotifyUtil(this, NotificationManager.IMPORTANCE_DEFAULT);
        notifyUtil.sendNotify("微信",
                edt_message.getText().toString(),
                R.mipmap.message,
                R.mipmap.msg);

    }
}