package com.xiaoxiao.search.service.impl;

import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.search.mapper.SearchArticleMapper;
import com.xiaoxiao.search.pojo.SolrArticleDocument;
import com.xiaoxiao.search.service.ArticleSolrService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.CollectionParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import java.util.List;

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
 * @date:2019/12/13:14:00
 * @author:shinelon
 * @Describe:
 */
@Service
public class ArticleSolrServiceImpl implements ArticleSolrService
{

    @Autowired
    private SearchArticleMapper articleMapper;

    @Autowired
    private SolrTemplate solrTemplate;


    /**
     * 核心库
     */
    @Value("${spring.data.core}")
    private String core;


    @Override
    public Result importArticleToSolr()
    {
        List<XiaoxiaoArticleVo> allArticle = this.articleMapper.findAllArticle();
        try
        {
            insertToSolr(allArticle);
            return Result.ok(StatusCode.OK,Result.MARKED_WORDS_SUCCESS);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Result.error(StatusCode.ERROR, Result.MARKED_WORDS_FAULT);
    }


    public void insertToSolr(List<XiaoxiaoArticleVo> list){
        try
        {
            for (XiaoxiaoArticleVo x:list
            )
            {
                SolrInputDocument document = new SolrInputDocument();
                document.setField("article_id", x.getArticleId());
                document.setField("user_id", x.getUserId());
                document.setField("article_views", x.getArticleViews());
                document.setField("article_comment_count", x.getArticleCommentCount());
                document.setField("article_date", x.getArticleDate());
                document.setField("article_like_count", x.getArticleLikeCount());
                document.setField("article_bk_sorts_id", x.getArticleBkSortsId());
                document.setField("article_bk_first_img", x.getArticleBkFirstImg());
                document.setField("article_recommend", x.getArticleRecommend());
                document.setField("user_profile_photo", x.getUserProfilePhoto());
                document.setField("user_nickname", x.getUserNickname());
                document.setField("article_title", x.getArticleTitle());
                document.setField("article_desc", x.getArticleDesc());
                document.setField("article_type", x.getArticleType());
                this.solrTemplate.saveDocument(core, document).wait(10000);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }





    @Override
    @SuppressWarnings("all")
    public Result searchArticle(String q, Long page, Integer rows)
    {
        //设置高亮查询条件
        try
        {
            HighlightQuery query = new SimpleHighlightQuery();
            //设置检索域
            Criteria criteria = new Criteria("item_keywords");
            criteria.is(q); //放入查询关键字
            query.addCriteria(criteria);
            //设置高亮属性
            HighlightOptions highlightOptions = new HighlightOptions();
            highlightOptions.addField("article_title");//设置高亮显示的域
            highlightOptions.setSimplePrefix("<em style='color:red'>");//设置高亮的样式的前缀
            highlightOptions.setSimplePostfix("</em>");
            query.setHighlightOptions(highlightOptions);
            //分页
            query.setOffset((page -1) * rows);
            query.setRows(rows);
            //设置高亮设置
            HighlightPage<SolrArticleDocument> highlightPage = this.solrTemplate.queryForHighlightPage(this.core, query, SolrArticleDocument.class);
            List<HighlightEntry<SolrArticleDocument>> highlighted = highlightPage.getHighlighted();
            for (HighlightEntry<SolrArticleDocument> tbItemHighlightEntry : highlighted)
            {
                SolrArticleDocument entity = tbItemHighlightEntry.getEntity();//实体对象，原始的实体对象
                List<HighlightEntry.Highlight> highlights = tbItemHighlightEntry.getHighlights();
                //如果有高亮，就取高亮 主要的方法是getSnipplets用于获取高亮数据
                if (highlights != null && highlights.size() > 0 && highlights.get(0).getSnipplets().size() > 0)
                {
                    //将高亮数据设置的到实体列
                    entity.setArticle_title(highlights.get(0).getSnipplets().get(0));
                }
            }
            //返回数据
            List<SolrArticleDocument> list = highlightPage.getContent();
            return Result.ok(StatusCode.OK,true,Result.MARKED_WORDS_SUCCESS,list);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Result.error(StatusCode.ERROR, Result.MARKED_WORDS_FAULT);
    }



    @Override
    public void insertArticleToSolr(Long articleId) throws Exception
    {
        List<XiaoxiaoArticleVo> articleById = this.articleMapper.findArticleById(articleId);
        insertToSolr(articleById);
        this.solrTemplate.commit(this.core);
    }


    @Override
    public void deleteArticleToSolr(Long articleId) throws Exception
    {
        Criteria criteria = new Criteria("article_id");
        criteria.is(articleId);
        this.solrTemplate.delete(this.core,new SimpleQuery(criteria));
        this.solrTemplate.commit(this.core);
    }
}



