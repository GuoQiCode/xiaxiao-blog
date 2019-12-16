package com.xiaoxiao.provider;

import com.xiaoxiao.pojo.XiaoxiaoSetArtitleLabel;
import org.apache.ibatis.jdbc.SQL;

/**

 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/3:13:59
 * @author:shinelon
 * @Describe:
 */
public class SetArticleLabelProvider {

    public String insert(final XiaoxiaoSetArtitleLabel articleLabel){
        return new SQL(){
                {
                INSERT_INTO("xiaoxiao_set_artitle_label");
                if(articleLabel.getArticleId() != null)
                {
                    VALUES("article_id", "#{articleLabel.articleId}");
                }
                if(articleLabel.getLabelId() != null)
                {
                    VALUES("label_id", "#{articleLabel.labelId}");
                }
            }
        }.toString();
    }
}
