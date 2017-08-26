package com.fi.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fi.service.TestService;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/8/26 0026 18:43
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class ServiceActivity extends Activity implements View.OnClickListener {
    //声明控件
    private Button open;
    private Button stop;
    private Button bind;
    private Button unbind;
    private static final String TAG = ServiceActivity.class.getSimpleName();

    //创建服务连接对象
    private MyConn conn = new MyConn();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initView();
        initListener();
    }

    //设置点击监听
    private void initListener() {
        open.setOnClickListener(this);
        stop.setOnClickListener(this);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
    }

    //实例化控件
    private void initView() {
        open = (Button) findViewById(R.id.open);
        stop = (Button) findViewById(R.id.stop);
        bind = (Button) findViewById(R.id.bind);
        unbind = (Button) findViewById(R.id.unbind);
    }

    public class MyConn implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("TestService", "服务被绑定了...........");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open:
                //开启服务
                Intent intent1 = new Intent(this, TestService.class);
                startService(intent1);
                break;
            case R.id.stop:
                //停止服务
                Intent intent2 = new Intent(this, TestService.class);
                stopService(intent2);
                break;
            case R.id.bind:
                Intent intent3 = new Intent(this, TestService.class);
                bindService(intent3, conn, BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                //Intent intent4 = new Intent(this, TestService.class);
                unbindService(conn);
                break;
            default:
                break;
        }
    }
}
