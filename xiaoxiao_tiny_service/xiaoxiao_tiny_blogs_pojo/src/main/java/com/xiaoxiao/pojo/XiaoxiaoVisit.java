package com.xiaoxiao.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**访问量数据
 * @author shinelon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XiaoxiaoVisit implements Serializable
{
  private String visitId;
  private long visitSum;
  private long visitTodaySum;
}
