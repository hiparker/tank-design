# 坦克大战 - 设计模式训练



## Tank-Master 基础版 - 里程碑

```
坦克大战简单游戏本身

## 简单的开发了 坦克大战游戏,用于后续的改进和优化
```



## Tank-Desin-Model 单机版 - 设计模式


++一个设计模式的出现一定有它特殊的价值++

坦克大战练习23种设计模式

用于对象设计技能提升

### 一、创建型模式（共5种）4

++单例模式、工厂方法模式、抽象工厂模式、建造者模式、原型模式++

    1.单例模式（Single）
    
        定义：（⭐ 单例也是一种工厂，静态工厂  ⭐ 其中简单工厂是可以产生对象或者类）️
            1.直接 private static final Object = new Object();
            2.static 静态块 去new
            3.getInstance 调用时去new (线程不安全)
            4.getInstance 调用时去new (加锁,但不是太安全)效率低
            5.同4 企图通过减小同步代码块提高效率
            6.同5 双重检测
            7.静态内部类方式,加载外部类时不会加载内部类,这样可以实现
              懒加载,内部类为static (完美)
            8.枚举,不仅可以解决线程安全问题,还可以防止反序列化(enum完美,
              但不习惯)
        
        用途:
            1.Tank 静态工厂
            2.Util 工具类
            3.策略模式的实现类为单例模式(策略只需要创建一次)
    
    2.工厂方法模式 （Factory Method）
        
        定义：
            1.任何可以产生对象的方法或类，都可以称之为工厂
            2.单例也是一种工厂
            3.不可以咬文嚼字，死扣概念
            4.为什么有了new之后，还要有工厂？
               * 灵活的控制生产过程
               * 全县、修饰、日志
            
        用途：
            1.对Tank的创建
            2.对关卡的创建    
        
    
    3.抽象工厂模式（Abstract Factory）
    
        定义：
            1.对产品一族进行扩展
        
        用途：
            1.用于责任链模式下，可更换游戏场景 
            
    4.构建器模式（Builder）
            
            定义：
                1.构建复杂对象
                2.分离复杂对象的构建表示
                3.同样的构建过程可以创建不同的表示
                4.无需记忆，自然使用
                
            用途：
                1.用于关卡地图地形（墙、碉堡、地雷、敌方坦克）构建    

### 二、结构型模式（共7种）

++装饰器模式、门面模式、组合模式、享元模式、代理模式、适配器模式、桥接模式++

    1.装饰器模式（Decortor）
    
        定义：
            1.用聚合代替继承（包装类）
            2.装饰器的价值在于装饰，它并不影响被装饰类本身的核心功能
            3.包装类来实现在不改变原类的改动
        
        用途：
            1.用于装饰器模式下可更换子弹样式
            
    2.门面模式（Faced）
        	
            定义:
                1.对外来说是Facede门面
                2.提供统一的对外负责人
                3.Model 与 View 分离
            
            用途:
                1.Frame只负责展示，模型分离        

    3.组合模式（Composite）
    
        定义：
            1.树状结构专用模式，在Composite中定义公共接口
              以保持透明性蛋损失安全性
        
        用途：
            1.暂无
            
    4.享元模式（FlyWeight）
    
        定义：
            1.String的原理和线程池的池都是FlyWeight模式
            2.原理：
                 有些操作需要new出n多个同一对象，这个过度耗费内存空间，所以
              建立一个对象池，判断它们的使用寿命，共享模式
              
        用途：
            1.坦克炮弹加入 炮弹池，N多坦克Fire都使用池内的炮弹，重复利用
              避免资源的浪费
              
    5.代理模式（Proxy）⭐️⭐️⭐️⭐️⭐️
        
        定义：（静态代理/动态代理）⭐️⭐️
            1.静态代理：（语法上非常像装饰，但是语义上是代理）️⭐
                     代理类接收一个接口对象，任何实现该接口的对象都可以通过代理类进行
                 代理，增加了通用性。
                     缺点就是,接口增加方法，代理池必须跟着修改，委托的对象越多，静态代
                 理就越臃肿
            2.动态代理：
                     根据代理的对象，动态创建代理类。实现方式是通过反射机制，借助Java
                 自带的包(java.lang.reflect.proxy),通过固定的规则生产！
                     JDK动态代理对象必须实现了一个接口 abstract不可以，必须是interface（⭐️⭐️）             
              
        用途：
            1.静态代理游戏总执行责任链，记录总执行时间  
            2.动态代理游戏关卡执行责任链，记录游戏关卡执行时间
            
    6.适配器模式（Adapter）        
                    
        定义：
            1.例：中间适配。国外电压110-国内电压220
            2.java.io
            3.jdbc-odbc bridge (不是桥接模式)
            4.ASM transformer
            
        用途：
            1.暂无
            
    7.桥接模式（Bridge）
    
        定义：
            1.双维度扩展
            2.分离抽象与具体
            3.用聚合模式（桥）连接抽象与具体 
            
        用途：
            1.暂无                           
                    

### 三、行为型模式（共11种）6

++策略模式、观察者模式、责任链模式、调停者模式、迭代器模式、访问者模式++

    1.策略模式 （Strategy）
    
        定义:
            1.抽象方法为Interface,然后多个类去实现这个接口,以做到灵活性
            2.范型也是一样    
    
        用途:
            1.坦克Fire策略
    
    2.观察者模式（Observer）⭐️⭐️⭐️⭐️⭐️
    
        定义：
            1.事件处理经常是Observer+责任链
            2.观察者的情况不一定要耦合在一个特定的被观察者身上
            3.责任链是能中断的，观察者模式一般不能中断
            4.大多数处理事件的时候需要事件源对象
            5.事件本身也可以形成一系列的体系
    
        用途：
            1.监控主战坦克 开火/移动事件   

    3.责任链模式 （Chain）⭐️⭐️⭐️⭐️⭐
    
        定义：
            1.软件的可扩展性，在对原来代码改动越小，说明这个代码的扩展性
              越好（封装变化）⭐️
            2.责任链模式并不创建责任链，责任链的创建必须由系统的其他部分
              创建出来
            3.责任链模式降低了请求的发送端和接受端之间的耦合，使多个对象
              都有机会处理这个请求
            4.一个链可以是一条线，一个树，也可以是一个环
    
    	定义：
            1). 各种Modle之间的碰撞检测
            2). 游戏关卡责任链
    
    4.调停者模式（Mediation / Mediator）
    
        定义:
            1.对内来说是Mediator调停者
            2.提供统一的对外负责人
            3.Model 与 View 分离
        
        用途:
            1.用途为各种模型的统一管控，各种Modle调停者的总控制
            
    5.迭代器模式（Iterator）
        
        定义：
            1.自实现多种集合结构遍历，抽象为接口子类去实现，包含
              hasNext() next()
              
        用途：
            1.暂无
                          
    6.访问者模式（Visitor）
        
        定义：（面窄，多数用在编译器上）
            1.针对结构固定的模式
            2.在结构不变的情况下动态改变对于内部元素的动作
            
        用途：
            1.暂无        

## Tank-Net-Model 网络版 - 游戏服务器设计


坦克大战网络版设计

用于对象设计技能提升 与 游戏服务底层设计 Netty




