package com.software.finaltest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.software.finaltest.DB.GoodsDB;
import com.software.finaltest.R;
import com.software.finaltest.adapters.ShoppingCartAdapter;
import com.software.finaltest.entity.SelectedGoods;

public class ShoppingCartActivity extends AppCompatActivity {

    private GridView gv_shoppingCart;
    private Button btn_back_to_shop;
    private TextView tv_total_price;
    private Button btn_pay;
    private Button btn_counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        initViews();
        initEvents();

        //获取适配器
        ShoppingCartAdapter adapter = new ShoppingCartAdapter(
                this,
                R.layout.item_shoppingcart
        );
        //绑定适配器
        gv_shoppingCart.setAdapter(adapter);

    }

    private void initEvents() {
        btn_back_to_shop.setOnClickListener(v -> {
            Intent intent = new Intent(
                    ShoppingCartActivity.this,
                    ShoppingActivity.class
            );
            startActivity(intent);
        });

        //计算总价格
        btn_counter.setOnClickListener(v -> {
            int count = 0;
            for(SelectedGoods product : GoodsDB.goodsList) {
                count += product.getCount() * Integer.valueOf(product.getPrice());
            }

            tv_total_price.setText(String.valueOf(count));
        });
    }

    private void initViews() {
        //获取GridView
        gv_shoppingCart = findViewById(R.id.gv_shoppingCart);
        btn_back_to_shop = findViewById(R.id.btn_back_to_shop);
        tv_total_price = findViewById(R.id.tv_total_price);
        btn_counter = findViewById(R.id.btn_counter);
        btn_pay = findViewById(R.id.btn_pay);
    }
}