package com.software.finaltest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.software.finaltest.R;
import com.software.finaltest.activity.ShoppingCartActivity;

public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mine, null);

        Button btn_goto_cart = view.findViewById(R.id.btn_goto_cart);
        btn_goto_cart.setOnClickListener(v -> {
            Intent intent = new Intent(
                    getActivity(),
                    ShoppingCartActivity.class
            );
            startActivity(intent);
        });
        return view;
    }
}
