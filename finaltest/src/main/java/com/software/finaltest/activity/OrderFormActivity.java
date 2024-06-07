package com.software.finaltest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.software.finaltest.R;
import com.software.finaltest.adapters.ShoppingCartAdapter;

public class OrderFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);

    }
}