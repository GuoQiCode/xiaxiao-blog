package com.xiaoxiao.pojo;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class XiaoxiaoSorts implements Serializable
{
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize
    private Long sortId;
    private String sortName;
    private String sortAlias;
    private String sortDescription;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentSortId;
}
