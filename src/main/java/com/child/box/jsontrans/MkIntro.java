package com.child.box.jsontrans;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
@JSONType(orders={"id","name","url","zipurl"})
public class MkIntro {

    private String id;

    private String name;

    private String url;

    private String zipurl;
}
