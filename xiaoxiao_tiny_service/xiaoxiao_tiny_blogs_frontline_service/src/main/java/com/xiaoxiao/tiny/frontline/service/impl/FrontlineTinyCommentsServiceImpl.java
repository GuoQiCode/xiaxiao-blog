package com.xiaoxiao.tiny.frontline.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.pojo.XiaoxiaoComments;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyCommentsMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyCommentsService;
import com.xiaoxiao.tiny.frontline.utils.PageUtils;
import com.xiaoxiao.utils.IDUtils;
import com.xiaoxiao.utils.PageResult;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
 * @date:2019/12/11:23:15
 * @author:shinelon
 * @Describe:
 */
@Service
public class FrontlineTinyCommentsServiceImpl implements FrontlineTinyCommentsService
{

    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;


    @Value("${MARKED_WORDS_FAULT}")
    private String MARKED_WORDS_FAULT;

    @Autowired
    private FrontlineTinyCommentsMapper frontlineTinyCommentsMapper;


    @Autowired
    private RedisCacheFeignClient client;



    @Override
    public Result saveComments(XiaoxiaoComments comments) throws Exception
    {
        comments.setCommentId(IDUtils.genItemId());
        comments.setCommentDate(new Date());
        int i = frontlineTinyCommentsMapper.saveComments(comments);
        if(i > 0){
            try
            {
                this.client.deleteCommentArticle(comments.getArticleId());
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK,this.MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }






    @Override
    public Result findComments(Long articleId, Integer page, Integer rows)
    {
        try
        {
            PageResult commentArticle = this.client.getCommentArticle(articleId);
            if(commentArticle != null && commentArticle.getResult().size() > 0){
                return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,commentArticle);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        PageHelper.startPage(page, rows);
        List<XiaoxiaoComments> all = this.frontlineTinyCommentsMapper.findAll(articleId);
        PageResult result = PageUtils.getResult(new PageInfo<>(all), page);
        List<XiaoxiaoComments> child = getChild(( List<XiaoxiaoComments> )result.getResult());
        result.setResult(child);
        if(result.getResult() != null && result.getResult().size() > 0){
            try
            {
                this.client.insertCommentArticle(articleId, result);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
           return Result.ok(StatusCode.OK, result);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 获取子评论
     * @param comments  查询的全部评论
     */
    public List<XiaoxiaoComments>  getChild(List<XiaoxiaoComments> comments){
        List<XiaoxiaoComments> result = new ArrayList<>();
        if(comments == null || comments.size() < 0){
            return comments;
        }else {

            for (XiaoxiaoComments c : comments)
            {
                if(c.getParentCommentId() == -1){
                    /**
                     * 有子节点
                     */
                    List<XiaoxiaoComments> childComments = this.frontlineTinyCommentsMapper.findChildComments(c.getCommentId());
                    c.setList(childComments);
                    result.add(c);
                }else {
                    /**
                     * 无子节点
                     */
                    result.add(c);
                    continue;
                }
            }
        }
        return result;
    }
}
