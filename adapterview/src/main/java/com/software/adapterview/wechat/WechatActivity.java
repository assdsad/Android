package com.software.adapterview.wechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.software.adapterview.R;
import com.software.adapterview.adapters.WechatAdapter;
import com.software.adapterview.adapters.WechatFragmentAdapter;
import com.software.adapterview.entity.Contract;
import com.software.adapterview.entity.Wechat;
import com.software.adapterview.fragments.ContractFragment;
import com.software.adapterview.fragments.FindFragment;
import com.software.adapterview.fragments.MineFragment;
import com.software.adapterview.fragments.WechatFragment;


import java.util.ArrayList;
import java.util.List;

public class WechatActivity extends AppCompatActivity {

    private ViewPager2 vp_context;
    private TabLayout tb_nav;
    private List<String> tabNameList;
    private List<Integer> tabImageList;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);

        //获取activity中的控件
        findViews();

        //创建fragment
        initFragments();

        //创建适配器
        WechatFragmentAdapter adapter = new WechatFragmentAdapter(
                fragmentList, this
        );
        //ViewPager2绑定适配器
        vp_context.setAdapter(adapter);
        //TabLayout与ViewPager2关联
        TabLayoutMediator mediator = new TabLayoutMediator(
                tb_nav,
                vp_context,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabNameList.get(position));
                        tab.setIcon(tabImageList.get(position));

//                        switch (position){
//                            case 0:
//                                tab.setText("微信");
//                                tab.setIcon(R.mipmap.message);
//                                break;
//                            case 1:
//                                tab.setText("通讯录");
//                                tab.setIcon(R.mipmap.contract);
//                                break;
//                            case 2:
//                                tab.setText("发现");
//                                tab.setIcon(R.mipmap.find);
//                                break;
//                            case 3:
//                                tab.setText("我的");
//                                tab.setIcon(R.mipmap.mine);
//                                break;
//                        }
                    }
                }
        );

        //使效果生效
        mediator.attach();
        int green = ContextCompat.getColor(this, R.color.green);
        int black = ContextCompat.getColor(this, R.color.black);

        tb_nav.setTabTextColors(black, green);//第一个是选中时,第二个是不选中时    字体变成绿色

//        ListView lv_wechat = findViewById(R.id.lv_wechat);
//        ListView lv_contract = findViewById(R.id.lv_contract);
//        List<Wechat> wechatList = Wechat.getWechatList();
//        List<Contract> contractList = Contract.getContractList();
//
////
//        lv_wechat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(WechatActivity.this,
//                        wechatList.get(position).getNickName(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        lv_contract.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(WechatActivity.this,
//                        contractList.get(position).getContractText(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void findViews() {
        tb_nav = findViewById(R.id.tb_nav);
        vp_context = findViewById(R.id.vp_context);
    }

    private void initFragments(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new WechatFragment());
        fragmentList.add(new ContractFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new MineFragment());

        tabNameList = new ArrayList<>();
        tabNameList.add("微信");
        tabNameList.add("联系人");
        tabNameList.add("发现");
        tabNameList.add("我的");

        tabImageList = new ArrayList<>();
        tabImageList.add(R.mipmap.message);
        tabImageList.add(R.mipmap.contract);
        tabImageList.add(R.mipmap.find);
        tabImageList.add(R.mipmap.mine);

    }
}