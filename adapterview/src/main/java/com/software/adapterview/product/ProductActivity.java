package com.software.adapterview.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.software.adapterview.R;
import com.software.adapterview.adapters.ProductAdapter;
import com.software.adapterview.entity.Product;

import java.util.List;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        //获取AdapterView
        GridView gv_product = findViewById(R.id.gv_product);
        //获取数据
        List<Product> productList = Product.getProductList();
        //创建适配器
        ProductAdapter productAdapter = new ProductAdapter(
                this,
                R.layout.item_product,
                productList
        );

        gv_product.setAdapter(productAdapter);

        //绑定事件（点击事件）
    }
}