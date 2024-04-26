package com.software.notification.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.software.notification.R;

public class ToastUtil {
    public static void show(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }

    //Toast显示自定义布局
    public static void show(Context context, String message, Integer layout){
        View view = LayoutInflater.from(context).inflate(layout, null);
        Toast toast = new Toast(context);
        toast.setView(view);
//        toast.setDuration();
        toast.show();
    }
}
