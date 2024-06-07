package com.software.finaltest.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.software.finaltest.DB.GoodsDB;
import com.software.finaltest.DB.GoodsInfoDB;
import com.software.finaltest.R;
import com.software.finaltest.activity.GoodsDetailActivity;
import com.software.finaltest.entity.Goods;
import com.software.finaltest.entity.GoodsInfo;
import com.software.finaltest.entity.SelectedGoods;

import java.util.List;

public class GoodsAdapter extends BaseAdapter {
    private Context context;
    private Integer layout_id;
    private List<Goods> goodsList;
    public GoodsAdapter(Context context, Integer layout_id) {
        this.context = context;
        this.layout_id = layout_id;

    }

//    public GoodsAdapter(Context context, Integer layout_id, List<Goods> goodsList) {
//        this.context = context;
//        this.layout_id = layout_id;
//        this.goodsList = goodsList;
//    }
    @Override
    public int getCount() {
//        return goodsList.size();
        return GoodsInfoDB.goodsInfoList.size();
    }

    @Override
    public Object getItem(int position) {
//        return goodsList.get(position);
        return GoodsInfoDB.goodsInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(layout_id, null);
        }

        //获取图片，商品名，商品描述，商品价格
        ImageView iv_goods_image = convertView.findViewById(R.id.iv_goods_image);
        TextView tv_goods_name = convertView.findViewById(R.id.tv_goods_name);
        TextView tv_goods_description = convertView.findViewById(R.id.tv_goods_description);
        TextView tv_goods_price = convertView.findViewById(R.id.tv_goods_price);

        convertView.setOnClickListener(v -> {
            //获取到点击的商品
//            Goods item = (Goods) getItem(position);
            GoodsInfo item = (GoodsInfo) getItem(position);
            Intent intent = new Intent(
                    parent.getContext(),
                    GoodsDetailActivity.class
            );
            Bundle bundle = new Bundle();
            bundle.putString("image", String.valueOf(item.getImg()));
            bundle.putString("name", item.getName());
            bundle.putString("price", String.valueOf(item.getPrice()));
            bundle.putString("description", item.getDescription());
            bundle.putInt("count", item.getCount());
            intent.putExtras(bundle);
            parent.getContext().startActivity(intent);

        });
        GoodsInfo goods = GoodsInfoDB.goodsInfoList.get(position);
        Glide.with(parent.getContext()).asBitmap().load(goods.getImg()).into(iv_goods_image);
        tv_goods_name.setText(goods.getName());
        tv_goods_description.setText(goods.getDescription());
        tv_goods_price.setText("$ " + String.valueOf(goods.getPrice()));

//        Goods goods = goodsList.get(position);
//        iv_goods_image.setImageResource(goods.getGoodsImage());
//        tv_goods_name.setText(goods.getGoodsName());
//        tv_goods_description.setText(goods.getGoodsDescription());
//        tv_goods_price.setText(String.valueOf(goods.getPrice()));

        return convertView;
    }
}
