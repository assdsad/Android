package com.software.homework.homework2.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.homework.R;
import com.software.homework.homework2.DB.ProductDB;
import com.software.homework.homework2.activity.ShoppingCartActivity;
import com.software.homework.homework2.entity.Product;

import java.util.List;

public class ShoppingCartAdapter extends BaseAdapter {
    private Context context;
    private Integer layout;
    public ShoppingCartAdapter(Context context, Integer layout) {
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return ProductDB.productList.size();
    }

    @Override
    public Object getItem(int position) {
        return ProductDB.productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null);
        }

        ImageView iv_cart_image = convertView.findViewById(R.id.iv_cart_image);
        TextView tv_cart_name = convertView.findViewById(R.id.tv_cart_name);
        TextView tv_cart_price = convertView.findViewById(R.id.tv_cart_price);
        Button btn_cart_minus = convertView.findViewById(R.id.btn_cart_minus);
        TextView tv_cart_count = convertView.findViewById(R.id.tv_cart_count);
        Button btn_cart_plus = convertView.findViewById(R.id.btn_cart_plus);

        final Product current = ProductDB.productList.get(position);
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
                        ProductDB.productList.remove(position);
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
        Product product = ProductDB.productList.get(position);

        iv_cart_image.setImageResource(product.getSelectedProductImage());
        tv_cart_name.setText(product.getSelectedProductName());
        tv_cart_price.setText(product.getSelectedProductPrice());
        tv_cart_count.setText(String.valueOf(product.getCount()));

        return convertView;
    }
}
