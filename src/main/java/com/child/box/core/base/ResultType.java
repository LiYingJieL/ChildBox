package com.child.box.core.base;

import org.springframework.util.StringUtils;

public final class ResultType {

    public static final String TYPE_SUCCESS = "0";
    public static final String TYPE_ERROR = "-1";

    public static final String TYPE_NOTFOUND = "-5";
    public static final String TYPE_NOAUTHORIZATION = "-6";

    public static final String TYPE_EXISTED = "-7";

    public static final ResultType ERROR = new ResultType(TYPE_ERROR,"操作失败");
    public static final ResultType SUCCESS = new ResultType(TYPE_SUCCESS,"操作成功");
    public static final ResultType ERROR_AUTHENTICATION = new ResultType(TYPE_NOAUTHORIZATION, "未登录");
    public static final ResultType ERROR_AUTHENTICATION_WRONG = new ResultType(TYPE_ERROR,"用户名或密码错误");
    public static final ResultType ERROR_AUTHENTICATION_LOCKEDACCOUNT = new ResultType(TYPE_ERROR,"账号被禁用");

    public static final ResultType ERROR_NOTFOUND = new ResultType(TYPE_NOTFOUND, "404");

    public static final ResultType ERROR_ROLE_OR_PERMISSION = new ResultType(TYPE_ERROR,"权限不足");

    public static final ResultType ERROR_PARAMETER = new ResultType(TYPE_ERROR, "参数错误");
    public static final ResultType ERROR_REPEATEDSUBMIT = new ResultType(TYPE_ERROR, "请勿重复提交");

    public static final ResultType ERROR_EXISTED = new ResultType(TYPE_EXISTED, "该订单有未处理完退款流程，处理完后才能继续申请退款，请知悉！");


    private String code;
    private String msg;

    public ResultType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultType error(String msg){
        ResultType resultType = new ResultType(TYPE_ERROR, "操作失败");
        if (!StringUtils.isEmpty(msg)){
            resultType.setMsg(msg);
        }
        return  resultType;
    }

    public static ResultType success(String msg){
        ResultType resultType = new ResultType(TYPE_SUCCESS, "操作成功");
        if (!StringUtils.isEmpty(msg)){
            resultType.setMsg(msg);
        }
        return  resultType;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
