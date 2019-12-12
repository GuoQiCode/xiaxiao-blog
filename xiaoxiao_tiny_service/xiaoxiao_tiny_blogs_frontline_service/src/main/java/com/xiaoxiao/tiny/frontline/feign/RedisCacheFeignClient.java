package com.xiaoxiao.tiny.frontline.feign;

import com.xiaoxiao.pojo.*;
import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoLabelVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoSortsVo;
import com.xiaoxiao.utils.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * _ooOoo_ o8888888o 88" . "88 (| -_- |) O\ = /O ____/`---'\____ .' \\| |// `. / \\||| : |||// \ / _||||| -:- |||||- \ |
 * | \\\ - /// | | | \_| ''\---/'' | | \ .-\__ `-` ___/-. / ___`. .' /--.--\ `. . __ ."" '< `.___\_<|>_/___.' >'"". | |
 * : `- \`.;`\ _ /`;.`/ - ` : | | \ \ `-. \_ __\ /__ _/ .-` / / ======`-.____`-.___\_____/___.-`____.-'====== `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 佛祖保佑 永无BUG 佛曰: 写字楼里写字间，写字间里程序员； 程序人员写程序，又拿程序换酒钱。 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。 但愿老死电脑间，不愿鞠躬老板前； 奔驰宝马贵者趣，公交自行程序员。 别人笑我忒疯癫，我笑自己命太贱； 不见满街漂亮妹，哪个归得程序员？
 *
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/2:09:48
 * @author:shinelon
 * @Describe:
 */
@FeignClient("xiaoxiao-redis")
public interface RedisCacheFeignClient {

    /**
     *
     * ===================菜单缓存=================
     *
     */

    /**
     * 插入缓存
     *
     * @param menus
     */
    @PostMapping(value = "/redis_menu_service/insert_menu_to_redis")
    void insertMenuToRedis(@RequestBody List<XiaoxiaoMenu> menus);

    /**
     * 请求缓存数据
     *
     * @return
     */
    @PostMapping(value = "/redis_menu_service/get_menu_to_redis")
    List<XiaoxiaoMenu> getMenuToRedis();

    /**
     * =============================缓存后台管理菜单
     */

    /**
     * 插入
     *
     * @param messages
     */
    @PostMapping(value = "/redis_admin_manager_service/insert_admin_manager_to_redis")
    void insertAdminManagerToRedis(@RequestBody List<XiaoxiaoAdminMessage> messages);

    /**
     * 获取
     *
     * @return
     */
    @PostMapping(value = "/redis_admin_manager_service/get_admin_manager_to_redis")
    List<XiaoxiaoAdminMessage> getAdminManagerToRedis();

    /**
     * 首頁文章分类信息
     */

    /**
     * @param sortsVos
     */
    @PostMapping(value = "/redis_sorts_service/insert_index_sorts_all_to_redis")
    void insertIndexSortsAllToRedis(@RequestBody List<XiaoxiaoSortsVo> sortsVos);

    /**
     * 获取
     *
     * @return
     */
    @PostMapping(value = "/redis_sorts_service/get_index_sorts_all_to_redis")
    List<XiaoxiaoSortsVo> getIndexSortsAllToRedis();

    /**
     *
     * =========================文章缓存
     *
     */

    /**
     * 插入文章个数
     *
     * @param sum
     */
    @ApiOperation(value = "插入文章个数到缓存中", notes = "插入文章个数到缓存中")
    @PostMapping(value = "/redis_article_service/insert_article_sum_to_redis")
    void insertArticleSumToRedis(@RequestParam(name = "sum") Integer sum);

    /**
     * 获取文章个数
     *
     * @return
     */
    @ApiOperation(value = "获取文章个数", response = Integer.class, notes = "获取文章个数")
    @PostMapping(value = "/redis_article_service/get_article_sum_to_redis")
    Integer getArticleSumToRedis();

    /**
     * 插入文章推荐缓存信息
     *
     * @param result
     */
    @PostMapping(value = "/redis_article_service/insert_article_new_recommend")
    void insertArticleNewRecommend(@RequestBody PageResult result);

    /**
     * 获取文章推荐缓存信息
     */
    @PostMapping(value = "/redis_article_service/get_article_new_recommend")
    PageResult getArticleNewRecommend();

    /**
     * 缓存首页博客
     *
     * @param result
     */
    @PostMapping(value = "/redis_article_service/insert_index_article")
    void insertIndexArticle(@RequestBody PageResult result);

    /**
     * 获取首页博客
     *
     * @return
     */
    @PostMapping(value = "/redis_article_service/get_index_article")
    PageResult getIndexArticle();

    /**
     * 缓存分类文章
     *
     * @param sortId
     * @return
     */
    @PostMapping(value = "/redis_article_service/get_blogs_by_sorts_to_redis")
    PageResult getBlogsBySortsToRedis(@RequestParam(name = "sortId") Long sortId);

    /**
     * 插入分类缓存
     *
     * @param result
     * @param sortId
     */
    @PostMapping(value = "/redis_article_service/insert_blogs_by_sorts_to_redis")
    void insertBlogsBySortsToRedis(@RequestBody PageResult result, @RequestParam(name = "sortId") Long sortId);

    /**
     * 插入首页标签文章
     *
     * @param result
     */
    @PostMapping(value = "/redis_label_service/insert_index_article_label")
    void insertIndexArticleLabel(@RequestBody PageResult result);

