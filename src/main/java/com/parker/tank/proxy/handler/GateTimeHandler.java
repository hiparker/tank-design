package com.parker.tank.proxy.handler;

import com.parker.tank.chain.GameChain;

import java.lang.reflect.InvocationHandler;
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
public class GateTimeHandler implements InvocationHandler {

    /** 代理方法名称 */
    private final static String METHOD_NAME = "handler";

    private GameChain gameChain;

    public GateTimeHandler(GameChain gameChain){
        this.gameChain = gameChain;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object o = method.invoke(gameChain, args);
        long endTime = System.currentTimeMillis();
        if(METHOD_NAME.endsWith(method.getName())){
            System.out.println("["+gameChain.getNum2()+" - "+gameChain.getNum()+
                    " ]关卡执行时间(秒)："+(endTime/1000-beginTime/1000));
        }
        return o;
    }
}
