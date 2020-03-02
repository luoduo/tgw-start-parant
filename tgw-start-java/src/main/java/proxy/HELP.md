# Java的三种代理模式

### 1.代理模式
代理(Proxy)是一种设计模式，提供了对目标对象另外的访问方式；即通过代理对象访问目标对象。这样做的好处是：可以在目标对象实现的基础上，增强额外的功能操作，即扩展目标对象的功能。
​ 这里使用到编程中的一个思想：不要随意去修改别人已经写好的代码或者方法，如果需改修改，可以通过代理的方式来扩展该方法。

​ 举个例子来说明代理的作用：假设我们想邀请一位明星，那么并不是直接连接明星，而是联系明星的经纪人，来达到同样的目的。明星就是一个目标对象，他只要负责活动中的节目，而其他琐碎的事情就交给他的代理人(经纪人)来解决。这就是代理思想在现实中的一个例子

用图表示如下:

![Image text](https://images2015.cnblogs.com/blog/790334/201701/790334-20170116124522880-1137330008.png)

#### 1.1.静态代理(类似于装饰者模式)

```$xslt
静态代理在使用时，需要定义接口或者父类，被代理对象与代理对象一起实现相同的接口或者是继承相同父类。

下面举个案例来解释：
模拟保存动作，定义一个保存动作的接口：IUserDao.java，然后目标对象实现这个接口的方法UserDao.java，此时如果使用静态代理方式，就需要在代理对象(UserDaoProxy.java)中也实现IUserDao接口。调用的时候通过调用代理对象的方法来调用目标对象。
需要注意的是，代理对象与目标对象要实现相同的接口，然后通过调用相同的方法来调用目标对象的方法。

```
代码示例：<br/>
接口：IUserDao.java

```$xslt

/**
 * 接口
 */
public interface IUserDao {

    void save();
}

```
目标对象：UserDao.java
```$xslt
/**
 * 接口实现
 * 目标对象
 */
public class UserDao implements IUserDao {
    public void save() {
        System.out.println("----UserDao 保存数据!----");
    }
}
```

代理对象：UserDaoProxy.java
```$xslt
/**
 * 代理对象,静态代理
 */
public class UserDaoProxy implements IUserDao{
    //代理类需要获得被代理类的信息
    private IUserDao target;
    public UserDaoProxy(IUserDao target){
        this.target = target;
    }

    public void save() {
        System.out.println("开始事务...");
        target.save();//执行目标对象的方法
        System.out.println("提交事务...");
    }
}
```

测试类：App.java

```$xslt
/**
 * 测试类
 */
public class App {
    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();

        //代理对象,把目标对象传给代理对象,建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);

        proxy.save();//执行的是代理的方法
    }
}
```

静态代理总结:

1.可以做到在不修改目标对象的功能前提下,对目标功能扩展.<br/>

2.缺点:<br/>
因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护.
如何解决静态代理中的缺点呢?答案是可以使用动态代理方式


### 1.3.Cglib代理(基于继承的方式实现)

上面的静态代理和动态代理模式都是要求目标对象是实现一个接口的目标对象，但是有时候目标对象只是一个单独的对象，并没有实现任何的接口，这个时候就可以使用以目标对象子类的方式类实现代理，这种方法就叫做：Cglib代理

####`Cglib代理，也叫作子类代理，它是在内存中构建一个子类对象从而实现对目标对象功能的扩展。`

JDK的动态代理有一个限制，就是使用动态代理的对象必须实现一个或多个接口，如果想代理没有实现接口的类，就可以使用Cglib实现。
Cglib 是一个强大的高性能的代码生成包，它可以在运行期扩展java类与实现java接口。它广泛的被许多AOP的框架使用，例如Spring AOP和syn aop，为他们提供方法的interception(拦截)
Cglib 包的底层是通过使用一个小而快的字节码处理框架ASM来转换字节码并生成新的类。不鼓励直接使用ASM，因为它要求你必须对 JVM 内部结构包括 class 文件的格式和指令集都很熟悉。

