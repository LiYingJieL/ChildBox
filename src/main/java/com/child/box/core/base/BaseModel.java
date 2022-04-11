package com.child.box.core.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseModel implements Serializable {

    @Id
    private Long id;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;

    private Long createUser;
    @Column(name = "update_user")
    private Long updateUser;

}
