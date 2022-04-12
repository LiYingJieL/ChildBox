package com.child.box.beautycode.factory.impl;

import com.child.box.beautycode.factory.Behavior;
import com.child.box.beautycode.factory.People;

@People(1)
public class Xiaoming implements Behavior {

    @Override
    public String doSomeThing(int type) {
        return "Xiaoming:"+type;
    }
}
