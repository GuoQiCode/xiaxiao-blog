package com.xiaoxiao.tiny.frontline.component;

import com.xiaoxiao.pojo.*;
import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoLabelVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoSortsVo;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.utils.PageResult;
import org.springframework.stereotype.Component;

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
 * @date:2019/12/17:11:01
 * @author:shinelon
 * @Describe:
 */
@Component
public class FrontlineTinyRedisFallback implements RedisCacheFeignClient
{
    @Override
    public void insertMenuToRedis(List<XiaoxiaoMenu> menus)
    {

    }

    @Override
    public List<XiaoxiaoMenu> getMenuToRedis()
    {
        return null;
    }

    @Override
    public void insertAdminManagerToRedis(List<XiaoxiaoAdminMessage> messages)
    {

    }

    @Override
    public List<XiaoxiaoAdminMessage> getAdminManagerToRedis()
    {
        return null;
    }

    @Override
    public void insertIndexSortsAllToRedis(List<XiaoxiaoSortsVo> sortsVos)
    {

    }

    @Override
    public List<XiaoxiaoSortsVo> getIndexSortsAllToRedis()
    {
        return null;
    }

    @Override
    public void insertArticleSumToRedis(Integer sum)
    {

    }

    @Override
    public Integer getArticleSumToRedis()
    {
        return null;
    }

    @Override
    public void insertArticleNewRecommend(PageResult result)
    {

    }

    @Override
    public PageResult getArticleNewRecommend()
    {
        return null;
    }

    @Override
    public void insertIndexArticle(PageResult result, Integer page)
    {

    }

    @Override
    public PageResult getIndexArticle(Integer page)
    {
        return null;
    }


    @Override
    public PageResult getBlogsBySortsToRedis(Long sortId)
    {
        return null;
    }

    @Override
    public void insertBlogsBySortsToRedis(PageResult result, Long sortId)
    {

    }

    @Override
    public void insertIndexArticleLabel(PageResult result)
    {

    }

    @Override
    public PageResult getIndexArticleLabel()
    {
        return null;
    }

    @Override
    public void insertShowMeToRedis(XiaoxiaoUsers users)
    {

    }

    @Override
    public XiaoxiaoUsers getShowMeToRedis()
    {
        return null;
    }

    @Override
    public void insertHobbyToRedis(List<XiaoxiaoHobby> list)
    {

    }

    @Override
    public List<XiaoxiaoHobby> getHobbyToRedis()
    {
        return null;
    }

    @Override
    public void insertLabelToRedis(List<XiaoxiaoLabels> labels)
    {

    }

    @Override
    public List<XiaoxiaoLabels> getLabelToRedis()
    {
        return null;
    }

    @Override
    public void insertArticleById(XiaoxiaoArticles articles)
    {

    }

    @Override
    public XiaoxiaoArticleVo getArticleById(Long articleId)
    {
        return null;
    }

    @Override
    public void insertArticleArchive(Map<String, List<XiaoxiaoArticleVo>> map)
    {

    }

    @Override
    public Map<String, List<XiaoxiaoArticleVo>> getArticleArchive()
    {
        return null;
    }

    @Override
    public void insertLabelCount(XiaoxiaoLabelVo xiaoxiaoLabelVo)
    {

    }

    @Override
    public XiaoxiaoLabelVo getLabelCount()
    {
        return null;
    }

    @Override
    public void insertArticleByLabelId(PageResult result, Long labelId)
    {

    }

    @Override
    public PageResult getArticleByLabelId(Long labelId)
    {
        return null;
    }

    @Override
    public void insertArticleSortSum(Long sortId, XiaoxiaoSortsVo sortsVo)
    {

    }

    @Override
    public XiaoxiaoSortsVo getArticleSortSum(Long sortId)
    {
        return null;
    }

    @Override
    public void insertArticleLabelSum(Long labelId, XiaoxiaoLabelVo labelVo)
    {

    }

    @Override
    public XiaoxiaoLabelVo getArticleLabelSum(Long labelId)
    {
        return null;
    }

    @Override
    public void insertCommentArticle(Long articleId, PageResult result)
    {

    }

    @Override
    public PageResult getCommentArticle(Long articleId)
    {
        return null;
    }

    @Override
    public void deleteCommentArticle(Long articleI)
    {

    }

    @Override
    public void insertTechniqueSharingArticle(PageResult result)
    {

    }

    @Override
    public PageResult getTechniqueSharingArticle()
    {
        return null;
    }

    @Override
    public void insertArticleView(Integer views, Long articleId)
    {

    }

    @Override
    public Integer getArticleView(Long articleId)
    {
        return null;
    }

    @Override
    public Map<Object, Object> getArticleView()
    {
        return null;
    }

    @Override
    public void insertAdvertisingToRedis(List<XiaoxiaoAdvertising> list)
    {

    }

    @Override
    public List<XiaoxiaoAdvertising> getAdvertisingToRedis()
    {
        return null;
    }

    @Override
    public void insertLeaveMessage(PageResult result, Integer page)
    {

    }

    @Override
    public PageResult getLeaveMessage(Integer page)
    {
        return null;
    }

    @Override
    public void deleteLeaveMessage()
    {

    }
}
