package com.fi.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.fi.activity.DetailActivity;
import com.fi.activity.IService3;
import com.fi.activity.R;

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
public class TestService3 extends Service {
    private static final String TAG = TestService3.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");

        /*Notification notification=new Notification(R.drawable.logo,"Notification comes",System.currentTimeMillis());
        Intent notificationIntent=new Intent(this, ServiceActivity3.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);*/

        //点击提示，并且有跳转功能
        //调用startForeground()方法后就会让TestService3变成前台服务，并在系统状态栏显示出来
        Notification.Builder builder1 = new Notification.Builder(getApplicationContext());
        builder1.setSmallIcon(R.drawable.logo); //设置图标
        builder1.setTicker("显示第二个通知");
        builder1.setContentTitle("通知"); //设置标题
        builder1.setContentText("点击查看详细内容"); //消息内容
        builder1.setWhen(System.currentTimeMillis()); //发送时间
        builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
        builder1.setAutoCancel(true);//打开程序后图标消失
        Intent intent =new Intent (getApplicationContext(),DetailActivity.class);
        PendingIntent pendingIntent =PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        builder1.setContentIntent(pendingIntent);
        Notification notification1 = builder1.build();
//        notificationManager.notify(124, notification1); // 通过通知管理器发送通知
        startForeground(123,notification1);


        //没有跳转功能，只有提示作用
        /*Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.logo);
        builder.setTicker("显示第一个通知");
        builder.setContentTitle("第一个通知");
        builder.setContentText("每天进步一点点");
        builder.setWhen(System.currentTimeMillis()); //发送时间
        builder.setDefaults(Notification.DEFAULT_ALL);
        Notification notification = builder.build();
//        notificationManager.notify(123, notification);
        startForeground(123,notification);*/

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
        Toast.makeText(this, "我是服务里面的方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }
    /**
     * 服务内部的中间人，代理人的类,官员的小秘2号
     */
    private class MyBinder2 extends Binder implements IService3 {
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
