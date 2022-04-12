package com.child.box.beautycode.observer;

import java.util.ArrayList;
import java.util.List;

public class MailServer implements Observerable{
    private List<Observer> list;
    private String msg = null;

    public MailServer(){
        list = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        list.remove(o);
    }

    @Override
    public void notifyObserver() {
        for(Observer o : list){
            o.update(msg);
        }
    }

    public void setMsg(String msg){
        this.msg = msg;
    }
}
