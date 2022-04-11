package com.child.box.beautycode.Decorator;


public class Deractor implements Ilog{
    //装饰模式，实现同一个接口，装饰对象持有被装饰对象的实例
    private UserMsg userMsg = null;

    public Deractor(UserMsg userMsg){
        this.userMsg = userMsg;
    }

    @Override
    public void log(String msg) {
        System.out.println("Before deractor");
        System.out.println(msg);
        userMsg.log(msg);
        System.out.println("after deractor");
    }

    public static void main(String[] args) {
        UserMsg userMsg = new UserMsg();
        Ilog ilog = new Deractor(userMsg);
        ilog.log("123");
    }
}
//代理模式和装饰模式形似，区别是deractor类中new UserMsg，表示被代理的对象完全被代理类控制执行或不执行。而装饰类对代理对象没有控制权，只能为其增加一层装饰，以加强被装饰对象的功能，仅此而已