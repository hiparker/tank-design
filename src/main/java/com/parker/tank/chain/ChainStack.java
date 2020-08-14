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

    private Stack<GameChain> stack = new Stack<>();

    public void put(GameChain gc){
        this.stack.push(gc);
    }

    public GameChain peek(){
        if(!this.stack.empty()){
            return this.stack.peek();
        }
        return null;
    }

    public void clear(){
        this.stack.clear();
    }
}
