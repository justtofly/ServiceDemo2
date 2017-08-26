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

import com.fi.service.TestService3;

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
public class ServiceActivity3 extends Activity implements View.OnClickListener {
    //声明控件
    private Button open;
    private Button stop;
    private Button bind;
    private Button unbind;
    private Button get;
    private static final String TAG = ServiceActivity3.class.getSimpleName();

    private IService3 myBinder;

    //创建服务连接对象
    private MyConn conn = new MyConn();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service3);
        initView();
        initListener();
    }

    //设置点击监听
    private void initListener() {
        open.setOnClickListener(this);
        stop.setOnClickListener(this);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
        get.setOnClickListener(this);
    }

    //实例化控件
    private void initView() {
        open = (Button) findViewById(R.id.open3);
        stop = (Button) findViewById(R.id.stop3);
        bind = (Button) findViewById(R.id.bind3);
        unbind = (Button) findViewById(R.id.unbind3);
        get= (Button) findViewById(R.id.get3);
    }

    public class MyConn implements ServiceConnection {


        //当服务被连接的时候调用的方法,相当于和中间人搭上了话
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("TestService", "4.代理人成功返回过来，得到代理人对象,iBinder:"+service.toString());
            //不用管返回的是哪个小秘的对象，它们都有一个IService的接口
            myBinder= (IService3) service;
        }

        //当服务失去连接的时候调用的方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("TestService", "服务失去连接了...........");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open3:
                //开启服务
                Intent intent1 = new Intent(this, TestService3.class);
                startService(intent1);
                break;
            case R.id.stop3:
                //停止服务
                Intent intent2 = new Intent(this, TestService3.class);
                stopService(intent2);
                break;
            //调用服务里面的方法
            case R.id.get3:
                Log.e("TestService","5.调用服务里面代理人的方法");
                myBinder.callMethodInService(8000);//只能调用这个方法
                break;
            //绑定服务
            case R.id.bind3:
                Intent intent3 = new Intent(this, TestService3.class);
                /**
                 * 第二个参数是一个接口，就要把这个接口的实现类给创建出来，conn,用来接收服务开启和停止的消息
                 * BIND_AUTO_CREATE,绑定服务的时候，如果服务不存在会自动地把服务创建出来
                 */
                Log.e("TestService","1.采用绑定的方式开启服务,bindService");
                bindService(intent3, conn, BIND_AUTO_CREATE);
                break;
            case R.id.unbind3:
                unbindService(conn);
                break;
            default:
                break;
        }
    }
}
