package com.child.box.jsontrans;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
@JSONType(orders={"id","name","intro","intro2","cover","list"})
public class Kejian {

    private int id;

    private String name;

    private String intro;

    private String intro2;

    private String cover;

    private List<KejianDetail> list;

    Kejian(int id,String name,String intro,String intro2,String cover){
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.intro2 = intro2;
        this.cover = cover;
    }
}
