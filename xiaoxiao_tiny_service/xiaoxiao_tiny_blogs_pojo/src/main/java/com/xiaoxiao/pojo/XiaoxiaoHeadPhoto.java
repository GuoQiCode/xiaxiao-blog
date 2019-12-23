package com.xiaoxiao.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author shinelon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XiaoxiaoHeadPhoto {

  private String headId;
  private String headUrl;
  private String headType;
  private Date headDate;
}
