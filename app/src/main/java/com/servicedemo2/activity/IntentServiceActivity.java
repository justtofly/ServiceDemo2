package com.servicedemo2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fi.activity.R;
import com.servicedemo2.service.MyIntentService;

public class IntentServiceActivity extends AppCompatActivity implements View.OnClickListener {
    //声明控件
    private Button btn_intent_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        initView();
        initListener();
    }

    private void initListener() {
        btn_intent_service.setOnClickListener(this);
    }

    private void initView() {
        btn_intent_service= (Button) findViewById(R.id.btn_intent_service);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_intent_service:
                //打印主线程的id
                Log.e("MyIntentService","IntentServiceActivity:Thread id is:"+Thread.currentThread().getId());
                Intent intentService=new Intent(this, MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }
}
