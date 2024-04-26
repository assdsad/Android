package com.software.layout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.layout.R;
import com.software.layout.entity.Contract;

import java.util.List;

public class ContractAdapter extends BaseAdapter {
    private Context context;
    private Integer layoutId;
    private List<Contract> contractList;
    public ContractAdapter(Context context, Integer layoutId, List<Contract> contractList){
        this.context = context;
        this.layoutId = layoutId;
        this.contractList = contractList;
    }

    //获取数据个数
    @Override
    public int getCount() {
        return contractList.size();
    }

    //获取position下标位置的数据
    @Override
    public Object getItem(int position) {
        return contractList.get(position);
    }

    //获取数据的下标
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取视图
        if(convertView == null){
            //创建LayoutInflater视图加载器
            convertView = LayoutInflater.from(this.context).inflate(layoutId, null);
        }
        //获取图片，信息，并进行绑定
        ImageView lv_contract_avatar = convertView.findViewById(R.id.lv_contract_avatar);
        TextView lv_contract_text = convertView.findViewById(R.id.lv_contract_text);

        Contract contract = contractList.get(position);
        lv_contract_avatar.setImageResource(contract.getContract_avatar());
        lv_contract_text.setText(contract.getContractText());

        return convertView;
    }
}
