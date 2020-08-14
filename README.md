## 坦克大战 - 设计模式训练



> Tank-Master 基础版 - 里程碑

```
坦克大战简单游戏本身

## 简单的开发了 坦克大战游戏,用于后续的改进和优化
```



> Tank-Factory-Model 单机版 - 主工厂 设计模式

```
坦克大战练习23种设计模式

用于对象设计技能提升

1.单例模式（Single）
	1). final + private 构造函数方法
	2). enum 枚举单例
	3). 内部类单例
	3). 双重判断+synchronize+volatile
	...

2.策略模式（Strategy）
	坦克Fire策略

3.抽象工厂（Abstract Factory）
	抽象工厂一键换肤换主题 - 产品一族

4.工厂方法（Factory Method）
	具体抽象工厂下 - 产品

5.模版模式/原型模式（Model / Prototype）
	BaseTank定义了 fire move 等方法,子类去具体的实现,否则报错	

```


> Tank-Desin-Model 单机版 - 设计模式

<font color="red">**未解决问题：自身Main坦克死亡后，还可以发射子弹**</font>

```
坦克大战练习23种设计模式

用于对象设计技能提升

1.单例模式（Single）
	1). final + private 构造函数方法
	2). enum 枚举单例
	3). 内部类单例
	3). 双重判断+synchronize+volatile
	...

2.策略模式 （Strategy）
	坦克Fire策略

3.工厂方法 （Factory Method）
	具体抽象工厂下 - 产品

4.门面（Faced）
	Model 与 View 分离
    Frame只负责展示，模型分离

5.调停者（Mediation / Mediator）
    用途为 各种模型的统一管控，各种Modle调停者的总控制

6.责任链 （Chain）
	用途：
        1). 各种Modle之间的碰撞检测
        2). 游戏关卡责任链
7.抽象工厂
    用于责任链模式下，可更换游戏场景

```



> Tank-Net-Model 网络版 - 游戏服务器设计

```
坦克大战网络版设计

用于对象设计技能提升 与 游戏服务底层设计 Netty
```



