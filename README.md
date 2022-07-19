# COEBuff
Chapter of Ember 效果系统。

## 如何进行开发？（代码结构介绍）

### com.coe.coebuff.buff.COEBuffType
该枚举类将列举所有buff类型信息。
每一个buff应具有一个独特的ID与一个名字。
如果需要一个新的buff类型，请在这里创建。

###  com.coe.coebuff.handler.**Handler
你需要在这里为每个buff创建一个监听器类，来处理每个buff的作用效果。

为此，我准备了两种示例：

LifeBuffHandler是修改属性性质的监听器，在buff加载和卸载时触发特定属性变化。

PowerBuffHandler是修改事件性质的监听器，在玩家造成伤害时，对伤害大小进行修改。

**注意：Handler必须实现Listener，但你无需在Handler中写构造函数、以及尝试注册它。**

### com.coe.coebuff.BuffMain
在该主类的onEnable函数中，你需要选择启用之前写的各个Handler。
当然示例也是有的，比如你看到的这段代码：
```
enable(LifeBuffHandler.class);
```
