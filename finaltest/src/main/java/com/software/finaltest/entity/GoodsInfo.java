package com.software.finaltest.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class GoodsInfo implements Parcelable {
    private int id;
    private String name;
    private int price;
    private int count;
    private String img;
    private String description;
    public GoodsInfo(){}

    protected GoodsInfo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readInt();
        count = in.readInt();
        img = in.readString();
        description = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeInt(count);
        dest.writeString(img);
        dest.writeString(description);
    }

    public static final Creator<GoodsInfo> CREATOR = new Creator<GoodsInfo>() {
        @Override
        public GoodsInfo createFromParcel(Parcel in) {
            return new GoodsInfo(in);
        }

        @Override
        public GoodsInfo[] newArray(int size) {
            return new GoodsInfo[size];
        }
    };

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
