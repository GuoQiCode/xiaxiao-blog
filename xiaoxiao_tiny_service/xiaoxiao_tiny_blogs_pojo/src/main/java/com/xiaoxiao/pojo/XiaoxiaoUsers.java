package com.xiaoxiao.pojo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class XiaoxiaoUsers
{
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String userIp;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userProfilePhoto;
    private Date userRegistrationTime;
    private Date userBirthday;
    private Long userAge;
    private String userTelephoneNumber;
    private String userNickname;
    private String userSignature;
    private String userRealName;
    private String userSex;
    private String userHobby;
    /**
     * 二维码
     */
    private String userQrCode;


}
