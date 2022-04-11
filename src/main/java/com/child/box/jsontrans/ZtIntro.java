package com.child.box.jsontrans;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
@JSONType(orders={"id","name","zipurl","res"})
public class ZtIntro {

    private String id;

    private String name;

    private String zipurl;

    private List<ZtIntroDetail> res;

    @Data
    @JSONType(orders={"name","url"})
    static class ZtIntroDetail{

        private String name;

        private String url;

        ZtIntroDetail(String name,String url){
            this.name = name;
            this.url = url;
        }
    }
}
