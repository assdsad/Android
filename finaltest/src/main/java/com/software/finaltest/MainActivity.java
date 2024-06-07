package com.software.finaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.software.finaltest.activity.LoginActivity;
import com.software.finaltest.activity.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_in_login = findViewById(R.id.btn_in_login);
        btn_in_login.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    LoginActivity.class
            );
            startActivity(intent);
        });
        Button btn_in_register = findViewById(R.id.btn_in_reister);
        btn_in_register.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });

    }
}