package com.child.box.jsontrans;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
@JSONType(orders={"id","title","mkname"})
public class KejianHomeBk {
    private String id;

    private String title;

    private List<Object> mkname;

}
