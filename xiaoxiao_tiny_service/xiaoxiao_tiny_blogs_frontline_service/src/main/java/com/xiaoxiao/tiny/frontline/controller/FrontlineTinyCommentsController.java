package com.xiaoxiao.tiny.frontline.controller;

import com.xiaoxiao.pojo.XiaoxiaoComments;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyCommentsService;
import com.xiaoxiao.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
 * @date:2019/12/11:23:07
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/frontline/tiny/comments")
public class FrontlineTinyCommentsController
{

    @Autowired
    private FrontlineTinyCommentsService frontlineTinyCommentsService;



    @ApiOperation(value = "保存文章的评论",response = Result.class,notes = "保存文章的评论")
    @PostMapping(value = "/saveComments")
    public Result saveComments(@RequestBody XiaoxiaoComments comments) throws Exception
    {
        return this.frontlineTinyCommentsService.saveComments(comments);
    }


    @ApiOperation(value = "获取文章评论信息",response = Result.class,notes = "获取文章评论信息")
    @PostMapping(value = "/findComments")
    public Result findComments(@RequestParam(name = "articleId")Long articleId,
                               @RequestParam(name = "page", defaultValue = "1") Integer page,
                               @RequestParam(name = "rows", defaultValue = "10") Integer rows)
    {
        return this.frontlineTinyCommentsService.findComments(articleId,page,rows);
    }

}
