package com.software.adapterview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.adapterview.R;
import com.software.adapterview.entity.Wechat;

import java.util.List;

//获取数据，对数据进行操作
//给视图绑定数据

public class WechatAdapter extends BaseAdapter {
    private Context context;
    private Integer layoutId;//item_wechat布局
    private List<Wechat> wechatList;
    //模拟官方给出的
    public WechatAdapter(Context context, Integer layoutId, List<Wechat> wechatList){
        this.context = context;
        this.layoutId = layoutId;
        this.wechatList = wechatList;
    }

    //获取数据源中数据的个数
    @Override
    public int getCount() {
        return wechatList.size();
    }
    //获取数据， position下标位置的数据
    @Override
    public Object getItem(int position) {
        return wechatList.get(position);
    }
    //获取数据下标的位置
    @Override
    public long getItemId(int position) {
        return position;
    }

    //拿到视图，绑定数据，再返回绑定完数据的视图
    //拿到一个未绑定数据的视图 convertView
    //绑定数据
    //返回绑定好数据的视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取视图
        if(convertView == null) {
            //LayooutInflater视图加载器
            convertView = LayoutInflater.from(this.context).inflate(layoutId, null);
        }
        //获取视图中的头像， 昵称， 最后一条消息  进行数据绑定
        ImageView iv_avatar = convertView.findViewById(R.id.iv_avatar);
        TextView tv_nickName = convertView.findViewById(R.id.tv_nickname);
        TextView tv_endMessage = convertView.findViewById(R.id.tv_endMessage);
        //绑定数据
        Wechat wechat = wechatList.get(position);
        iv_avatar.setImageResource(wechat.getAvatar());
        tv_nickName.setText(wechat.getNickName());
        tv_endMessage.setText(wechat.getEndMessage());
        return convertView;
    }
}
