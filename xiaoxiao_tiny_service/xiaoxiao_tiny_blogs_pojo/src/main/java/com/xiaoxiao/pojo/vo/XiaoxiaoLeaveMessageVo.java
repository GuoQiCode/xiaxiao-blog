package com.xiaoxiao.pojo.vo;


import java.io.Serializable;
import java.util.Date;

import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shinelon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XiaoxiaoLeaveMessageVo extends XiaoxiaoLeaveMessage implements Serializable
{
    private Integer sum;
}
