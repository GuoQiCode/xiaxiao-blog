package com.xiaoxiao.search.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

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
 * @date:2019/12/13:15:07
 * @author:shinelon
 * @Describe: 检索数据的实体类
 */
@Data
public class SolrArticleDocument implements Serializable
{
    /**
     * 必要属性 是在Solr检索器内的ID
     */
    private String id;
    private Long article_id;
    private Long user_id;
    private Long article_views;
    private Long article_comment_count;
    private Long article_like_count;
    private Long article_bk_sorts_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date article_date;
    private String article_bk_first_img;
    private String article_recommend;
    private String user_profile_photo;
    private String user_nickname;
    private String article_title;
    private String article_desc;
    private String article_type;

}
