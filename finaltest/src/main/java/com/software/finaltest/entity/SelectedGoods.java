package com.software.finaltest.entity;

public class SelectedGoods {
//    private Integer Image;
    private String image;
    private String name;
    private String price;
    private int count;
    public SelectedGoods(){}

    public SelectedGoods(String image, String name, String price, int count) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.count = count;
    }
    //    public SelectedGoods(Integer image, String name, String price, int count) {
//        Image = image;
//        this.name = name;
//        this.price = price;
//        this.count = count;
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
//    public Integer getImage() {
//        return Image;
//    }
//
//    public void setImage(Integer image) {
//        Image = image;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
