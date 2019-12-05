package com.xiaoxiao.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XiaoxiaoImg
{
    private String imgId;
    private String imgAddr;
    private Date imgUploadTime;
    private String imgDesc;
}
