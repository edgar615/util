package com.github.edgar615.util.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Edgar on 2016/2/19.
 *
 * @author Edgar  Date 2016/2/19
 */
public class MyServiceProxy implements InvocationHandler, ObjectProxy<IMyService> {

    private IMyService target;

    public MyServiceProxy(IMyService target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(1);
        return method.invoke(target, args);
    }

    @Override
    public IMyService getProxiedObject() {
        return target;
    }
}
