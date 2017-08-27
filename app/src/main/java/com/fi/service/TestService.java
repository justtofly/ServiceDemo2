package com.fi.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/8/26 0026 18:48
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class TestService extends Service {
    private static final String TAG = TestService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        Toast.makeText(this, "onCreate()方法被回调", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        Toast.makeText(this, "onStartCommand()方法被回调", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 在服务里面定义的方法
     */
    public void methodInService() {
        //可以被调用
        //        Log.e(TAG,"methodInService(服务里面的方法)");

        Toast.makeText(this, "我是服务里面的方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        Toast.makeText(this, "onDestroy()方法被回调", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    /**
     * 服务内部的中间人，代理人的类
     */
    public class MyBinder extends Binder {
        /**
         * 帮忙调用服务里面的方法
         *
         * @param money 钱
         */
        public void callMethodInService(int money) {
            if (money > 5000) {
                Log.e(TAG,"6.代理人间接地调用了服务里面的方法");
                methodInService();
            } else {
                Toast.makeText(getApplicationContext(), "这点钱，办个毛事！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "onBind()方法被回调，返回一个IBinder代理人对象", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "2.如果服务被成功绑定，就会调用onBind方法，返回一个IBinder代理人对象,onBind方法");
        MyBinder myBinder=new MyBinder();
        Log.e(TAG,"3.new出来代理人对象"+myBinder.toString());
        //返回一个代理对象
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "onUnbind()方法被回调", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }
}
