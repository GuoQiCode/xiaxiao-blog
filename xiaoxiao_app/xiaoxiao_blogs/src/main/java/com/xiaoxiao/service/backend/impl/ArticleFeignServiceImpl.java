package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.feign.BlogsFeignServiceClient;
import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.pojo.XiaoxiaoImg;
import com.xiaoxiao.service.backend.ArticleFeignService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import com.xiaoxiao.utils.UploadOSSUtils;
import com.xiaoxiao.utils.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

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
 * @date:2019/11/29:16:37
 * @author:shinelon
 * @Describe:
 */
@Service
public class ArticleFeignServiceImpl implements ArticleFeignService
{

    @Autowired
    private BlogsFeignServiceClient blogsFeignServiceClient;


    @Value("${ENDPOINT}")
    private String ENDPOINT;

    @Value("${ACCESSKEYSECRET}")
    private String ACCESSKEYSECRET;

    @Value("${ACCESSKEYID}")
    private String ACCESSKEYID;

    @Value("${BUCKETNAME}")
    private String BUCKETNAME;

    @Value("${ARTICLE_FILE_PATH}")
    private String ARTICLE_FILE_PATH;

    @Value("${REQUEST_HEAD}")
    private String REQUEST_HEAD;


    /**
     * 查询全部的文章信息
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllArticle(Integer page, Integer rows)
    {
        return this.blogsFeignServiceClient.findAllArticle(page, rows);
    }


    /**
     * 删除文章
     *
     * @param articleId
     * @return
     */
    @Override
    public Result delete(Long articleId)
    {
        return this.blogsFeignServiceClient.deleteArticle(articleId);
    }


    /**
     * 查找一个文章
     *
     * @param articleId
     * @return
     */
    @Override
    public Result findArticleById(Long articleId)
    {
        return this.blogsFeignServiceClient.findArticleById(articleId);
    }

    /**
     * 插入
     *
     * @param xiaoxiaoArticles
     * @return
     */
    @Override
    public Result insert(XiaoxiaoArticles xiaoxiaoArticles)
    {
        return this.blogsFeignServiceClient.insert(xiaoxiaoArticles);
    }


    /**
     * 修改
     *
     * @param xiaoxiaoArticles
     * @return
     */
    @Override
    public Result update(XiaoxiaoArticles xiaoxiaoArticles)
    {
        return this.blogsFeignServiceClient.update(xiaoxiaoArticles);
    }


    /**
     * 根据标题，分类，是否推荐查询信息
     *
     * @param page
     * @param rows
     * @param xiaoxiaoArticles
     * @return
     */
    @Override
    public Result findArticleByTitleOrSorts(Integer page, Integer rows, XiaoxiaoArticles xiaoxiaoArticles)
    {
        return this.blogsFeignServiceClient.findArticleByTitleOrSorts(page, rows, xiaoxiaoArticles);
    }


    /**
     * 修改推荐信息
     *
     * @param articleId
     * @param articleRecommend
     * @return
     */
    @Override
    public Result updateRecommend(Long articleId, String articleRecommend)
    {
        return this.blogsFeignServiceClient.updateRecommend(articleId, articleRecommend);
    }


    /**
     * https://xiaoxiao-xiaoxiao.oss-cn-beijing.aliyuncs.com/xiaoxiao-and-me/cdb43b35a1204d739bab09e84101f6eb.jpg
     *
     * https://xiaoxiao-xiaoxiao.oss-cn-beijing.aliyuncs.comxiaoxiao-and-me/4d70a38edd344a4598c8cd4c2de34ee1.jpg
     * 上传文件
     * @param file
     * @return
     */
    @Override
    public UploadResult upload(MultipartFile file) throws IOException
    {
        String fileName = null;
        try
        {
            fileName = UploadOSSUtils.upload(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET, BUCKETNAME,ARTICLE_FILE_PATH, file.getInputStream());
            Result result = this.blogsFeignServiceClient.insert(new XiaoxiaoImg("", this.ENDPOINT + fileName, new Date(), ""));
            if(result != null && result.getCode() == StatusCode.OK){
                return UploadResult.ok(StatusCode.UPLOADSUCCESS, "", this.REQUEST_HEAD+ARTICLE_FILE_PATH+fileName);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return UploadResult.error(StatusCode.UPLOADERROR,"","");
    }
}
