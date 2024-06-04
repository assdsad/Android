package com.software.homework.homework2.entity;


import com.software.homework.R;

import java.util.ArrayList;
import java.util.List;

public class Product{
    private Integer selectedProductImage;
    private String selectedProductName;
    private String selectedProductPrice;
    private int count;//记录该件商品购买的数量

    public Integer getSelectedProductImage() {
        return selectedProductImage;
    }

    public void setSelectedProductImage(Integer selectedProductImage) {
        this.selectedProductImage = selectedProductImage;
    }

    public String getSelectedProductName() {
        return selectedProductName;
    }

    public void setSelectedProductName(String selectedProductName) {
        this.selectedProductName = selectedProductName;
    }

    public String getSelectedProductPrice() {
        return selectedProductPrice;
    }

    public void setSelectedProductPrice(String selectedProductPrice) {
        this.selectedProductPrice = selectedProductPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product(){}
    public Product(Integer selectedProductImage, String selectedProductName, String selectedProductPrice, int count) {
        this.selectedProductImage = selectedProductImage;
        this.selectedProductName = selectedProductName;
        this.selectedProductPrice = selectedProductPrice;
        this.count = count;
    }
}
