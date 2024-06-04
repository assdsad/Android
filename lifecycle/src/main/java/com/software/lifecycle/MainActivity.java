package com.software.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "wsh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");

        Button btn_jump_to_second = findViewById(R.id.btn_jump_to_second);
        btn_jump_to_second.setOnClickListener(v -> {
            //显示使用intent
//            Intent intent = new Intent(
//                    MainActivity.this,
//                    SecondActivity.class
//            );
            Intent intent = new Intent();
            intent.setClass(
                    MainActivity.this,
                    SecondActivity.class
            );

            //用于设置启动模式
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//A->B->A->B->A->B,,,回退时直接到A然后结束
            //只要回到MainActivity页面，再回退就直接退出
            startActivity(intent);
        });

        //点击按钮后  显示拨号面板
        Button btn_phone = findViewById(R.id.btn_phone);
        btn_phone.setOnClickListener(v -> {
            Intent intent = new Intent();
            //隐式调用
            intent.setAction(Intent.ACTION_DIAL);
            //指定电话号码   使用data，data是一个URI  content
            Uri uri = Uri.parse("tel:10086");
            intent.setData(uri);
            startActivity(intent);


        });

        //点击按钮后 发短信
        Button btn_message = findViewById(R.id.btn_message);
        btn_message.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SENDTO);
            Uri uri = Uri.parse("smsto:10086");
            intent.setData(uri);
            startActivity(intent);
        });

        //点击按钮后 打开本地应用
        Button btn_app = findViewById(R.id.btn_app);
        btn_app.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("software");//指定avatartest中的loginActivity   avatartest中的manifest中添加了过滤器
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);//点击后跳转到avatartest中的登录页面
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }
}