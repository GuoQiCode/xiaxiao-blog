package com.xiaoxiao.contorller.frontline;

import com.xiaoxiao.service.frontline.FrontlineArticleService;
import com.xiaoxiao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.StringJoiner;

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
 * @date:2019/12/2:21:02
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/frontline/article")
@CrossOrigin(origins = "*",maxAge = 3600)
public class FrontlineArticleController
{

    @Autowired
    private FrontlineArticleService frontlineArticleService;


    /**
     * 获取的文章的个数
     * @return
     */
    @PostMapping(value = "/find_article_sum")
    public Result findArticleSum(){
        return this.frontlineArticleService.findArticleSum();
    }


    /**
     * 查询推荐文章
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/find_article_new_recommend")
    public Result findArticleNewRecommend(@RequestParam(name = "page",defaultValue = "1")Integer page,
                                          @RequestParam(name = "rows",defaultValue = "10")Integer rows)
    {
        return this.frontlineArticleService.findArticleNewRecommend(page,rows);
    }


    /**
     * 查询首页文章
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/find_index_article")
    public Result findIndexArticle(@RequestParam(name = "page",defaultValue = "1")Integer page,
                                   @RequestParam(name = "rows",defaultValue = "10")Integer rows){

        return this.frontlineArticleService.findIndexArticle(page,rows);
    }


    /**
     * 根虎指定的分类查找文章
     * @param page
     * @param rows
     * @param sortId
     * @return
     */
    @PostMapping(value = "/find_blogs_by_sorts")
    public Result findBlogsBySorts(@RequestParam(name = "page",defaultValue = "1")Integer page,
                                   @RequestParam(name = "rows",defaultValue = "10")Integer rows,
                                   @RequestParam(name = "sortId")Long sortId){
        return this.frontlineArticleService
                .findBlogsBySorts(page,rows,sortId);
    }


    /**
     * 查询指定的文章信息
     * @return
     */
    @PostMapping(value = "/find_blog_by_id")
    public Result findBlogById(Long articleId){
       return this.frontlineArticleService.findBlogById(articleId);
    }


    /**
     * 获取归档文件信息
     * @return
     */
    @PostMapping(value = "/find_article_archive")
    public Result findArticleArchive()
    {
        return this.frontlineArticleService.findArticleArchive();
    }
}
