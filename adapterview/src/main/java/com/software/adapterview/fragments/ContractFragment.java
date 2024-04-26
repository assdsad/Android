package com.software.adapterview.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.software.adapterview.R;
import com.software.adapterview.adapters.ContractAdapter;
import com.software.adapterview.entity.Contract;

import java.util.ArrayList;
import java.util.List;

public class ContractFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contract, null);
        ListView lv_contract = view.findViewById(R.id.lv_contract);

        List<Contract> contractList = Contract.getContractList();
        ContractAdapter adapter = new ContractAdapter(
                this.getContext(),
                R.layout.item_contract,
                contractList
        );
        lv_contract.setAdapter(adapter);
        return view;
    }
}
