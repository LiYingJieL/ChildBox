package com.child.box.beautycode.adapter;

import org.checkerframework.checker.units.qual.A;

public class Adapter extends Source1 implements Source2{
    //类适配器模式，扩展类的功能。
    @Override
    public void method2() {
        System.out.println("method2.........");
    }


    public static void main(String[] args) {
        Source2 adapter = new Adapter();
        //Adapter adapter = new Adapter();
        adapter.method1();
        adapter.method2();
    }
}

  class Adapter2 implements Source2{
    //对象适配器模式
    private Source1 source1;

    public Adapter2(Source1 source1){
        this.source1 = source1;
    }

    public void method1() {
        source1.method1();
    }

    @Override
    public void method2() {
        System.out.println("method2.....");
    }

    public static void main(String[] args) {
        Source2 source2 = new Adapter2(new Source1());
        source2.method1();
        source2.method2();
    }
}