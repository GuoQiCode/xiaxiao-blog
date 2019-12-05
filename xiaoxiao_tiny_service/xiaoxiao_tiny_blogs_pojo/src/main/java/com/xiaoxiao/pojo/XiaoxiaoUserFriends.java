package com.xiaoxiao.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class XiaoxiaoUserFriends implements Serializable
{

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonFormat()
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userFriendsId;
    private String userNote;
    private String userStatus;
}
