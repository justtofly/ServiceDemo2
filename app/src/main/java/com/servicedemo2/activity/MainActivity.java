package com.servicedemo2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fi.activity.R;

public class MainActivity extends Activity implements View.OnClickListener {
    //声明控件
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    //设置监听
    private void initListener() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    //初始化控件
    private void initView() {
        btn1= (Button) findViewById(R.id.btn1);
        btn2= (Button) findViewById(R.id.btn2);
        btn3= (Button) findViewById(R.id.btn3);
        btn4= (Button) findViewById(R.id.btn4);
        btn5= (Button) findViewById(R.id.btn5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                startActivity(new Intent(this,ServiceActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(this,ServiceActivity2.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(this,ServiceActivity3.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(this,IntentServiceActivity.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(this,AlarmServiceActivity.class));
                break;
            default:
                break;
        }
    }
}
