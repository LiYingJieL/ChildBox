package com.child.box.beautycode.impl;

import com.child.box.beautycode.Behavior;
import com.child.box.beautycode.People;
import org.springframework.stereotype.Service;

@People(2)
public class Xiaohong implements Behavior {

    @Override
    public String doSomeThing(int type) {
        return "Xiaohong:"+type;
    }
}
