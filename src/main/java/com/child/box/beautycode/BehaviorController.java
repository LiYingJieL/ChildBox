package com.child.box.beautycode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/behavior")
public class BehaviorController {

    private Behavior behavior;

    @GetMapping("/do")
    public String doSomeThing(@RequestParam("type")Integer type) throws Exception{
        BehaviorFactory behaviorFactory = BehaviorFactory.getInstance();
        behavior = behaviorFactory.create(type);
        return behavior.doSomeThing(type);
    }
}
