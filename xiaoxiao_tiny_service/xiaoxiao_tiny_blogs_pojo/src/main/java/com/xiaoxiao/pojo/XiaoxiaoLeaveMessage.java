package com.xiaoxiao.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shinelon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XiaoxiaoLeaveMessage implements Serializable
{
  private String messageId;
  private String messageNickname;
  private String messageHeadPortrait;
  private String messageContent;
  private Date messageDate;
  private String messageIp;
  private String messageEmail;
  private String messageParentId;
  private List<XiaoxiaoLeaveMessage> list = new ArrayList<>();
  private String reply;
}
