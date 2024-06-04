package com.software.homework.homework2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.BaseAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.software.homework.R;
import com.software.homework.homework2.adapters.RecommendFragmentAdapter;
import com.software.homework.homework2.fragments.ClothFragment;
import com.software.homework.homework2.fragments.FoodFragment;
import com.software.homework.homework2.fragments.GoodsFragment;
import com.software.homework.homework2.fragments.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity {
    private ViewPager2 vp_context;
    private TabLayout tb_nav;
    private List<Fragment> fragmentList;
    private List<String> tabNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        findViews();//获取控件

        initFragments();
        //创建适配器
        RecommendFragmentAdapter adapter = new RecommendFragmentAdapter(
                fragmentList, this
        );

        //给ViewPager2绑定适配器
        vp_context.setAdapter(adapter);

        //TabLayout与ViewPager2关联
        TabLayoutMediator mediator = new TabLayoutMediator(
                tb_nav,
                vp_context,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabNameList.get(position));
                    }
                }
        );
        int red = ContextCompat.getColor(this, R.color.red);
//        tb_nav.setBackgroundColor(red);
        tb_nav.setSelectedTabIndicatorColor(red);//设置选中项下划线为红色
        //是效果生效
        mediator.attach();


    }

    private void initFragments() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new RecommendFragment());
        fragmentList.add(new GoodsFragment());
        fragmentList.add(new FoodFragment());
        fragmentList.add(new ClothFragment());

        tabNameList = new ArrayList<>();
        tabNameList.add("推荐");
        tabNameList.add("百货");
        tabNameList.add("食物");
        tabNameList.add("衣物");
    }

    private void findViews() {
        vp_context = findViewById(R.id.vp_context);
        tb_nav = findViewById(R.id.tb_nav);
    }
}