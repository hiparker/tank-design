package com.parker.tank.proxy;

import com.parker.tank.chain.GameChain;
import com.parker.tank.chain.gate.Gate;
import com.parker.tank.proxy.handler.GateTimeHandler;

import java.lang.reflect.Proxy;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.proxy
 * @Author: Parker
 * @CreateTime: 2020-08-16 20:25
 * @Description: 关卡动态代理
 */
public enum GateChainProxy {

    INSTANCE;

    /**
     * 创建关卡动态代理类
     * @param gate
     * @return
     */
    public GameChain createGate(GameChain gate){

        /**
         *  注意：
         *  因为程序设计问题，责任链没有使用 interface 接口，而是使用的抽象类
         *  所以会报错 com.sun.proxy.$Proxy0 cannot be cast to xxx
         *  这里是JDK自带的Proxy 必要要继承接口类
         *
         *
         */

        Object o = Proxy.newProxyInstance(
                Gate.class.getClassLoader(),
                new Class[]{GameChain.class},
                new GateTimeHandler(gate)
        );

        /**
         *  cglib 对于继承抽象类 会有点问题 暂时还没搞懂
         */
        // 使用 cglib 作为动态代理
        /*Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Gate.class);
        enhancer.setCallback(new GateTimeInterceptor(gate));
        enhancer.setClassLoader(Gate.class.getClassLoader());
        enhancer.setInterfaces(Gate.class.getInterfaces());
        Object o enhancer.create();
        */

        return (GameChain) o;
    }


}
