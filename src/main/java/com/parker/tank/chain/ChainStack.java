package com.parker.tank.chain;

import java.util.Stack;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain
 * @Author: Parker
 * @CreateTime: 2020-08-14 06:30
 * @Description: 责任链压栈
 */
public enum ChainStack {

    INSTANCE;

    private Stack<BaseGameChain> stack = new Stack<>();

    public void put(BaseGameChain gc){
        this.stack.push(gc);
    }

    public BaseGameChain peek(){
        if(!this.stack.empty()){
            return this.stack.peek();
        }
        return null;
    }

    public void clear(){
        this.stack.clear();
    }
}
