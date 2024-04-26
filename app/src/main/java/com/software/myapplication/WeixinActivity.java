package com.software.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeixinActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin2);



        textView = findViewById(R.id.btn);
        textView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn){
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}