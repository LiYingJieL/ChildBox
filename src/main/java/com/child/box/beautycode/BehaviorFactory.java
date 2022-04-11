package com.child.box.beautycode;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

public class BehaviorFactory {

    //单例模式
    //双重检查
    private static volatile BehaviorFactory factory;
    public static BehaviorFactory getInstance() {//
        if (factory == null) {
            synchronized (BehaviorFactory.class) {
                if (factory == null) {
                    factory = new BehaviorFactory();
                }
            }
        }
        return factory;
    }
    //静态内部类
   /* private static class BehaviorFactoryInstance{
        private static final BehaviorFactory INSTANCE = new BehaviorFactory();
    }
    public static BehaviorFactory getInstance(){
        return BehaviorFactoryInstance.INSTANCE;
    }*/

    //饿汉模式，线程安全浪费内存
    /*private static BehaviorFactory factory = new BehaviorFactory();

    public static BehaviorFactory getInstance(){
        return factory;
    }*/

    public static HashMap<Integer,String> peopleMap = new HashMap<>();

    static {
        //使用反射框架Reflections获取包下带有people注解的类，通过类获取注解的值和类名放到map中
        Reflections reflections = new Reflections("com.child.box.beautycode.impl");
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(People.class);
        for(Class<?> clazz : classSet){
            People people = clazz.getAnnotation(People.class);
            peopleMap.put(people.value(),clazz.getCanonicalName());
        }
    }

     public Behavior create(int type) throws Exception{
        String canonicalName  = peopleMap.get(type);//com.child.box.beautycode.impl.Xiaoming
        Class clazz = Class.forName(canonicalName);
        return (Behavior) clazz.newInstance();
     }

}
//使用工厂模式，通过反射机制获取接口实现类注解上的值和类名存到map中，通过类名获取具体实现方法
//抽象工厂模式，创建xiaomingFactory实现BehaviorFactory,实现create方法，通过new xiaomingFactory创建方法工厂。
