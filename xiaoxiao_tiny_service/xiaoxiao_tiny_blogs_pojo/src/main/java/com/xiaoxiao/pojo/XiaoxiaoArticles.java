package com.xiaoxiao.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class XiaoxiaoArticles implements Serializable
{
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String articleTitle;
    private String articleContent;
    private Long articleViews;
    private Long articleCommentCount;
    private Date articleDate;
    private Long articleLikeCount;
    private List<String> articleBkImg;
    /**
     * 文章的分类
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleBkSortsId;
    /**
     * 首图
     */
    private String articleBkFirstImg;

    private String articleRecommend;

    private String articleLabel;

    private String articleDesc;

    private String articleType;
}
