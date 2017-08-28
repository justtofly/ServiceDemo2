package com.servicedemo2.activity;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/8/26 0026 22:28
 * 作用	      把代理人以接口的方式抽象出来，这样就可以屏蔽代码里面的细节，把你想暴露的内容给暴露出来
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public interface IService3 {
    /**
     * 接口定义可以调用的方法，办正事,我只需要知道你这个接口暴露的方法，不需要知道具体的内部实现
     * @param money
     */
    public void callMethodInService(int money);
}
