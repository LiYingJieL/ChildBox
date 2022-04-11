package com.child.box.threads;

public class TestThread {
    public static void methodOne(){
        for(int i = 0;i<100;i++){
            System.out.println("methodOne-"+i+"-one");
        }
        System.out.println("one结束");
    }

    public static void methodTwo(){
        for(int i = 0;i<100;i++){
            System.out.println("methodTwo-"+i+"-two");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            methodOne();
            methodTwo();
        });
        thread.start();
    }


}
