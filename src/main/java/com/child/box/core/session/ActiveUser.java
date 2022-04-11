package com.child.box.core.session;

import java.lang.annotation.*;

/**
 * @description 已登录管理员标识
 * @author xxm
 * @date 2018-05-22
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActiveUser {
}
