package com.shiye.mir.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Param {

    private String paramId;

    private String paramName;

    @Override
    public String toString(){
        return "{ "+"paramId:"+paramId
        +"paramName"+paramName+" }";
    }

}
