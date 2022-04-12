package com.child.box.beautycode.factory.impl;

import com.child.box.beautycode.factory.Behavior;
import com.child.box.beautycode.factory.People;

@People(3)
public class Xiaogang implements Behavior {

    @Override
    public String doSomeThing(int type) {
        return "Xiaogang:"+type;
    }
}
