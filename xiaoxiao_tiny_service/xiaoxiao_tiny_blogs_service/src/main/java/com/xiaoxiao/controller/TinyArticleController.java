package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.service.backend.TinyArticleService;
import com.xiaoxiao.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date:2019/11/29:16:44
 * @author:shinelon
 * @Describe:文章服务
 */
@RestController
@RequestMapping(value = "/tiny_article_service")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@Api(value = "文章接口")
public class TinyArticleController
{


    @Autowired
    private TinyArticleService tinyArticleService;


    @GetMapping(value = "/find_all_article")
    @ApiOperation(value = "/查询全部的文章信息", response = Result.class, notes = "查询全部的文章信息")
    public Result findAllArticle(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page, @RequestParam(name = "rows", defaultValue = "10", required = false) Integer rows)
    {
        return this.tinyArticleService.findAllArticle(page, rows);
    }


    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除文章", response = Result.class, notes = "删除文章")
    public Result delete(@RequestParam(name = "articleId") Long articleId)
    {
        return this.tinyArticleService.delete(articleId);
    }


    @GetMapping(value = "/find_article_by_id")
    @ApiOperation(value = "查找一个文章", response = Result.class, notes = "查找一个文章")
    public Result findArticleById(@RequestParam(name = "articleId") Long articleId)
    {
        return this.tinyArticleService.findArticleById(articleId);
    }


    @ApiOperation(value = "插入", response = Result.class, notes = "插入")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody XiaoxiaoArticles xiaoxiaoArticles)
    {
        return this.tinyArticleService.insert(xiaoxiaoArticles);
    }

    @ApiOperation(value = "修改", response = Result.class, notes = "修改")
    @PostMapping(value = "/update")
    public Result update(@RequestBody XiaoxiaoArticles xiaoxiaoArticles)
    {
        return this.tinyArticleService.update(xiaoxiaoArticles);
    }


    @ApiOperation(value = "根据标题、分类、是否推荐查询信息", response = Result.class, notes = "根据标题、分类、是否推荐查询信息")
    @PostMapping(value = "/tiny_article_service/find_article_by_title_or_sorts")
    public Result findArticleByTitleOrSorts(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page, @RequestParam(name = "rows", defaultValue = "10", required = false) Integer rows, @RequestBody XiaoxiaoArticles xiaoxiaoArticles)
    {

        return this.tinyArticleService.findArticleByTitleOrSorts(page, rows, xiaoxiaoArticles);
    }


    @ApiOperation(value = "修改文章是否推荐", response = Result.class, notes = "修改文章是否推荐")
    @GetMapping(value = "/update_recommend")
    public Result updateRecommend(@RequestParam(name = "articleId") Long articleId, @RequestParam(name = "articleRecommend") String articleRecommend)
    {
        return this.tinyArticleService.updateRecommend(articleId, articleRecommend);
    }

}
