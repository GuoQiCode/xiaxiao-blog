package com.xiaoxiao.pojo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class XiaoxiaoLabels implements Serializable
{
    @JsonSerialize(using = ToStringSerializer.class)
    private Long labelId;
    private String labelName;
    private String labelAlias;
    private String labelDescription;
}
