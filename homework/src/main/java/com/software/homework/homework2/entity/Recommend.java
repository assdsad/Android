package com.software.homework.homework2.entity;

import androidx.fragment.app.Fragment;

import com.software.homework.R;

import java.util.ArrayList;
import java.util.List;

public class Recommend extends Fragment {
    private Integer productImage;
    private String productName;
    private String productDescription;
    private String price;

    public Recommend(Integer productImage, String productName, String productDescription, String price) {
        this.productImage = productImage;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
    }

    public static List<Recommend> getRecommendList() {
        List<Recommend> recommendList = new ArrayList<>();
        recommendList.add(new Recommend(R.drawable.huawei, "华为", "这是华为手机", "5299"));
        recommendList.add(new Recommend(R.drawable.iphone, "iPhone", "这是苹果手机", "4299"));
        recommendList.add(new Recommend(R.drawable.oppo, "OPPO", "这是OPPO手机", "3299"));
        recommendList.add(new Recommend(R.drawable.rongyao, "荣耀", "这是荣耀手机", "2299"));
        recommendList.add(new Recommend(R.drawable.vivo, "VIVO", "这是VIVO手机", "1299"));
        recommendList.add(new Recommend(R.drawable.xiaomi, "小米", "这是小米手机", "1699"));

        return recommendList;
    }

    public Integer getProductImage() {
        return productImage;
    }

    public void setProductImage(Integer productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
