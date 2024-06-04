package com.software.homework.homework2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.software.homework.R;
import com.software.homework.homework2.DB.ProductDB;
import com.software.homework.homework2.entity.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView iv_details_image;
    private TextView tv_details_name;
    private TextView tv_details_price;
    private TextView tv_details_description;
    private Button btn_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produce_details);

        initViews();

        initEvents();


    }

    private void initEvents() {
        //接收点击的商品的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Integer image = Integer.valueOf(bundle.getString("image"));
        String name = bundle.getString("name");
        String price = bundle.getString("price");
        String description = bundle.getString("description");

        //将点击的商品的详情显示在页面上
        iv_details_image.setImageResource(image);
        tv_details_name.setText(name);
        tv_details_price.setText(price);
        tv_details_description.setText(description);

        btn_buy.setOnClickListener(v -> {//点击 加入购物车 按钮

            //点击 加入购物车 按钮后，将购买的商品的数据打包
            Product product = new Product();
            product.setSelectedProductImage(image);
            product.setSelectedProductName(name);
            product.setSelectedProductPrice(price);
            product.setCount(1);
            //判断是否之前加入过购物车
            //如果之前加入过购物车，则再次点击 加入购物车 后，购物车中相应商品的数量加1
            int flag = 1;
            for(Product pd : ProductDB.productList) {
                if(pd.getSelectedProductName().equals(name)) {
                    pd.setCount(pd.getCount() + 1);
                    flag = 0;
                    break;
                }
            }
            //如果之前没加入购物车，则把该商品加入到list中
            if(flag == 1) {
                ProductDB.productList.add(product);
            }

            Toast.makeText(this,
                    "添加购物车成功",
                    Toast.LENGTH_SHORT
            ).show();

            Intent newIntent = new Intent(
                    ProductDetailsActivity.this,
                    ShoppingCartActivity.class
            );
            startActivity(newIntent);
        });
    }

    private void initViews() {
        iv_details_image = findViewById(R.id.iv_details_image);
        tv_details_name = findViewById(R.id.tv_details_name);
        tv_details_price = findViewById(R.id.tv_details_price);
        tv_details_description = findViewById(R.id.tv_details_description);
        btn_buy = findViewById(R.id.btn_buy);
    }
}