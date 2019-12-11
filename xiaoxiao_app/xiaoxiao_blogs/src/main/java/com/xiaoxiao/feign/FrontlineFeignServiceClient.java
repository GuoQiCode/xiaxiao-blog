package com.xiaoxiao.feign;

import com.xiaoxiao.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
 * @date:2019/12/2:15:27
 * @author:shinelon
 * @Describe:
 */
@FeignClient("xiaoxiao-tiny-blogs-frontline-service")
public interface FrontlineFeignServiceClient
{

    /**
     * ======================================首页菜单
     *
     */

    /**
     *
     * @return
     */
    @PostMapping(value = "/frontline/tiny/menu/find_all_menu")
    Result findAllMenu();


    /**
     *======================================分类信息
     */


    /**
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/frontline/tiny/sorts/find_all_sorts")
    Result findAllSorts(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "rows",defaultValue = "10") Integer rows);


    /**
     *
     * =====================================文章
     *
     */

    /**
     *
     * @return
     */
    @PostMapping(value = "/frontline/tiny/article/find_article_sum")
    Result findArticleSum();


    /**
     * 缓存文章推荐信息
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/frontline/tiny/article/find_article_new_recommend")
    Result findArticleNewRecommend(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                   @RequestParam(name = "rows",defaultValue = "10") Integer rows);



    @PostMapping(value = "/frontline/tiny/article/find_index_article")
    Result findIndexArticle(@RequestParam(name = "page",defaultValue = "1") Integer page,
                            @RequestParam(name = "rows",defaultValue = "10") Integer rows);



    @PostMapping(value = "/frontline/tiny/article/find_blogs_by_sorts")
    Result findBlogsBySorts(@RequestParam(name = "page",defaultValue = "1") Integer page,
                            @RequestParam(name = "rows",defaultValue = "10") Integer rows,
                            @RequestParam(name = "sortId") Long sortId);

    /**
     * =========================================标签
     *
     */


    /**
     *
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/frontline/tiny/label/find_index_label_article")
    Result findIndexLabelArticle(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                 @RequestParam(name = "rows",defaultValue = "10") Integer rows);


    /**
     * 关于我信息
     * @return
     */
    @PostMapping(value = "/frontline/tiny/user/show_me")
    Result showMe();


    /**
     * 获取我的爱好
     * @return
     */
    @PostMapping(value = "/frontline/tiny/hobby/find_me_hobby")
    Result findMeHobby();


    /**
     * 获取全部的标签
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/frontline/tiny/label/find_all_label")
    Result findAllLabel(@RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "rows",defaultValue = "10")Integer rows);


    /**
     * 获取详情信息
     * @param articleId
     * @return
     */
    @PostMapping(value = "/frontline/tiny/article/find_blog_by_id")
    Result findBlogById(@RequestParam("articleId")Long articleId);


    /**
     *获取 文章归档信息
     * @return
     */
    @PostMapping(value = "/frontline/tiny/article/find_article_of_year")
    Result findArticleOfYear();


    /**
     * 获取标签个数
     * @return
     */
    @PostMapping(value = "/frontline/tiny/label/count")
    Result labelCount();


    /**
     * 获取文章的全部标签
     * @param articleId
     * @return
     */
    @PostMapping(value = "/frontline/tiny/label/find_article_label_name")
    Result findArticleLabelName(@RequestParam("articleId") Long articleId);


    /**
     * 获取标签文章
     * @param labelId
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/frontline/tiny/article/find_article_by_label_id")
    Result findArticleByLabelId(@RequestParam(value = "labelId") Long labelId,
                                @RequestParam(name = "page",defaultValue = "1")Integer page,
                                @RequestParam(name = "rows",defaultValue = "10")Integer rows);


    /**
     * 获取分类文章的个数
     * @param sortId
     * @return
     */
    @PostMapping(value = "/frontline/tiny/article/find_article_by_sort_sum")
    Result findArticleBySortSum(@RequestParam("sortId") Long sortId);


    /**
     * 获取标签文章的个数
     * @param labelId
     * @return
     */
    @PostMapping(value = "/frontline/tiny/article/find_article_label_sum")
    Result findArticleLabelSum(@RequestParam(name = "labelId") Long labelId);

}
