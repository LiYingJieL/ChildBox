package com.child.box.beautycode.observer;

public class User implements Observer{
    private String name;

    public User(String name){
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name+":"+message);
    }


    public static void main(String[] args) {
        MailServer mail = new MailServer();

        User user1 = new User("java");
        mail.registerObserver(user1);

        User user2 = new User("C++");
        mail.registerObserver(user2);

        mail.setMsg("hello all");
        mail.notifyObserver();

        mail.removeObserver(user1);

        mail.setMsg("hello one");
        mail.notifyObserver();
    }
}
//观察者模式
//在对象之间定义了一对多的依赖，这样一来，当一个对象改变状态，依赖它的对象会收到通知并自动更新。
