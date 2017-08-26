package com.fi.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

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
    private static final String TAG =TestService.class.getSimpleName() ;

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

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"onBind");
        //返回一个代理对象
        return new Binder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG,"onUnbind");
        return super.onUnbind(intent);
    }
}
