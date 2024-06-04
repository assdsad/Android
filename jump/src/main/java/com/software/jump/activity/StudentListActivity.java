package com.software.jump.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.software.jump.R;
import com.software.jump.entity.Student;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    private List<String> studentList;
    private ActivityResultLauncher launcher;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        //初始化数据源
        initData();

        //创建适配器
        adapter =  new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                studentList
        );
        ListView lv_student_list = findViewById(R.id.lv_student_list);
        lv_student_list.setAdapter(adapter);

        //初始化Launcher
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),//启动一个带返回结果的activity
                new ActivityResultCallback<ActivityResult>() { //这是一个接口
                    @Override
                    public void onActivityResult(ActivityResult result) { //等待返回数据并更改相应信息
                        //形参result是返回数据的那个setResult
                        //接收返回的数据
                        if(result.getResultCode() == 201) {
                            Intent back = result.getData();
//                            String newName = back.getStringExtra("newStudentName");//跟下面的bundle对换使用  和studentList.add()对应

                            //在对应位置更新数据
                            Bundle bundle = back.getExtras();
                            studentList.set(bundle.getInt("position"), bundle.getString("name"));
                            //拿到数据后放到数据源中
//                            studentList.add(newName);//在原有数据的后面加一条数据
                            //通知adapter来更新内容
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
        );

//        绑定item事件，用于跳转详情页面，并携带学生详情传输
        lv_student_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取数据
                String studentName = studentList.get(position);
                //创建Intent用于跳转
                Intent intent = new Intent(
                        StudentListActivity.this,
                        StudentDetailActivity.class
                );
                //传递参数
                //1、第一种传递参数方式
//                intent.putExtra("name", studentName);//类似键值对

                //第二种方式   可以打包一起传输
                Bundle bundle = new Bundle();
                bundle.putString("name", studentName);
                bundle.putInt("position", position);
//                bundle.putString("pwd", "hello world!");
                intent.putExtras(bundle);


                //第三种方式  传递对象
//                Student student = new Student(studentName);//Student类实现Serializable接口
//                intent.putExtra("student", student);

                //跳转
//                startActivity(intent);

                //带返回值的跳转
                launcher.launch(intent);

            }
        });
    }

    private void initData() {
        studentList = new ArrayList<>();
        studentList.add("张三");
        studentList.add("李四");
        studentList.add("王五");
    }
}