package com.software.finaltest.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.software.finaltest.entity.Goods;
import com.software.finaltest.entity.GoodsInfo;
import com.software.finaltest.fragment.GoodsFragment;

import java.util.List;

public class GoodsFragmentAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList;
    public GoodsFragmentAdapter(List<Fragment> fragmentList, @NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
