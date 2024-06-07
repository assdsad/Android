package com.software.finaltest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.software.finaltest.R;

public class MineActivity extends AppCompatActivity {

    private Button btn_goto_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        initViews();
        initEvents();
    }

    private void initEvents() {
//        btn_goto_cart.setOnClickListener(v -> {
//            Intent intent = new Intent(
//                    MineActivity.this,
//                    ShoppingCartActivity.class
//            );
//            startActivity(intent);
//        });
    }

    private void initViews() {
        btn_goto_cart = findViewById(R.id.btn_goto_cart);
    }
}