    /**
     * 获取首页标签文章
     *
     * @return
     */
    @PostMapping(value = "/redis_label_service/get_index_article_label")
    PageResult getIndexArticleLabel();

    /**
     * 插入首页缓存关于我
     *
     * @param users
     */
    @PostMapping(value = "/redis_service/insert_show_me_to_redis")
    void insertShowMeToRedis(@RequestBody XiaoxiaoUsers users);

    /**
     * 获取首页缓存关于我
     *
     * @return
     */
    @PostMapping(value = "/redis_service/get_show_me_to_redis")
    XiaoxiaoUsers getShowMeToRedis();

    /**
     * 插入缓存
     *
     * @param list
     */
    @PostMapping(value = "/redis_hobby_service/insert_hobby_to_redis")
    void insertHobbyToRedis(@RequestBody List<XiaoxiaoHobby> list);

    /**
     * 获取缓存
     *
     * @return
     */
    @PostMapping(value = "/redis_hobby_service/get_hobby_to_redis")
    List<XiaoxiaoHobby> getHobbyToRedis();

    @PostMapping(value = "/redis_label_service/insert_label_to_redis")
    void insertLabelToRedis(@RequestBody List<XiaoxiaoLabels> labels);

    @PostMapping(value = "/redis_label_service/get_label_to_redis")
    List<XiaoxiaoLabels> getLabelToRedis();

    /**
     * 插入文章信息
     *
     * @param articles
     */
    @PostMapping(value = "/redis_article_service/insert_article_by_id")
    void insertArticleById(@RequestBody XiaoxiaoArticles articles);

    /**
     * 获取文章信息
     *
     * @param articleId
     * @return
     */
    @PostMapping(value = "/redis_article_service/get_article_by_id")
    XiaoxiaoArticleVo getArticleById(@RequestParam(name = "articleId") Long articleId);

    /**
     * 缓存归档日志信息
     * 
     * @param map
     */
    @PostMapping(value = "/redis_article_service/insert_article_archive")
    void insertArticleArchive(@RequestBody Map<String, List<XiaoxiaoArticleVo>> map);

    /**
     * 获取缓存的归档日志
     * 
     * @return
     */
    @PostMapping(value = "/redis_article_service/get_article_archive")
    Map<String, List<XiaoxiaoArticleVo>> getArticleArchive();

    /**
     * 插入文章的总数
     * 
     * @param xiaoxiaoLabelVo
     */
    @PostMapping(value = "/redis_label_service/insert_label_count")
    void insertLabelCount(@RequestBody XiaoxiaoLabelVo xiaoxiaoLabelVo);

    /**
     * 获取文章的总数
     * 
     * @return
     */
    @PostMapping(value = "/redis_label_service/get_label_count")
    XiaoxiaoLabelVo getLabelCount();

    /**
     * 插入标签文章数据
     * 
     * @param result
     * @param labelId
     */
    @PostMapping(value = "/redis_article_service/insert_article_by_label_id")
    void insertArticleByLabelId(@RequestBody PageResult result, @RequestParam(name = "labelId") Long labelId);

    /**
     * 获取标签文章数据
     * 
     * @param labelId
     * @return
     */
    @PostMapping(value = "/redis_article_service/get_article_by_label_id")
    PageResult getArticleByLabelId(@RequestParam(name = "labelId") Long labelId);

    /**
     * 缓存指定分类文章个数
     * 
     * @param sortId
     * @param sortsVo
     */
    @PostMapping(value = "/redis_article_service/insertArticleSortSum")
    void insertArticleSortSum(@RequestParam(name = "sortId") Long sortId, @RequestBody XiaoxiaoSortsVo sortsVo);

    /**
     * 获取指定分类文章的个数
     * 
     * @param sortId
     * @return
     */
    @PostMapping(value = "/redis_article_service/getArticleSortSum")
    XiaoxiaoSortsVo getArticleSortSum(@RequestParam(name = "sortId") Long sortId);

    /**
     * 缓存标签文章的个数
     * 
     * @param labelId
     * @param labelVo
     */
    @PostMapping(value = "/redis_article_service/insertArticleLabelSum")
    void insertArticleLabelSum(@RequestParam(name = "labelId") Long labelId, @RequestBody XiaoxiaoLabelVo labelVo);

    /**
     * 获取文章标签个数
     * 
     * @param labelId
     * @return
     */
    @PostMapping(value = "/redis_article_service/getArticleLabelSum")
    XiaoxiaoLabelVo getArticleLabelSum(@RequestParam(name = "labelId") Long labelId);

    /**
     * 插入文章评论缓存
     * 
     * @param articleId
     * @param result
     */
    @PostMapping(value = "/redis_comments_service/insertCommentArticle")
    void insertCommentArticle(@RequestParam("articleId") Long articleId, @RequestBody PageResult result);

    /**
     * 获取文章评论
     * 
     * @param articleId
     * @return
     */
    @PostMapping(value = "/redis_comments_service/getCommentArticle")
    PageResult getCommentArticle(@RequestParam("articleId") Long articleId);

    /**
     * 删除文章评论
     * 
     * @param articleI
     */
    @PostMapping(value = "/redis_comments_service/deleteCommentArticle")
    void deleteCommentArticle(@RequestParam("articleId") Long articleI);

}
