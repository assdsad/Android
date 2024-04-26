package com.software.homework.homework2.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;



import java.util.List;

public class RecommendFragmentAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList;
    public RecommendFragmentAdapter(List<Fragment> fragmentList, @NonNull FragmentActivity fragmentActivity) {
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
