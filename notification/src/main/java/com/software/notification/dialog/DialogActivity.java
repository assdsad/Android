package com.software.notification.dialog;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.software.notification.R;

public class DialogActivity extends AppCompatActivity{

    private Button btn_simpledialog;
    private Button btn_listdialog;
    private Button btn_singlelist;
    private Button btn_multilist;
    private Button btn_halfdialog;
    private Button btn_circle;
    private Button btn_absolute;
    private ActivityResultLauncher launcher;
    private ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        initViews();

        initEvents();

        initLauncher();
    }

    private void initEvents() {
        btn_simpledialog.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.msg);
            builder.setTitle("简单对话框");
            builder.setMessage("这是一个简单对话框");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, "点击了确定按钮", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNeutralButton("第三个按钮", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, "这是优先级最高的按钮", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        btn_listdialog.setOnClickListener(v -> {
            final String[] items = {"这是第一项", "这是第二项", "这是第三项", "这是第四项"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.msg);
            builder.setTitle("列表对话框");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, "点击了确定按钮", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        btn_singlelist.setOnClickListener(v -> {
            final String[] items = {"这是第一项", "这是第二项", "这是第三项", "这是第四项"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.msg);
            builder.setTitle("单选列表对话框");
            builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, "点击了确定按钮", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        btn_multilist.setOnClickListener(v -> {
            final String[] items = {"这是第一项", "这是第二项", "这是第三项", "这是第四项"};
            final boolean[] checkedItems = {false, false, false, false};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.msg);
            builder.setTitle("多选列表对话框");
            builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    //选中后置为true
                    checkedItems[which] = isChecked;
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //点击确定后显示出选择了哪几项
                    for(int i = 0; i < checkedItems.length; i++) {
                        if (checkedItems[i]) {
                            Toast.makeText(DialogActivity.this, "选中了第" + i + "项", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        btn_halfdialog.setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.half_dialog, null);
            final EditText editText = (EditText) view.findViewById(R.id.edt_dialog);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.msg);
            builder.setTitle("半自定义列表对话框");
            builder.setView(view);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //点击确定之后获取到文本框中的内容，并显示出来
                    String content = editText.getText().toString();
                    Toast.makeText(DialogActivity.this, content, Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        btn_circle.setOnClickListener(v -> {
            //            ProgressDialog dialog = new ProgressDialog(this);
//            dialog.setMessage("正在加载中");
//            dialog.show();

            final ProgressDialog dialog = new ProgressDialog(DialogActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setTitle("圆形进度条");
            dialog.setMessage("正在加载");
            dialog.setMax(100);
            dialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(5000);
                        dialog.cancel();//取消对话框
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        });
        btn_absolute.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this, R.style.NormalDialogStyle);
            View view = View.inflate(this, R.layout.absolue_define, null);
            dialog.setContentView(view);

            //让布局位于屏幕底端
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.setCanceledOnTouchOutside(true);

            dialog.show();

            Button btn_take_picture = view.findViewById(R.id.btn_take_picture);
            Button btn_photo = view.findViewById(R.id.btn_photo);

            //点击相册后跳转到系统相册
            btn_photo.setOnClickListener(m -> {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                );
                launcher.launch(intent);
            });

        });
    }
    private void initLauncher() {
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK) {
                            Uri uri = result.getData().getData();

                            try {
                                //将从系统相册获得的图片赋值给头像
                                //先用bitmap获取图片
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                iv_image.setImageBitmap(bitmap);

                            } catch (Exception e){
                                e.printStackTrace();
                            }
                    }
                }
    }
        );
    }

    private void initViews() {
        btn_simpledialog = findViewById(R.id.btn_simpledialog);
        btn_listdialog = findViewById(R.id.btn_listdialog);
        btn_singlelist = findViewById(R.id.btn_singlelist);
        btn_multilist = findViewById(R.id.btn_multilist);
        btn_halfdialog = findViewById(R.id.btn_halfdialog);
        btn_circle = findViewById(R.id.btn_circle);
        btn_absolute = findViewById(R.id.btn_absolute);
        iv_image = findViewById(R.id.iv_image);
    }

}