package com.xiaoxiao.controller;

import com.xiaoxiao.service.RedisCommentsService;
import com.xiaoxiao.utils.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * @date:2019/12/12:16:33
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_comments_service")
@CrossOrigin(origins = "*",maxAge = 3600)
public class RedisCommentsController
{

    @Autowired
    private RedisCommentsService redisCommentsService;


    @ApiOperation(value = "缓存文章评论")
    @PostMapping(value = "/insertCommentArticle")
    public void insertCommentArticle(@RequestParam("articleId") Long articleId,
                                     @RequestBody PageResult result){
        this.redisCommentsService.insertCommentArticle(articleId,result);
    }


    @ApiOperation(value = "获取指定的文章评论",response = PageResult.class,notes = "获取指定的文章评论")
    @PostMapping(value = "/getCommentArticle")
    public PageResult getCommentArticle(@RequestParam("articleId") Long articleId){
        return this.redisCommentsService.getCommentArticle(articleId);
    }


    @ApiOperation(value = "删除文章的评论")
    @PostMapping(value = "/deleteCommentArticle")
    public void deleteCommentArticle(@RequestParam("articleId") Long articleId){
        this.redisCommentsService.deleteCommentArticle(articleId);
    }



}
