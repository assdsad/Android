package com.software.homework.homework2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.homework.R;
import com.software.homework.homework2.entity.Recommend;

import java.util.List;

public class RecommendAdapter extends BaseAdapter {
    private Context context;
    private Integer layoutId;
    private List<Recommend> recommendList;
    public RecommendAdapter(Context context, Integer layoutId, List<Recommend> recommendList) {
        this.context = context;
        this.layoutId = layoutId;
        this.recommendList = recommendList;
    }

    @Override
    public int getCount() {
        return recommendList.size();
    }

    @Override
    public Object getItem(int position) {
        return recommendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(layoutId, null);
        }

        //获取图片，商品名，商品描述，商品价格
        ImageView iv_recommend_image = convertView.findViewById(R.id.iv_recommend_image);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_description = convertView.findViewById(R.id.tv_description);
        TextView tv_price = convertView.findViewById(R.id.tv_price);

        //绑定数据
        Recommend recommend = recommendList.get(position);
        iv_recommend_image.setImageResource(recommend.getProductImage());
        tv_name.setText(recommend.getProductName());
        tv_description.setText(recommend.getProductDescription());
        tv_price.setText(String.valueOf(recommend.getPrice()));
        return convertView;
    }
}
