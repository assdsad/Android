package com.software.layout.entity;

import com.software.layout.R;

import java.util.ArrayList;
import java.util.List;

public class Contract {
    private Integer contract_avatar;
    private String contractText;

    public static List<Contract> getContractList(){
        List<Contract> contractList = new ArrayList<>();
        contractList.add(new Contract(R.mipmap.newfriend, "新的朋友"));
        contractList.add(new Contract(R.mipmap.grouptalk, "群聊"));
        contractList.add(new Contract(R.mipmap.flag, "标签"));
        contractList.add(new Contract(R.mipmap.officialaccount, "公众号"));
        return contractList;
    }

    public Integer getContract_avatar() {
        return contract_avatar;
    }

    public void setContract_avatar(Integer contract_avatar) {
        this.contract_avatar = contract_avatar;
    }

    public String getContractText() {
        return contractText;
    }

    public void setContractText(String contractText) {
        this.contractText = contractText;
    }

    public Contract(){}
    public Contract(Integer contract_avatar, String contractText){
        this.contract_avatar = contract_avatar;
        this.contractText = contractText;
    }
}
