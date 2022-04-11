package com.child.box.core.base;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.log4j.Logger;

public abstract class BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());

    public static final JSONObject EMPTY_DATA = new JSONObject();
    public Object renderJsonV1(ApiRes res) {
        if (res.getData() == null) {
            res.setData(EMPTY_DATA);
        }
        return getJsonString(res); // Null 直接显示 null，ref进行深度拷贝
    }

    public String getJsonString(ApiRes res) {
        if (ApiCons.STATUS_SUC.equals(res.getStatus())
                && StringUtils.isEmpty(res.getMsg())) {
            res.setMsg("success");
        }
        String result = JSON.toJSONString(res,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect);
        return result;
    }
}