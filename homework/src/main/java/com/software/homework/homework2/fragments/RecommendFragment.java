package com.software.homework.homework2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.software.homework.R;
import com.software.homework.homework2.adapters.RecommendAdapter;
import com.software.homework.homework2.entity.Recommend;

import java.util.List;

public class RecommendFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, null);
        GridView gv_recommend = view.findViewById(R.id.gv_recommend);

        List<Recommend> recommendList = Recommend.getRecommendList();
        RecommendAdapter adapter = new RecommendAdapter(
                this.getContext(),
                R.layout.item_recommend,
                recommendList
        );
        gv_recommend.setAdapter(adapter);
        return view;
    }
}
