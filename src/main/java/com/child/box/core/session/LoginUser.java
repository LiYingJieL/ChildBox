package com.child.box.core.session;

import java.lang.annotation.*;

/**
 * @author xxm
 * @description 已登录用户标识
 * @date 2018-05-22
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
}
