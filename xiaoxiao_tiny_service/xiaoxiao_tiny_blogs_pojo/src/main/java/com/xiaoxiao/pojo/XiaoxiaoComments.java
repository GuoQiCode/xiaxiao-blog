package com.xiaoxiao.pojo;


import lombok.Data;

import java.io.Serializable;

@Data
public class XiaoxiaoComments  implements Serializable
{

  private long commentId;
  private long userId;
  private long articleId;
  private long commentLikeCount;
  private java.sql.Timestamp commentDate;
  private String commentContent;
  private long parentCommentId;
}
