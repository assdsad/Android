package com.software.adapterview.entity;

import com.software.adapterview.R;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String productName;
    private Integer productImage;
    private Double productPrice;
    public static List<Product> getProductList(){
        List<Product> productList = new ArrayList<>();

        productList.add(new Product("华为", R.drawable.huawei, 6299.00));
        productList.add(new Product("荣耀", R.drawable.rongyao, 6299.00));
        productList.add(new Product("iphone", R.drawable.iphone, 5299.00));
        productList.add(new Product("vivo", R.drawable.vivo, 7299.00));
        productList.add(new Product("oppo", R.drawable.oppo, 8299.00));
        productList.add(new Product("xiaomi", R.drawable.xiaomi, 3299.00));

        return productList;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductImage() {
        return productImage;
    }

    public void setProductImage(Integer productImage) {
        this.productImage = productImage;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
    public Product(){}

    public Product(String productName, Integer productImage, Double productPrice){
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
    }


}
