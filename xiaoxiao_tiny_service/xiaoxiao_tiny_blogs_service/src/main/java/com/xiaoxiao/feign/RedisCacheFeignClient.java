package com.xiaoxiao.feign;

import com.xiaoxiao.pojo.XiaoxiaoAdminMessage;
import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.pojo.XiaoxiaoMenu;
import com.xiaoxiao.pojo.XiaoxiaoUsers;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
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
     * ======前台页面展示缓存
     */

    /**
     * 清空前端缓存总文章个数
     */
    @PostMapping(value = "/redis_article_service/delete_article_sum_to_redis")
    void deleteArticleSumToRedis();

    /**
     * 删除标签文章缓存
     */
    @PostMapping(value = "/redis_label_service/delete_index_article_label")
    void deleteIndexArticleLabel();

    /**
     * 删除首页分类文章缓存
     */
    @PostMapping(value = "/redis_sorts_service/delete_index_sorts_all_to_redis")
    void deleteIndexSortsAllToRedis();

    /**
     * 删除缓存的后台管理界面
     */
    @PostMapping(value = "/redis_admin_manager_service/delete")
    void deleteAdminManager();

    /**
     * 用户信息修改同步缓存
     *
     * @param users
     */
    @PostMapping(value = "/redis_service/update_user_to_redis")
    void updateUserToRedis(XiaoxiaoUsers users);

    /**
     * 删除文章缓存
     */
    @PostMapping(value = "/redis_article_service/delete_index_article")
    void deleteIndexArticle();

    /**
     * 删除展示我的信息的缓存
     */
    @PostMapping(value = "/redis_service/delete_show_Me")
    void deleteShowMe();

    /**
     * 删除标签文章总数
     */
    @PostMapping(value = "/redis_label_service/delete_label_count")
    void deleteLabelCount();

    /**
     * 删除分类文章
     *
     * @param sortId
     */
    @PostMapping(value = "/redis_article_service/delete_blogs_by_sorts_to_redis")
    void deleteBlogsBySortsToRedis(@RequestParam(name = "sortId") Long sortId);

    /**
     * 删除标签个数
     * 
     * @param sortId
     */
    @PostMapping(value = "/redis_article_service/deleteArticleSortSum")
    void deleteArticleSortSum(@RequestParam(name = "sortId") Long sortId);

    /**
     * 删除根据标签缓存的文章
     * 
     * @param labelId
     */
    @PostMapping(value = "/redis_article_service/delete_article_by_label_id")
    void deleteArticleByLabelId(@RequestParam(name = "labelId") Long labelId);


    /**
     * 删除推荐文章数据
     */
    @PostMapping(value = "/redis_article_service/delete_article_new_recommend")
     void deleteArticleNewRecommend();


    /**
     * 删除标签文章个数缓存
     * @param labelId
     */
    @PostMapping(value = "/redis_article_service/deleteArticleLabelSum")
     void deleteArticleLabelSum(@RequestParam(name = "labelId") Long labelId);
    /**
     * 删除用户信息
     * 
     * @param token
     */
    @PostMapping(value = "/redis_service/deleteUserToRedis")
    void deleteUserToRedis(@RequestParam(name = "token") String token);


    /**
     * 删除归档数据
     */
    @PostMapping(value = "/redis_article_service/delete_article_archive")
    void deleteArticleArchive();

}
