package com.xiaoxiao.feign;

import com.xiaoxiao.pojo.*;
import com.xiaoxiao.utils.Result;
import feign.Param;
import javafx.scene.chart.ValueAxis;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
 * @date:2019/11/27:08:52
 * @author:shinelon
 * @Describe:
 */
@FeignClient("xiaoxiao-tiny-blogs-service")
public interface BlogsFeignServiceClient
{
    /**
     * 菜单
     */
    @GetMapping(value = "/tiny_service_menu/find_all_menu")
    Result findAllMenu();


    /**
     * ================================================分类
     */

    /**
     *
     * @param page
     * @param rows
     * @return
     */
    @GetMapping(value = "/tiny_service_sort/find_all_sorts")
    Result findAllSorts(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "rows",defaultValue = "10") Integer rows);



    @GetMapping(value = "/tiny_service_sort/find_all")
    Result findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "rows",defaultValue = "10") Integer rows);


    /**
     * 删除一个分类
     * @param sortId
     * @return
     */
    @GetMapping(value = "/tiny_service_sort/delete")
    Result delete(@RequestParam(name = "sortId") Long sortId);


    /**
     * 根据ID查询
     * @param sortId
     * @return
     */
    @GetMapping(value = "/tiny_service_sort/find_sort_by_id")
    Result findSortById(@RequestParam(name = "sortId") Long sortId);


    /**
     * 修改
     * @param sorts
     * @return
     */
    @PostMapping(value = "/tiny_service_sort/update")
    Result update(@RequestBody XiaoxiaoSorts sorts);


    /**
     * 新增
     * @param sorts
     * @return
     */
    @PostMapping(value = "/tiny_service_sort/insert")
    Result insert(@RequestBody XiaoxiaoSorts sorts);


    /**
     *
     * ==================================================================================
     *
     */



    /**
     * 查询全部的标签
     * @param page
     * @param rows
     * @return
     */
    @GetMapping(value = "/tiny_service_label/find_all_label")
    Result findAllLabel(@RequestParam(name = "page",defaultValue = "1",required = false)Integer page,
                        @RequestParam(name = "rows",defaultValue = "10",required = false)Integer rows);


    /**
     * 修改标签
     * @param labels
     * @return
     */
    @GetMapping(value = "/tiny_service_label/update")
    Result update(@RequestBody XiaoxiaoLabels labels);


    /**
     * 查找一个标签
     * @param labelId
     * @return
     */
    @GetMapping(value = "/tiny_service_label/find_label_by_id")
    Result findLabelById(@RequestParam(name = "labelId") Long labelId);


    /**
     * 删除
     * @param labelId
     * @return
     */
    @GetMapping(value = "/tiny_service_label/delete")
    Result deleteLabelsById(@RequestParam(name = "labelId") Long labelId);

    /**
     *插入
     * @param labels
     * @return
     */
    @PostMapping(value = "/tiny_service_label/insert")
    Result insert(@RequestBody XiaoxiaoLabels labels);


    /**
     *  文章管理
     * =======================================================================
     */


    /**
     * @param page
     * @param rows
     * @return
     */
    @GetMapping(value = "/tiny_article_service/find_all_article")
    Result findAllArticle(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "rows",defaultValue = "10")Integer rows);



    /**
     * 删除文章信息
     * @param articleId
     * @return
     */
    @GetMapping(value = "/tiny_article_service/delete")
    Result deleteArticle(@RequestParam(name = "articleId") Long articleId);


    /**
     * 查找一个文章
     * @param articleId
     * @return
     */
    @GetMapping(value = "/tiny_article_service/find_article_by_id")
    Result findArticleById(@RequestParam(name = "articleId") Long articleId);


    /**
     * 插入一个文章
     * @param xiaoxiaoArticles
     * @return
     */
    @PostMapping(value = "/tiny_article_service/insert")
    Result insert(@RequestBody XiaoxiaoArticles xiaoxiaoArticles);


    /**
     * 修改一个文件
     * @param xiaoxiaoArticles
     * @return
     */
    @PostMapping(value = "/tiny_article_service/update")
    Result update(@RequestBody XiaoxiaoArticles xiaoxiaoArticles);


    /**
     * 根据分类，标题，是否推荐查询的信息
     * @param page
     * @param rows
     * @param xiaoxiaoArticles
     * @return
     */
    @PostMapping(value = "/tiny_article_service/find_article_by_title_or_sorts")
    Result findArticleByTitleOrSorts(@RequestParam(name = "page",defaultValue = "1",required = false)Integer page,
                                     @RequestParam(name = "rows",defaultValue = "10",required = false)Integer rows,
                                     @RequestBody XiaoxiaoArticles xiaoxiaoArticles);


    /**
     * 修改状态
     * @param articleId
     * @param articleRecommend
     * @return
     */
    @GetMapping(value = "/tiny_article_service/update_recommend")
    Result updateRecommend(@RequestParam(name = "articleId") Long articleId,
                           @RequestParam(name = "articleRecommend") String articleRecommend);


    /***
     * ==================================图片管理
     *
     */




    /**
     * 插入图片信息
     * @param img
     * @return
     */
    @PostMapping(value = "/tiny_img_service/insert")
    Result insert(@RequestBody XiaoxiaoImg img);


    /**
     *
     * ==================================后台管理服务
     *
     */


    /**
     *查询全部的后台管理菜单
     * @return
     */
    @PostMapping(value = "/tiny_admin_manager_service/find_all_manager")
    Result findAllAdminManager();


    /**
     *
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/tiny_admin_manager_service/find_manager_all")
    Result findAllManager(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "rows",defaultValue = "10") Integer rows);


    /**
     * 插入
     * @param message
     * @return
     */
    @PostMapping(value = "/tiny_admin_manager_service/insert")
    Result insert(@RequestBody XiaoxiaoAdminMessage message);


    /**
     * 删除
     * @param adminId
     * @return
     */
    @PostMapping(value = "/tiny_admin_manager_service/delete")
    Result deleteAdminManager(@RequestParam(name = "adminId") String adminId);


    /**
     * 查找一个
     * @param adminId
     * @return
     */
    @PostMapping(value = "/tiny_admin_manager_service/find_admin_manager_by_id")
    Result findAdminManagerById(@RequestParam("adminId") Long adminId);



    /**
     * 爱好管理
     */


    /**
     *
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/tiny_admin_hobby_service/find_all")
    Result findAllHobby(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "rows",defaultValue = "10") Integer rows);


    /**
     * 删除爱好
     * @param hobbyId
     * @return
     */
    @PostMapping(value = "/tiny_admin_hobby_service/delete_hobby_by_id")
    Result deleteHobbyById(@RequestParam(name = "hobbyId") String hobbyId);

    /**
     * 获取用户的爱好
     * @param userId
     * @return
     */
    @PostMapping(value = "/tiny_admin_hobby_service/find_user_hobby")
    Result findUserHobby(@RequestParam(name = "userId") Long userId);

    /**
     * 插入新的爱好
     * @param hobby
     * @return
     */
    @PostMapping(value = "/tiny_admin_hobby_service/insert")
    Result insert(@RequestBody XiaoxiaoHobby hobby);


    /**
     *
     * @return
     */
    @PostMapping(value = "/tiny_admin_hobby_service/find_all_hobby")
    Result findAllHobby();




    /**
     * 修改用户个人信息
     * @param users
     * @return
     */
    @PostMapping(value = "/tiny_user_admin/update")
    Result update(@RequestBody XiaoxiaoUsers users);


}
