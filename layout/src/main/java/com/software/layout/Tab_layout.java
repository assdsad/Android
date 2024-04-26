package com.software.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.software.layout.adapters.ContractAdapter;
import com.software.layout.adapters.WechatAdapter;
import com.software.layout.entity.Contract;
import com.software.layout.entity.Wechat;

import java.util.List;

public class Tab_layout extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        TabLayout tabLayout = findViewById(R.id.tab_nav);//将标签放入其中
        //聊天列表
        ListView lv_wechat = findViewById(R.id.lv_wechat);
        //获取数据
        List<Wechat> wechatList = Wechat.getWechatList();//把模拟的数据存入wechatlist中
        //与数据绑定，自定义
        //创建自定义的适配器
        WechatAdapter wechatAdapter = new WechatAdapter(
                this,
                R.layout.item_wechat,
                wechatList
        );
        //绑定适配器
        lv_wechat.setAdapter(wechatAdapter);
        //设置监听事件
        lv_wechat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Tab_layout.this,
                        wechatList.get(position).getNickName(),
                        Toast.LENGTH_LONG).show();
            }
        });


        ListView lv_contract = findViewById(R.id.lv_contract);
        List<Contract> contractList = Contract.getContractList();
        ContractAdapter contractAdapter = new ContractAdapter(
                this,
                R.layout.item_contract,
                contractList
        );
        lv_contract.setAdapter(contractAdapter);//绑定适配器
        lv_contract.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Tab_layout.this,
                        contractList.get(position).getContractText(),
                        Toast.LENGTH_SHORT).show();
            }
        });


        TextView tv_find = findViewById(R.id.tv_find);
        TextView tv_mine = findViewById(R.id.tv_mine);

        //给tablayout设置颜色
        int green = ContextCompat.getColor(this, R.color.green);
        int black = ContextCompat.getColor(this, R.color.black);

        tabLayout.setTabTextColors(black, green);//第一个是选中时,第二个是不选中时    字体变成绿色


//        创建四个tab小组件
        TabLayout.Tab wechat = tabLayout.newTab();
        wechat.setIcon(R.mipmap.msg);//加图标
        wechat.setText("微信");

        TabLayout.Tab contract = tabLayout.newTab();
        contract.setIcon(R.mipmap.contract);
        contract.setText("通讯录");

        TabLayout.Tab find = tabLayout.newTab();
        find.setIcon(R.mipmap.find);
        find.setText("发现");

        TabLayout.Tab mine = tabLayout.newTab();
        mine.setIcon(R.mipmap.mine);
        mine.setText("我");

        tabLayout.addTab(wechat);
        tabLayout.addTab(contract);
        tabLayout.addTab(find);
        tabLayout.addTab(mine);

//        tabLayout.getTabAt(2).select();//设置优先级,要在xml文件中修改对应的visibility,此时对应的是"发现"


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //选中
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();//0是微信  1是通讯录  2是发现  3是我
                switch(position){//设置可见
                    case 0:
                        wechat.setIcon(R.mipmap.msg);
                        contract.setIcon(R.mipmap.contract);
                        find.setIcon(R.mipmap.find);
                        mine.setIcon(R.mipmap.mine);

                        lv_wechat.setVisibility(View.VISIBLE);
                        lv_contract.setVisibility(View.INVISIBLE);
                        tv_find.setVisibility(View.INVISIBLE);
                        tv_mine.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        wechat.setIcon(R.mipmap.message);
                        contract.setIcon(R.mipmap.contract_green);
                        find.setIcon(R.mipmap.find);
                        mine.setIcon(R.mipmap.mine);

                        lv_wechat.setVisibility(View.INVISIBLE);
                        lv_contract.setVisibility(View.VISIBLE);
                        tv_find.setVisibility(View.INVISIBLE);
                        tv_mine.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        wechat.setIcon(R.mipmap.message);
                        contract.setIcon(R.mipmap.contract);
                        find.setIcon(R.mipmap.find_onclick);
                        mine.setIcon(R.mipmap.mine);

                        lv_wechat.setVisibility(View.INVISIBLE);
                        lv_contract.setVisibility(View.INVISIBLE);
                        tv_find.setVisibility(View.VISIBLE);
                        tv_mine.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        wechat.setIcon(R.mipmap.message);
                        contract.setIcon(R.mipmap.contract);
                        find.setIcon(R.mipmap.find);
                        mine.setIcon(R.mipmap.mine_onclick);

                        lv_wechat.setVisibility(View.INVISIBLE);
                        lv_contract.setVisibility(View.INVISIBLE);
                        tv_find.setVisibility(View.INVISIBLE);
                        tv_mine.setVisibility(View.VISIBLE);
                        break;
                }
            }

            //不选中
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            //被重置
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
