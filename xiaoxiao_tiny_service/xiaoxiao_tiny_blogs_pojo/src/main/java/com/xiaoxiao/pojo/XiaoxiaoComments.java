package com.xiaoxiao.pojo;


import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shinelon
 */
@Data
public class XiaoxiaoComments  implements Serializable
{
  private Long commentId;
  private String nickname;
  private Long articleId;
  private Long commentLikeCount;
  private Date commentDate;
  private String commentContent;
  private Long parentCommentId;
  private String email;
  private String commentsNickname;

    /**
     * 存储子评论
     */
  private List<XiaoxiaoComments> list = new ArrayList<>();
}
