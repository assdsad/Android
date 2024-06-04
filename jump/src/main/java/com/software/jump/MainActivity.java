package com.software.jump;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.software.jump.activity.StudentListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_open = findViewById(R.id.btn_open);
        btn_open.setOnClickListener(v -> {
            //启动新的activity
            //Activity跳转的中介是Intent
            Intent intent = new Intent(
                    MainActivity.this,
                    StudentListActivity.class
            );
            startActivity(intent);
        });
    }
}