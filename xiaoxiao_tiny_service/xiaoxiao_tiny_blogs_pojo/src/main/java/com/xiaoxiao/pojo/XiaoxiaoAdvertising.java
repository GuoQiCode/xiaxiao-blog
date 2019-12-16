package com.xiaoxiao.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 广告位
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XiaoxiaoAdvertising {
  private String advertisingId;
  private String advertisingImg;
  private String advertisingAlt;
  private String advertisingUrl;
  private String advertisingLevel;
  private String advertisingRemark;
}
