package com.software.threadtext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class DownloadActivity extends AppCompatActivity {

    private Button btn_dl1;
    private TextView tv_dl1;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initViews();


        btn_dl1.setOnClickListener(v -> {
            new Thread(){
                @Override
                public void run() {
                    try {
                        for(int i = 1; i <= 10; i++){
                            tv_dl1.setText("文件已下载" + i + "%\n");
                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        });
    }

    private void initViews() {
        btn_dl1 = findViewById(R.id.btn_dl1);
        tv_dl1 = findViewById(R.id.tv_dl1);
    }
}