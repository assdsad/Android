package com.software.finaltest.entity;

import com.software.finaltest.R;

import java.util.ArrayList;
import java.util.List;

public class Goods {
    private Integer goodsImage;
    private String goodsName;
    private String goodsDescription;
    private String price;


    public Goods(Integer goodsImage, String goodsName, String goodsDescription, String price) {
        this.goodsImage = goodsImage;
        this.goodsName = goodsName;
        this.goodsDescription = goodsDescription;
        this.price = price;
    }

    public static List<Goods> getGoodsList() {
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods(R.drawable.huawei, "华为", "这是华为手机", "5299"));
        goodsList.add(new Goods(R.drawable.iphone, "iPhone", "这是苹果手机", "4299"));
        goodsList.add(new Goods(R.drawable.oppo, "OPPO", "这是OPPO手机", "3299"));
        goodsList.add(new Goods(R.drawable.rongyao, "荣耀", "这是荣耀手机", "2299"));
        goodsList.add(new Goods(R.drawable.vivo, "VIVO", "这是VIVO手机", "1299"));
        goodsList.add(new Goods(R.drawable.xiaomi, "小米", "这是小米手机", "1699"));

        return goodsList;
    }

    public Integer getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(Integer goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
