package com.child.box.entity;

import com.child.box.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author xxm
 * @date 2018-05-25
 **/
@ApiModel
@Data
@Table(name = "bugrecord")
public class BugRecord extends BaseModel {

    private String requestMethod;

    private String requestUrl;

    private String contentType;

    private String cause;

    private String message;

    private String userAgent;

    @ApiModelProperty("错误信息")
    private String stackTrace;

    @ApiModelProperty("bug状态，默认0")
    private Integer bugStatus;

    @ApiModelProperty("解决时间")
    private Date solvedDate;
}
