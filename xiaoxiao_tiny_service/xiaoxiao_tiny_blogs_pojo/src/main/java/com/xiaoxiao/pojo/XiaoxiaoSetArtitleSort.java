package com.xiaoxiao.pojo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class XiaoxiaoSetArtitleSort implements Serializable
{
    @JsonSerialize(using = ToStringSerializer.class)
    private long articleId;
    @JsonSerialize(using = ToStringSerializer.class)
    private long sortId;

}
