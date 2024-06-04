package com.software.avatar.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.software.avatar.R;
import com.software.avatar.entity.UserInfo;
import com.software.avatar.util.HostUtil;
import com.software.avatar.util.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MineActivity extends AppCompatActivity {

    private TextView tv_my_password;
    private TextView tv_my_phone;
    private ImageView iv_avatar;
    private ActivityResultLauncher launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        initViews();

        initData();

        initEvents();

        initLauncher();
    }

    private void initLauncher() {
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {//是一个路径
                        if(result.getResultCode() == RESULT_OK) {//是一个uri
                            Uri uri = result.getData().getData();//result.getData()得到一个intent
                            try {
                                //将从系统相册得到的图片赋给头像
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        iv_avatar.setImageBitmap(bitmap);
                                    }
                                });

                                //将头像上传到服务器
                                new Thread(){
                                    @Override
                                    public void run() {
                                        uploadAvatar(uri);
                                    }
                                }.start();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );
    }

    private void uploadAvatar(Uri uri) {
        String path = HostUtil.HOST + "upload?phone=" + tv_my_phone.getText().toString();
//        String path = String.format(
//                HostUtil.HOST + "upload?phone=%s" +
//                        tv_my_phone.getText().toString()
//        );
        InputStream is = null;
        OutputStream os = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            //通过uri得到图片流（输入流）
            is = getContentResolver().openInputStream(uri);
//            一边输入一边输出
            os = conn.getOutputStream();
            byte[] bytes = new byte[512];
            int len = -1;
            while((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
                os.flush();
            }

            //在主线程中显示上传成功
            if(conn.getResponseCode() == 200) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MineActivity.this,
                                "上传成功",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                String responseMessage = conn.getResponseMessage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MineActivity.this,
                                 responseMessage,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initEvents() {
        //点击头像后，去系统相册中选择一张图片作为头像
        iv_avatar.setOnClickListener(v -> {
            Intent intent = new Intent(
                    //选择器
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI  //打开系统相册
            );
            launcher.launch(intent);
            //选择一张图片后要有返回值
        });
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String phone = bundle.getString("phone");
        String password = bundle.getString("password");
        String avatar = bundle.getString("avatar");
        tv_my_phone.setText(phone);
        tv_my_password.setText(password);

    }

    private void initViews() {
        iv_avatar = findViewById(R.id.iv_avatar);
        tv_my_phone = findViewById(R.id.tv_my_phone);
        tv_my_password = findViewById(R.id.tv_my_password);
    }
}