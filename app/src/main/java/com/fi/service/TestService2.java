package com.fi.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.fi.activity.IService;

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
public class TestService2 extends Service {
    private static final String TAG = TestService2.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
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
        super.onDestroy();
    }

    /**
     * 服务内部的中间人，代理人的类,官员的小秘1号
     */
    private class MyBinder extends Binder implements IService {
        public void callMethodInService(int money) {
            if (money > 5000) {
                Log.e(TAG,"6.代理人间接地调用了服务里面的方法（小秘1号）");
                methodInService();
            } else {
                Toast.makeText(getApplicationContext(), "这点钱，办个毛事！", Toast.LENGTH_SHORT).show();
            }
        }
        private void playGame(){
            Toast.makeText(getApplicationContext(), "我可以和你愉快玩耍！", Toast.LENGTH_SHORT).show();
        }
        private void playBall(){
            Toast.makeText(getApplicationContext(), "我可以和你一起散步", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 服务内部的中间人，代理人的类,官员的小秘2号
     */
    private class MyBinder2 extends Binder implements IService {
        public void callMethodInService(int money) {
            if (money > 5000) {
                Log.e(TAG,"6.代理人间接地调用了服务里面的方法（小秘2号）");
                methodInService();
            } else {
                Toast.makeText(getApplicationContext(), "这点钱，办个毛事！", Toast.LENGTH_SHORT).show();
            }
        }
        private void eatTogether(){
            Toast.makeText(getApplicationContext(), "我可以和你一起吃饭", Toast.LENGTH_SHORT).show();
        }
        private void drinkTogether(){
            Toast.makeText(getApplicationContext(), "我可以和你一起喝酒", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "2.如果服务被成功绑定，就会调用onBind方法，返回一个IBinder代理人对象,onBind方法");
        //别人也不知道是哪个小秘去办事，但一定知道这个小秘可以调用里面的一个方法，调用callMethodInService(int money)这个方法，它是一个接口定义的
        MyBinder2 myBinder2=new MyBinder2();
        Log.e(TAG,"3.new出来代理人对象"+myBinder2.toString());
        //返回一个代理对象
        return myBinder2;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }
}
