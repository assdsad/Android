package com.software.adapterview.entity;
import com.software.adapterview.R;

import java.util.ArrayList;
import java.util.List;

public class Wechat {
    private Integer avatar;//头像
    private String nickName;//昵称
    private String endMessage;//最后一条消息

    //模拟几条聊天数据，以后会从访问web服务中获取
    public static List<Wechat> getWechatList(){
        List<Wechat> wechatList = new ArrayList<>();
        wechatList.add(new Wechat(R.mipmap.avatar1, "gril", "sing, dance, basketball"));
        wechatList.add(new Wechat(R.mipmap.avatar2, "Kobe", "man! What can I say"));
        wechatList.add(new Wechat(R.mipmap.avatar3, "Bryant", "manba out!"));
        wechatList.add(new Wechat(R.mipmap.contract_green, "11111", "[语音通话]"));
        return wechatList;
    }


    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEndMessage() {
        return endMessage;
    }

    public void setEndMessage(String endMessage) {
        this.endMessage = endMessage;
    }

    public Wechat() {}
    public Wechat(Integer avatar, String nickName, String endMessage) {
        this.avatar = avatar;
        this.nickName = nickName;
        this.endMessage = endMessage;
    }
}
