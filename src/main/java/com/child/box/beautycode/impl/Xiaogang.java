package com.child.box.beautycode.impl;

import com.child.box.beautycode.Behavior;
import com.child.box.beautycode.People;

@People(3)
public class Xiaogang implements Behavior {

    @Override
    public String doSomeThing(int type) {
        return "Xiaogang:"+type;
    }
}
