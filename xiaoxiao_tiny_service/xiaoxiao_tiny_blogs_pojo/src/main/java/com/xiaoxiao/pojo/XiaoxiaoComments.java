package com.xiaoxiao.pojo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class XiaoxiaoComments  implements Serializable
{
  private Long commentId;
  private Long userId;
  private Long articleId;
  private Long commentLikeCount;
  private Date commentDate;
  private String commentContent;
  private Long parentCommentId;
}
