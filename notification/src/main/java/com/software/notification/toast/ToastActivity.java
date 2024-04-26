package com.software.notification.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.software.notification.R;
import com.software.notification.utils.ToastUtil;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        Button btn_toast = findViewById(R.id.btn_toast);
        //通过创建ToastUtil类来实现功能
        btn_toast.setOnClickListener(v -> {
//            ToastUtil.show(this, "提示框");
            ToastUtil.show(this,"提示框", R.layout.dialog_custom);
        });
    }
}