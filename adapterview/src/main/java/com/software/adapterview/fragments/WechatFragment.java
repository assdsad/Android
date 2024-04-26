package com.software.adapterview.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.software.adapterview.R;
import com.software.adapterview.adapters.WechatAdapter;
import com.software.adapterview.entity.Wechat;

import java.util.List;

public class WechatFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //绑定布局
        View view = inflater.inflate(R.layout.fragment_wechat, null);
        ListView lv_wechat = view.findViewById(R.id.lv_wechat);
        //获取数据源
        List<Wechat> wechatList = Wechat.getWechatList();
        //获取适配器
        WechatAdapter adapter = new WechatAdapter(
                this.getContext(),
                R.layout.item_wechat,
                wechatList
        );
        lv_wechat.setAdapter(adapter);

        return view;//返回视图
    }
}
