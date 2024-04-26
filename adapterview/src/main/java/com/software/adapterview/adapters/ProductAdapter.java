package com.software.adapterview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.adapterview.R;
import com.software.adapterview.entity.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private Integer layout;
    private List<Product> productList;
    public ProductAdapter(Context context, Integer layout, List<Product> productList){
        this.context = context;
        this.layout = layout;
        this.productList = productList;

    }
    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            //视图加载器
            convertView = LayoutInflater.from(context).inflate(layout, null);
        }
        TextView tv_product_name = convertView.findViewById(R.id.tv_product_name);
        ImageView iv_product_image = convertView.findViewById(R.id.iv_product_image);
        TextView tv_product_price = convertView.findViewById(R.id.tv_product_price);
        //绑定数据
        Product product = productList.get(position);
        tv_product_name.setText(product.getProductName());
        iv_product_image.setImageResource(product.getProductImage());
        tv_product_price.setText(String.valueOf(product.getProductPrice()));//把价格转化成字符串
        return convertView;
    }
}
