package com.software.notification.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;

import com.software.notification.R;

public class NotifyUtil {

    //把共用的
    private static final String CHANNEL_ID = "wechat";
    private static final String CHANNEL_NAME = "微信";
    private NotificationManager manager;

    private Context context;

    public NotifyUtil(Context context, Integer importance) {
        this.context = context;
        manager = (NotificationManager) context.getApplicationContext()
                .getSystemService(context.NOTIFICATION_SERVICE);
        //创建渠道
        createNotificationChannel(importance);
    }

    private void createNotificationChannel(Integer importance) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    importance
            );
            //设置声音
            channel.setSound(null, null);
            //图标上是否有小红点
            channel.setShowBadge(true);
            //是否跑马灯
            channel.enableLights(true);
            //推送是否振动
            channel.enableVibration(true);
            //锁屏是否显示
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            manager.createNotificationChannel(channel);
        }
    }

    public void sendNotify(String title, String message, Integer smallIcon, Integer largeIcon) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setSmallIcon(smallIcon);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeIcon));
        //进度条
        builder.setProgress(100, 60, true);
        //进度条显示计时器
        builder.setUsesChronometer(true);//计时, 显示通知发送了多长时间

        Notification notification = builder.build();
        manager.notify(R.string.app_name, notification);
    }
}
