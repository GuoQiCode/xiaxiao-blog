package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoLabelVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoSortsVo;
import com.xiaoxiao.service.RedisArticleService;
import com.xiaoxiao.utils.PageResult;
import com.xiaoxiao.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
 * @date:2019/12/2:21:09
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_article_service")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api(value = "缓存文章")
public class RedisArticleController
{

    @Autowired
    private RedisArticleService redisArticleService;


    @ApiOperation(value = "插入文章个数到缓存中", notes = "插入文章个数到缓存中")
    @PostMapping(value = "/insert_article_sum_to_redis")
    public void insertArticleSumToRedis(@RequestParam("sum") Integer sum)
    {
        this.redisArticleService.insertArticleSumToRedis(sum);
    }


    @ApiOperation(value = "获取文章个数", response = Integer.class, notes = "获取文章个数")
    @PostMapping(value = "/get_article_sum_to_redis")
    public Integer getArticleSumToRedis()
    {
        return this.redisArticleService.getArticleSumToRedis();
    }


    @ApiOperation(value = "删除缓存个数数据", notes = "删除缓存个数数据")
    @PostMapping(value = "/delete_article_sum_to_redis")
    public void deleteArticleSumToRedis()
    {
        this.redisArticleService.deleteArticleSumToRedis();
    }


    @ApiOperation(value = "缓存最新推荐的文章", notes = "缓存最新推荐的文章")
    @PostMapping(value = "/insert_article_new_recommend")
    public void insertArticleNewRecommend(@RequestBody PageResult result)
    {
        this.redisArticleService.insertArticleNewRecommend(result);
    }


    @ApiOperation(value = "获取缓存中存储的推荐文章信息", response = Result.class, notes = "获取缓存中存储的推荐文章信息")
    @PostMapping(value = "/get_article_new_recommend")
    public PageResult getArticleNewRecommend()
    {
        return this.redisArticleService.getArticleNewRecommend();
    }


    @ApiOperation(value = "删除缓存的推荐文章中数据", notes = "删除缓存的推荐文章中数据")
    @PostMapping(value = "/delete_article_new_recommend")
    public void deleteArticleNewRecommend()
    {
        this.redisArticleService.deleteArticleNewRecommend();
    }


    @ApiOperation(value = "缓存首页博客列表数据", notes = "缓存首页博客列表数据")
    @PostMapping(value = "/insert_index_article")
    public void insertIndexArticle(@RequestBody PageResult result,@RequestParam(name = "page")Integer page)
    {
        this.redisArticleService.insertIndexArticle(result,page);
    }


    @ApiOperation(value = "获取首页缓存中的数据", response = PageResult.class, notes = "获取首页缓存中的数据")
    @PostMapping(value = "/get_index_article")
    public PageResult getIndexArticle(@RequestParam(name = "page")Integer page)
    {
        return this.redisArticleService.getIndexArticle(page);
    }


    @ApiOperation(value = "删除首页缓存信息", notes = "删除首页缓存信息")
    @PostMapping(value = "/delete_index_article")
    public void deleteIndexArticle(@RequestParam(name = "page")Integer page)
    {
        this.redisArticleService.deleteIndexArticle(page);
    }


    @ApiOperation(value = "插入分类文章缓存数据", notes = "插入分类文章缓存数据")
    @PostMapping(value = "/insert_blogs_by_sorts_to_redis")
    public void insertBlogsBySortsToRedis(@RequestBody PageResult result
            , @RequestParam(name = "sortId") Long sortId)
    {
        this.redisArticleService.insertBlogsBySortsToRedis(result, sortId);
    }


    @ApiOperation(value = "获取分类文章缓存数据", notes = "获取分类文章缓存数据")
    @PostMapping(value = "/get_blogs_by_sorts_to_redis")
    public PageResult getBlogsBySortsToRedis(@RequestParam(name = "sortId") Long sortId)
    {
        return this.redisArticleService.getBlogsBySortsToRedis(sortId);
    }


    @ApiOperation(value = "删除分类文章缓存",notes = "删除分类文章缓存")
    @PostMapping(value = "/delete_blogs_by_sorts_to_redis")
    public void deleteBlogsBySortsToRedis(@RequestParam(name = "sortId") Long sortId)
    {
        this.redisArticleService.deleteBlogsBySortsToRedis(sortId);
    }


    @ApiOperation(value = "缓存文章详情信息",notes = "缓存文章详情信息")
    @PostMapping(value = "/insert_article_by_id")
    public void insertArticleById(@RequestBody XiaoxiaoArticleVo articles){
        this.redisArticleService.insertArticleById(articles);
    }


    @ApiOperation(value = "获取缓存的文章",response = XiaoxiaoArticles.class,notes = "获取缓存的文章")
    @PostMapping(value = "/get_article_by_id")
    public XiaoxiaoArticleVo getArticleById(@RequestParam(name = "articleId")Long articleId)
    {
        return this.redisArticleService.getArticleById(articleId);
    }


