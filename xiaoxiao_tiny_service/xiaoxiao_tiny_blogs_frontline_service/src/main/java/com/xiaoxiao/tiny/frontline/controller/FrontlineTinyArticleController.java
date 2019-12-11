package com.xiaoxiao.tiny.frontline.controller;

import com.xiaoxiao.tiny.frontline.service.FrontlineTinyArticleService;
import com.xiaoxiao.utils.Result;
import io.swagger.annotations.Api;
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
 * @date:2019/12/2:20:55
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/frontline/tiny/article")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api(value = "前端文章")
public class FrontlineTinyArticleController
{

    @Autowired
    private FrontlineTinyArticleService frontlineTinyArticleService;


    @ApiOperation(value = "查找全部文章个数", response = Result.class, notes = "查找全部文章个数")
    @PostMapping(value = "/find_article_sum")
    public Result findArticleSum()
    {
        return this.frontlineTinyArticleService.findArticleSum();
    }


    @ApiOperation(value = "查找最新推荐文章", response = Result.class, notes = "查找最新推荐文章")
    @PostMapping(value = "/find_article_new_recommend")
    public Result findArticleNewRecommend(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "rows", defaultValue = "10") Integer rows)
    {
        return this.frontlineTinyArticleService.findArticleNewRecommend(page, rows);
    }



    @ApiOperation(value = "查找首页文章",response = Result.class,notes = "查找首页文章")
    @PostMapping(value = "/find_index_article")
    public Result findIndexArticle(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "rows", defaultValue = "10") Integer rows)
    {
        return this.frontlineTinyArticleService.findIndexArticle(page,rows);
    }


    @ApiOperation(value = "根据分类查询文章")
    @PostMapping(value = "/find_blogs_by_sorts")
    public Result findBlogsBySorts(@RequestParam(name = "page",defaultValue = "1") Integer page,
                            @RequestParam(name = "rows",defaultValue = "10") Integer rows,
                            @RequestParam(name = "sortId") Long sortId){
        return this.frontlineTinyArticleService.findBlogsBySorts(page,rows,sortId);
    }



    @ApiOperation(value = "获取文章的详细信息",response = Result.class,notes = "获取文章的详细信息")
    @PostMapping(value = "/find_blog_by_id")
    public Result findBlogById(@RequestParam("articleId") Long articleId){
        return this.frontlineTinyArticleService.findBlogById(articleId);
    }


    @ApiOperation(value = "获取分类文章的年份",response = Result.class,notes = "获取分类文章的年份")
    @PostMapping(value = "/find_article_of_year")
    public  Result findArticleOfYear(){
        return this.frontlineTinyArticleService.findArticleOfYear();
    }


    @ApiOperation(value = "根据标签查询文章",response = Result.class,notes = "根据标签查询文章")
    @PostMapping(value = "/find_article_by_label_id")
    public Result findArticleByLabelId(@RequestParam(name = "labelId") Long labelId,
                                       @RequestParam(name = "page",defaultValue = "1") Integer page,
                                       @RequestParam(name = "rows",defaultValue = "10") Integer rows)
    {
        return this.frontlineTinyArticleService.findArticleByLabelId(labelId,page,rows);
    }



    @ApiOperation(value = "获取分类文章的个数")
    @PostMapping(value = "/find_article_by_sort_sum")
    public Result findArticleBySortSum(@RequestParam(name = "sortId") Long sortId){
        return this.frontlineTinyArticleService.findArticleBySortSum(sortId);
    }


    /**
     *
     * @param labelId
     * @return
     */
    @ApiOperation(value = "获取标签文章的个数",
    response = Result.class,notes = "获取标签文章的个数")
    @PostMapping(value = "/find_article_label_sum")
    public Result findArticleLabelSum(@RequestParam(name = "labelId") Long labelId){
        return this.frontlineTinyArticleService.findArticleLabelSum(labelId);
    }


}
