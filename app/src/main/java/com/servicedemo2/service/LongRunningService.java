package com.servicedemo2.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.servicedemo2.receiver.AlarmReceiver;

import java.util.Date;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/8/27 0027 15:16
 * 作用	      继承Service，在后台执行定时任务
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class LongRunningService extends Service {
    private static final String  TAG =LongRunningService.class.getSimpleName() ;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate execute");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"onStartCommand execute");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //打印当前时间
                Log.e(TAG, "executed at:" + new Date());
            }
        }).start();
        //获取AlarmManager的实例
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //10秒
        int shiMiao=10*1000;
        long triggerAtTime= SystemClock.elapsedRealtime()+shiMiao;
        Intent i=new Intent(this,AlarmReceiver.class);
        //使用PendingIntent指定处理定时任务的广播接收器为AlarmReceiver,最后调用set()方法完成设定。
        PendingIntent pi=PendingIntent.getBroadcast(this, 0, i, 0);
        /**
         * 第一个参数：ELAPSED_REALTIME_WAKEUP，表示定时任务的触发时间从系统开机开始算起，但不会唤醒CPU。
         * 第二个参数：定时任务触发的时间
         */
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy execute");
    }
}
