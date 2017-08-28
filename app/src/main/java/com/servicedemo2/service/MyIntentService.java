package com.servicedemo2.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/8/27 0027 8:39
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class MyIntentService extends IntentService {
    private static final String TAG =IntentService.class.getSimpleName() ;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService() {
        super("MyIntentService");//调用父类的有参构造函数
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //打印当前线程的id
        Log.e(TAG, "MyIntentService:Thread id is:" + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy executed");
        super.onDestroy();
    }
}
