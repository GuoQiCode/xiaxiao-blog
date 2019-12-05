package com.xiaoxiao.contorller.backend;

import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.service.backend.ArticleFeignService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
 * @date:2019/11/29:16:33
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/article")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArticleController
{

    @Autowired
    private ArticleFeignService articleFeignService;


    @RequestMapping(value = "/find_all_article", method = RequestMethod.GET)
    public Result findAllArticle(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page, @RequestParam(name = "rows", defaultValue = "10", required = false) Integer rows)
    {
        return this.articleFeignService.findAllArticle(page, rows);
    }




    @PostMapping(value = "/find_article_by_id")
    public Result findArticleById(Long articleId)
    {
        return this.articleFeignService.findArticleById(articleId);
    }


    @GetMapping(value = "/delete")
    public Result delete(Long articleId)
    {
        return this.articleFeignService.delete(articleId);
    }

    @PostMapping(value = "/insert")
    public Result insert(XiaoxiaoArticles xiaoxiaoArticles)
    {
        return this.articleFeignService.insert(xiaoxiaoArticles);
    }


    @PostMapping(value = "/update")
    public Result update(XiaoxiaoArticles xiaoxiaoArticles)
    {
        return this.articleFeignService.update(xiaoxiaoArticles);
    }


    @PostMapping(value = "/find_article_by_title_or_sorts")
    public Result findArticleByTitleOrSorts(@RequestParam(name = "page",defaultValue = "1",required = false)Integer page,
                                            @RequestParam(name = "rows",defaultValue = "10",required = false)Integer rows,
                                            XiaoxiaoArticles xiaoxiaoArticles)

    {
        return this.articleFeignService.findArticleByTitleOrSorts(page,rows,xiaoxiaoArticles);
    }


    @GetMapping(value = "/update_recommend")
    public Result updateRecommend(@RequestParam(name = "articleId") Long articleId,
                                  @RequestParam(name = "articleRecommend") String articleRecommend){
        return this.articleFeignService.updateRecommend(articleId,articleRecommend);
    }




    @PostMapping(value = "/upload")
    public UploadResult upload(@RequestParam(name = "editormd-image-file") MultipartFile file) throws IOException
    {
        return this.articleFeignService.upload(file);
    }



}
