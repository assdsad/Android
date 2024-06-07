package com.software.finaltest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.software.finaltest.R;
import com.software.finaltest.adapters.GoodsAdapter;
import com.software.finaltest.entity.Goods;
import com.software.finaltest.entity.GoodsInfo;

import java.util.List;


public class GoodsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, null);
        GridView gv_goods = view.findViewById(R.id.gv_goods);


        GoodsAdapter adapter = new GoodsAdapter(
                this.getContext(),
                R.layout.item_goods
        );
        gv_goods.setAdapter(adapter);
//        List<Goods> goodsList = Goods.getGoodsList();
//        GoodsAdapter adapter = new GoodsAdapter(
//                this.getContext(),
//                R.layout.item_goods,
//                goodsList
//        );
//        gv_goods.setAdapter(adapter);
        return view;

    }
}