    @ApiOperation(value = "缓存归档文章下信息",notes = "缓存归档文章下信息")
    @PostMapping(value = "/insert_article_archive")
    public void insertArticleArchive(@RequestBody Map<String,List<XiaoxiaoArticleVo>> map){
        this.redisArticleService.insertArticleArchive(map);
    }


    @ApiOperation(value = "获取缓存的归档日志信息",response = Map.class,notes = "获取缓存的归档日志信息")
    @PostMapping(value = "/get_article_archive")
    public Map<String,List<XiaoxiaoArticleVo>>  getArticleArchive(){
        return this.redisArticleService.getArticleArchive();
    }


    @ApiOperation(value = "删除缓存的归档日志信息",notes = "删除缓存的归档日志信息")
    @PostMapping(value = "/delete_article_archive")
    public void deleteArticleArchive(){
        this.redisArticleService.deleteArticleArchive();
    }



    @ApiOperation(value = "缓存标签文章数据",notes = "缓存标签文章数据")
    @PostMapping(value = "/insert_article_by_label_id")
    public void insertArticleByLabelId(@RequestBody PageResult result,@RequestParam(name = "labelId") Long labelId){
        this.redisArticleService.insertArticleByLabelId(result,labelId);
    }


    @ApiOperation(value = "获取根据标签缓存的文章",response = PageResult.class,notes = "获取根据标签缓存的文章")
    @PostMapping(value = "/get_article_by_label_id")
    public PageResult getArticleByLabelId(@RequestParam(name = "labelId") Long labelId){
        return this.redisArticleService.getArticleByLabelId(labelId);
    }


    @ApiOperation(value = "删除缓存标签文章",notes = "删除缓存标签文章")
    @PostMapping(value = "/delete_article_by_label_id")
    public void deleteArticleByLabelId(@RequestParam(name = "labelId") Long labelId){
        this.redisArticleService.deleteArticleByLabelId(labelId);
    }



    @ApiOperation(value = "缓存指定分类文章个数")
    @PostMapping(value = "/insertArticleSortSum")
    public void insertArticleSortSum(@RequestParam(name = "sortId") Long sortId,
                                     @RequestBody XiaoxiaoSortsVo sortsVo){
        this.redisArticleService.insertArticleSortSum(sortId,sortsVo);
    }


    @ApiOperation(value = "获取指定分类文章的个数",response = XiaoxiaoSortsVo.class,notes = "获取指定分类文章的个数")
    @PostMapping(value = "/getArticleSortSum")
    public XiaoxiaoSortsVo getArticleSortSum(@RequestParam(name = "sortId") Long sortId){
        return this.redisArticleService.getArticleSortSum(sortId);
    }

    @ApiOperation(value = "删除的指定分类文章的个数",notes = "删除的指定分类文章的个数")
    @PostMapping(value = "/deleteArticleSortSum")
    public void deleteArticleSortSum(@RequestParam(name = "sortId") Long sortId){
        this.redisArticleService.deleteArticleSortSum(sortId);
    }


    @ApiOperation(value = "缓存标签文章的个数",notes = "缓存标签文章的个数")
    @PostMapping(value = "/insertArticleLabelSum")
    public void insertArticleLabelSum(@RequestParam(name = "labelId") Long labelId, @RequestBody XiaoxiaoLabelVo labelVo){
        this.redisArticleService.insertArticleLabelSum(labelId,labelVo);
    }


    @ApiOperation(value = "获取文章标签个数",response = XiaoxiaoLabelVo.class,notes = "获取文章标签个数")
    @PostMapping(value = "/getArticleLabelSum")
    public XiaoxiaoLabelVo getArticleLabelSum(@RequestParam(name = "labelId") Long labelId){
        return this.redisArticleService.getArticleLabelSum(labelId);
    }

    @ApiOperation(value = "删除文章标签数据",notes = "删除文章标签数据")
    @PostMapping(value = "/deleteArticleLabelSum")
    public void deleteArticleLabelSum(@RequestParam(name = "labelId") Long labelId){
        this.redisArticleService.deleteArticleLabelSum(labelId);
    }



    @ApiOperation(value = "插入文章浏览量数据")
    @PostMapping(value = "/insertArticleView")
    public void insertArticleView(@RequestParam(name = "views") Integer views,
                                  @RequestParam(name = "articleId") Long articleId)
    {
        this.redisArticleService.insertArticleView(views,articleId);
    }

    @PostMapping(value = "getArticleView")
    public Integer getArticleView(@RequestParam(name = "articleId") Long articleId){
        return this.redisArticleService.getArticleView(articleId);
    }


    /**
     * 获取存储的浏览量的数据
     * @return
     */
    @ApiOperation(value = "获取存储的浏览量的数据",response = Map.class,notes = "获取存储的浏览量的数据")
    @PostMapping(value = "/getView")
    public Map<Object, Object> getArticleView(){
        return this.redisArticleService.getArticleView();
    }

}
