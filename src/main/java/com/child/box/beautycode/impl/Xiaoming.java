package com.child.box.beautycode.impl;

import com.child.box.beautycode.Behavior;
import com.child.box.beautycode.People;
import org.springframework.stereotype.Service;

@People(1)
public class Xiaoming implements Behavior {

    @Override
    public String doSomeThing(int type) {
        return "Xiaoming:"+type;
    }
}
