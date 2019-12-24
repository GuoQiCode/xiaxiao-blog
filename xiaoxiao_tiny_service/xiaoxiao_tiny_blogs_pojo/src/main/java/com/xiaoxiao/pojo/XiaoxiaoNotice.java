package com.xiaoxiao.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * 公告
 * @author shinelon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XiaoxiaoNotice implements Serializable
{

  private String id;
  private String title;
  private String content;
  private Date date;
  private String type;
  private String author;

}
