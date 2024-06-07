package com.software.finaltest.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.software.finaltest.R;
import com.software.finaltest.adapters.GoodsFragmentAdapter;
import com.software.finaltest.entity.GoodsInfo;
import com.software.finaltest.fragment.GoodsFragment;
import com.software.finaltest.fragment.MineFragment;
import com.software.finaltest.fragment.OrderFormFragment;

import java.util.ArrayList;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity {

    private ViewPager2 vp_context;
    private TabLayout tb_nav;
    private List<Fragment> fragmentList;
    private List<String> tab_namelist;
    private List<GoodsInfo> goodsInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        initViews();

        initFragments();

        //获取跳转时传递过来的蛋糕信息
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        goodsInfoList = bundle.getParcelableArrayList("goodslist");

        //创建适配器
        GoodsFragmentAdapter adapter = new GoodsFragmentAdapter(
                fragmentList, this
        );
        //给ViewPager2绑定适配器
        vp_context.setAdapter(adapter);

        //TabLayout与ViewPager2关联
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tb_nav,
                vp_context,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tab_namelist.get(position));
                    }
                }
        );
        int red = ContextCompat.getColor(this, R.color.red);
        tb_nav.setSelectedTabIndicatorColor(red);
        //使结果生效
        tabLayoutMediator.attach();
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new GoodsFragment());
        fragmentList.add(new OrderFormFragment());
        fragmentList.add(new MineFragment());

        tab_namelist = new ArrayList<>();
        tab_namelist.add("商品");
        tab_namelist.add("订单");
        tab_namelist.add("我的");
    }

    private void initViews() {
        vp_context = findViewById(R.id.vp_context);
        tb_nav = findViewById(R.id.tb_nav);
    }
}