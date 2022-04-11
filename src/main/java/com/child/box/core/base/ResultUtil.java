package com.child.box.core.base;

@SuppressWarnings("unchecked")
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(ResultType resultCode) {
        Result result = new Result();
        result.setCode(resultCode.getCode());
        result.setMsg(resultCode.getMsg());
        return result;
    }
}