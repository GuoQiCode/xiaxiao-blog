package com.xiaoxiao.pojo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class XiaoxiaoPhotoAlbum implements Serializable
{
    private String albumId;
    private String albumDesc;
    private String albumName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long albumUserId;
    private Date albumCreateTime;
    private String albumStatus;
}
