package com.xiaoxiao.pojo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 文章和标签中间表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XiaoxiaoSetArtitleLabel
{

    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long labelId;

}
