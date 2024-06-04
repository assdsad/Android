package com.software.notification.menu;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import com.software.notification.R;
import com.software.notification.utils.ToastUtil;

public class MenuActivity extends AppCompatActivity {

    private EditText edt_copy;
    private EditText edt_paste;
    private Button btn_popmenu;
    private Button btn_test;
    private final String TAG = "msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //初始化控件
        initViews();
        //注册上下文菜单
        //长按之后显示菜单
        registerForContextMenu(edt_copy);
        registerForContextMenu(edt_paste);

        //弹出式菜单
        //给按钮绑定事件
        btn_popmenu.setOnClickListener(v -> {
            //创建弹出式菜单
            PopupMenu popupMenu = new PopupMenu(MenuActivity.this, btn_popmenu);//点击当前按钮就在当前按钮下显示菜单
                                                                           //btn_popmenu可换成其他，会变成在其他按钮下显示菜单
            //加载资源文件，再绑定
            popupMenu.getMenuInflater().inflate(R.menu.menu_item, popupMenu.getMenu());
            //绑定菜单点击事件
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    ToastUtil.show(MenuActivity.this, item.getTitle().toString());
                    return false;
                }
            });
            //显示菜单
            popupMenu.show();
        });

        //长按
        btn_test.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i(TAG, "longClick");
                return true;
            }
        });
        btn_test.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_UP) {
                    Log.i(TAG, "失去触摸");
                }
                if(action == MotionEvent.ACTION_DOWN) {
                    Log.i(TAG, "触摸上");
                }
                return false;
            }
        });
    }

    private void initViews() {
        edt_copy = findViewById(R.id.edt_copy);
        edt_paste = findViewById(R.id.edt_paste);
        btn_popmenu = findViewById(R.id.btn_popmenu);
        btn_test = findViewById(R.id.btn_test);
    }

    //选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //关联资源
        getMenuInflater().inflate(R.menu.menu_item, menu);//绑定布局
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//对应菜单中每一个选项
        ToastUtil.show(this, item.getTitle().toString());
        return super.onOptionsItemSelected(item);
    }



    //上下文菜单
    //长按选中某个内容才会出现菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                                    //绑定的菜单      视图，可自定义   当前选中的选项的信息
        getMenuInflater().inflate(R.menu.menu_item, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        //获取系统的剪切板（每个输入法都知道剪切板中的内容，所以是调用系统的剪切板）
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        //选择了复制菜单选项
        if(itemId == R.id.file_copy) {
            //把edt_copy文本框中的内容复制到剪切板
            cm.setPrimaryClip(ClipData.newPlainText("msg", edt_copy.getText()));//创建剪切板数据,并标识这是哪一个对应的内容 msg->edt_copy.getText()
            ToastUtil.show(MenuActivity.this,"复制成功");
        } else if(itemId == R.id.file_paste) {
            edt_paste.setText(cm.getPrimaryClip().getItemAt(0).getText());//获取剪切板中第一个内容
        }
        return super.onContextItemSelected(item);
    }

    public void handleClick(View view){
        Log.i(TAG, "handleClick");
    }
}