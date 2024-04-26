package com.software.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class Tab_layout_test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_test);

        //获得TextView
        TabLayout tabLayout = findViewById(R.id.tt_nav);
        TextView tt_wechat = findViewById(R.id.tt_wechat);
        TextView tt_contract = findViewById(R.id.tt_contract);
        TextView tt_find = findViewById(R.id.tt_find);
        TextView tt_mine = findViewById(R.id.tt_mine);

        //让选中的组件对应的文字变成绿色
        int green = ContextCompat.getColor(this, R.color.green);
        int black = ContextCompat.getColor(this, R.color.black);

        tabLayout.setTabTextColors(black, green);//第一个参数是未选中时，第二个参数是选中时

        //创建四个小组件
        TabLayout.Tab wechat = tabLayout.newTab();
        wechat.setIcon(R.mipmap.msg);
        wechat.setText("消息");

        TabLayout.Tab contract = tabLayout.newTab();
        contract.setIcon(R.mipmap.contract);
        contract.setText("通讯录");

        TabLayout.Tab find = tabLayout.newTab();
        find.setIcon(R.mipmap.find);
        find.setText("发现");

        TabLayout.Tab mine = tabLayout.newTab();
        mine.setIcon(R.mipmap.mine);
        mine.setText("我的");

        //将小组件加到桌面
        tabLayout.addTab(wechat);
        tabLayout.addTab(contract);
        tabLayout.addTab(find);
        tabLayout.addTab(mine);

        //将每个小组件与其相对应的TextView建立联系，增加点击事件，点击小组件则跳转到其对应的TextView
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override//选中组件
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();//获取组件位置，0为消息  1为通讯录  2为发现  3为我的
                switch(position){
                    case 0://此时选中“消息”组件
                        //更新图标
                        wechat.setIcon(R.mipmap.msg);
                        contract.setIcon(R.mipmap.contract);
                        find.setIcon(R.mipmap.find);
                        mine.setIcon(R.mipmap.mine);

                        //设置对应TextView是否可见
                        tt_wechat.setVisibility(View.VISIBLE);
                        tt_contract.setVisibility(View.INVISIBLE);
                        tt_find.setVisibility(View.INVISIBLE);
                        tt_mine.setVisibility(View.INVISIBLE);
                        break;
                    case 1://此时选中“通讯录”组件
                        wechat.setIcon(R.mipmap.message);
                        contract.setIcon(R.mipmap.contract_green);
                        find.setIcon(R.mipmap.find);
                        mine.setIcon(R.mipmap.mine);

                        //设置对应TextView是否可见
                        tt_wechat.setVisibility(View.INVISIBLE);
                        tt_contract.setVisibility(View.VISIBLE);
                        tt_find.setVisibility(View.INVISIBLE);
                        tt_mine.setVisibility(View.INVISIBLE);
                        break;
                    case 2://此时选中“发现”组件
                        wechat.setIcon(R.mipmap.message);
                        contract.setIcon(R.mipmap.contract);
                        find.setIcon(R.mipmap.find_onclick);
                        mine.setIcon(R.mipmap.mine);

                        //设置对应TextView是否可见
                        tt_wechat.setVisibility(View.INVISIBLE);
                        tt_contract.setVisibility(View.INVISIBLE);
                        tt_find.setVisibility(View.VISIBLE);
                        tt_mine.setVisibility(View.INVISIBLE);
                        break;
                    case 3://此时选中“我的”组件
                        wechat.setIcon(R.mipmap.message);
                        contract.setIcon(R.mipmap.contract);
                        find.setIcon(R.mipmap.find);
                        mine.setIcon(R.mipmap.mine_onclick);

                        //设置对应TextView是否可见
                        tt_wechat.setVisibility(View.INVISIBLE);
                        tt_contract.setVisibility(View.INVISIBLE);
                        tt_find.setVisibility(View.INVISIBLE);
                        tt_mine.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override//不选中
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override//被重置
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}