package com.xiaoxiao.tiny.frontline.provider;

import com.alibaba.druid.VERSION;
import com.xiaoxiao.pojo.XiaoxiaoComments;
import org.apache.ibatis.jdbc.SQL;

/**
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * O\  =  /O
 * ____/`---'\____
 * .'  \\|     |//  `.
 * /  \\|||  :  |||//  \
 * /  _||||| -:- |||||-  \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |   |
 * \  .-\__  `-`  ___/-. /
 * ___`. .'  /--.--\  `. . __
 * ."" '<  `.___\_<|>_/___.'  >'"".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `-.   \_ __\ /__ _/   .-` /  /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 写字楼里写字间，写字间里程序员；
 * 程序人员写程序，又拿程序换酒钱。
 * 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。
 * 但愿老死电脑间，不愿鞠躬老板前；
 * 奔驰宝马贵者趣，公交自行程序员。
 * 别人笑我忒疯癫，我笑自己命太贱；
 * 不见满街漂亮妹，哪个归得程序员？
 *
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/11:23:17
 * @author:shinelon
 * @Describe:
 */
public class CommentsProvider
{

    public String saveComments(final XiaoxiaoComments comments){
        return new SQL(){
            {
                INSERT_INTO("xiaoxiao_comments");

                if(comments.getCommentId() != null){
                    VALUES("comment_id", "#{comments.commentId}");
                }
                if(comments.getUserId() != null){
                    VALUES("user_id", "#{comments.userId}");
                }
                if(comments.getArticleId() != null){
                    VALUES("article_id", "#{comments.articleId}");
                }
                if(comments.getCommentLikeCount() != null){
                    VALUES("comment_like_count", "#{comments.commentLikeCount}");
                }
                if(comments.getCommentDate() != null){
                    VALUES("comment_date", "#{comments.commentDate}");
                }
                if(comments.getCommentContent() != null && comments.getCommentContent() != ""){
                    VALUES("comment_content", "#{comments.commentContent}");
                }
                if(comments.getParentCommentId() != null){
                    VALUES("parent_comment_id", "#{comments.parentCommentId}");
                }
            }
        }.toString();
    }
}
