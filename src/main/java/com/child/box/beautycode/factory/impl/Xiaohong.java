package com.child.box.beautycode.factory.impl;

import com.child.box.beautycode.factory.Behavior;
import com.child.box.beautycode.factory.People;

@People(2)
public class Xiaohong implements Behavior {

    @Override
    public String doSomeThing(int type) {
        return "Xiaohong:"+type;
    }
}
