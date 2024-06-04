package com.software.homework.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.software.homework.R;

public class CounterActivity extends AppCompatActivity {

    private int count = 100;
    private TextView tv_counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        //获取组件
        tv_counter = findViewById(R.id.tv_counter);

        //创建线程
        new Thread(){
            @Override
            public void run() {
                counter();
            }
        }.start();

    }

    private void counter() {
        while(true) {

            try {
                //count为0时显示100%
                if(count <= 0) {
                    tv_counter.setText("100%");
                    return;
                }

                //每隔十个数更新一次
                if(count % 10 == 0) {
                    tv_counter.setText(String.format("%d %%", 100 - count / 10 * 10));
                }

                count--;
//                count每减1，休眠100ms
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}