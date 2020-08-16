package com.parker.tank.proxy.handler;

import com.parker.tank.chain.BaseGameChain;
import com.parker.tank.chain.gate.Gate;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.proxy.handler
 * @Author: Parker
 * @CreateTime: 2020-08-16 22:36
 * @Description: 关卡 - 代理执行
 *
 *  注意：（这里不使用这个Handler）
 *  因为程序设计问题，责任链没有使用 interface 接口，而是使用的抽象类
 *  所以会报错 com.sun.proxy.$Proxy0 cannot be cast to xxx
 *  这里是JDK自带的Proxy 必要要继承接口类
 *
 */
public class GateTimeInterceptor implements MethodInterceptor {

    /** 代理方法名称 */
    private final static String METHOD_NAME = "handler";

    private BaseGameChain gate;

    public GateTimeInterceptor(Gate gate){
        this.gate = gate;
    }



    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object invokeO = method.invoke(gate, objects);
        long endTime = System.currentTimeMillis();
        if(METHOD_NAME.endsWith(method.getName())){
            System.out.println("["+gate.getNum2()+" - "+gate.getNum()+
                    " ]关卡执行时间(秒)："+(endTime/1000-beginTime/1000));
        }
        return invokeO;
    }
}
