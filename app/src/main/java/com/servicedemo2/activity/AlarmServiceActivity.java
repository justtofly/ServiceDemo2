package com.servicedemo2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fi.activity.R;
import com.servicedemo2.service.LongRunningService;

//后台执行定时任务
public class AlarmServiceActivity extends Activity implements View.OnClickListener {
    //声明控件
    private Button btn_alarm1;
    private Button btn_alarm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_service);
        //开启服务
//        startService(new Intent(this, LongRunningService.class));
        initView();
        initListener();
    }

    private void initListener() {
        btn_alarm1.setOnClickListener(this);
        btn_alarm2.setOnClickListener(this);
    }

    private void initView() {
        btn_alarm1= (Button) findViewById(R.id.btn_alarm1);
        btn_alarm2= (Button) findViewById(R.id.btn_alarm2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_alarm1:
                startService(new Intent(this, LongRunningService.class));
                break;
            case R.id.btn_alarm2:
                stopService(new Intent(this, LongRunningService.class));
                break;
            default:
                break;
        }
    }
}
