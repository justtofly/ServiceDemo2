package com.fi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.fi.service.LongRunningService;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/8/27 0027 15:28
 * 作用	      构建出一个Intent对象，然后去启动LongRunningService这个服务
 * 这里为什么这么写呢？这就已经将一个长期在后台定时运行的服务完成了。因为一旦启动LongRunningService,这就已经在
 * onStartCommand()方法里设定了一个定时任务，这样10秒后AlarmReceiver的onReceive()方法就得到执行，然后我们在这里
 * 再次启动LongRunningService,这样就形成了一个永久的循环，保证LongRunningService可以每隔10秒就会启动一次,一个长期
 * 在后台定时运行的服务自然也就完成了。
 * 退出应用程序，服务停止。
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i=new Intent(context, LongRunningService.class);
        context.startService(i);
    }
}
