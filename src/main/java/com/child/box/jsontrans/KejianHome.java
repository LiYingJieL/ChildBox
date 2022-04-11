package com.child.box.jsontrans;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
@JSONType(orders={"id","zt","list"})
public class KejianHome {

    private String id;

    private String zt;

    private List<KejianHomeBk> list;

    KejianHome(String id, String zt){
        this.id = id;
        this.zt = zt;
    }
}
