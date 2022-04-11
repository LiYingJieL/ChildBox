package com.child.box.jsontrans;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
@JSONType(orders={"title","cover","url","type"})
public class KejianDetail {

    private String title;

    private String cover;

    private String url;

    private int type;

}
