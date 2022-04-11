package com.child.box.core.session;

import lombok.Data;

/**
 * @author xxm
 * @date 2018-05-22
 **/
@Data
public class UserRef {

    private Long id;
    private Integer roleLevel;
    private Long createUser;
    private Long groupId;

    private String auth;

}
