package com.software.homework.test2.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.software.homework.R;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        Button btn_toast = findViewById(R.id.btn_toast);
        btn_toast.setOnClickListener(v -> {
            Toast.makeText(this,
                    "提示框",
                    Toast.LENGTH_SHORT).show();
        });
    }
}