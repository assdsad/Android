package com.software.finaltest.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.software.finaltest.DB.GoodsDB;
import com.software.finaltest.R;
import com.software.finaltest.activity.GoodsDetailActivity;
import com.software.finaltest.entity.Goods;
import com.software.finaltest.entity.SelectedGoods;

public class ShoppingCartAdapter extends BaseAdapter {
    private Context context;
    private Integer layout_id;

    public ShoppingCartAdapter(Context context, Integer layout_id) {
        this.context = context;
        this.layout_id = layout_id;
    }

    @Override
    public int getCount() {
        return GoodsDB.goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return GoodsDB.goodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout_id, null);
        }
        ImageView iv_cart_image = convertView.findViewById(R.id.iv_cart_image);
        TextView tv_cart_name = convertView.findViewById(R.id.tv_cart_name);
        TextView tv_cart_price = convertView.findViewById(R.id.tv_cart_price);
        Button btn_cart_minus = convertView.findViewById(R.id.btn_cart_minus);
        TextView tv_cart_count = convertView.findViewById(R.id.tv_cart_count);
        Button btn_cart_plus = convertView.findViewById(R.id.btn_cart_plus);

        final SelectedGoods current = GoodsDB.goodsList.get(position);
        btn_cart_minus.setOnClickListener(v -> {
            if(current.getCount() != 1) {
                current.setCount(current.getCount() - 1);
            } else {
                //数量为0时删除该商品
                //增加提示框  是否删除
//                ShoppingCartActivity.productList.remove(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setMessage("确定要删除该商品吗?");
                //点击确定后删除该商品
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GoodsDB.goodsList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.setCancelable(false);
                //生成对话框
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            //通知适配器数据源改变了
            notifyDataSetChanged();
        });

        //点击+号数量增加一
        btn_cart_plus.setOnClickListener(v -> {
            current.setCount(current.getCount() + 1);
            //通知适配器数据源改变
            notifyDataSetChanged();
        });

        //绑定数据
        SelectedGoods goods = GoodsDB.goodsList.get(position);

//        iv_cart_image.setImageResource(goods.getImage());
        Glide.with(parent.getContext()).asBitmap().load(goods.getImage()).into(iv_cart_image);
        tv_cart_name.setText(goods.getName());
        tv_cart_price.setText("$ " + goods.getPrice());
        tv_cart_count.setText(String.valueOf(goods.getCount()));
        return convertView;
    }
}
