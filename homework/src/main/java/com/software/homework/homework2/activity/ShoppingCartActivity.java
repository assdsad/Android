package com.software.homework.homework2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.homework.R;
import com.software.homework.homework2.DB.ProductDB;
import com.software.homework.homework2.adapters.ShoppingCartAdapter;
import com.software.homework.homework2.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    //设置一个list用于存储加入购物车的商品
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
            for(Product product : ProductDB.productList) {
                count += product.getCount() * Integer.valueOf(product.getSelectedProductPrice());
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