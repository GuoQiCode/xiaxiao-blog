package com.xiaoxiao.pojo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class XiaoxiaoPhoto implements Serializable
{

    private String photoId;
    private String photoDesc;
    private String photoAddr;
    private String photoAlbumId;
    private Date photoUploadTime;
    private String photoStatus;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long photoUserId;
    private String photoIndex;
}
