package com.software.avatartest.activity;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.software.avatartest.R;
import com.software.avatartest.utils.HostUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MineActivity extends AppCompatActivity {

    private TextView tv_my_password;
    private TextView tv_my_phone;
    private ImageView iv_avatar;
    private ActivityResultLauncher launcher;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        initViews();

        initData();//获取登录成功的用户信息

        initEvents();
        initLauncher();
    }

    private void initLauncher() {
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            Uri uri = result.getData().getData();

                            try {
                                //将从系统相册获得的图片赋值给头像
                                //先用bitmap获取图片
//                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                                iv_avatar.setImageBitmap(bitmap);

                                //使用Glide将头像变成圆形
                                Glide.with(MineActivity.this)
                                        .load(uri)
                                        .transform(new CircleCrop())
                                        .into(iv_avatar);

                                //将获得的头像上传到服务器
                                new Thread() {
                                    @Override
                                    public void run() {
                                        uploadAvatar(uri);
                                    }
                                }.start();
                            } finally {

                            }
                        }
                    }
                }
        );
    }

    private void uploadAvatar(Uri uri) {
        String path = HostUtil.PATH + "upload?phone=" + tv_my_phone.getText().toString();

        InputStream is = null;
        OutputStream os = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            //通过uri得到图片流，，输入流
            is = getContentResolver().openInputStream(uri);

            //一边输入一边输出
            os = conn.getOutputStream();
            byte[] bytes = new byte[512];
            int len = -1;
            while((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
                os.flush();
            }

            if(conn.getResponseCode() == 200) {
                //在主线程中显示上传成功
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                MineActivity.this,
                                "上传成功",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
            } else {
                String responseMessage = conn.getResponseMessage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                MineActivity.this,
                                responseMessage,
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String phone = bundle.getString("phone");
        String password = bundle.getString("password");
        String avatar = bundle.getString("avatar");

        //把用户名和密码显示在页面上
        tv_my_phone.setText(phone);
        tv_my_password.setText(password);
    }

    private void initEvents() {
        //点击头像后到系统相册中选择一张图片当头像
        //使用launcher
        iv_avatar.setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            );
            launcher.launch(intent);
        });

        btn_back.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MineActivity.this,
                    LoginActivity.class
            );
            startActivity(intent);
        });
    }

    private void initViews() {
        iv_avatar = findViewById(R.id.iv_avatar);
        tv_my_phone = findViewById(R.id.tv_my_phone);
        tv_my_password = findViewById(R.id.tv_my_password);
        btn_back = findViewById(R.id.btn_back);
    }
}