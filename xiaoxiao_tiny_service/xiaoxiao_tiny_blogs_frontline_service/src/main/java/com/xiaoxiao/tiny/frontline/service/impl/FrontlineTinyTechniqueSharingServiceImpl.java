package com.xiaoxiao.tiny.frontline.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyArticleMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyTechniqueSharingService;
import com.xiaoxiao.tiny.frontline.utils.PageUtils;
import com.xiaoxiao.utils.PageResult;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import io.swagger.annotations.SwaggerDefinition;
import net.bytebuddy.asm.Advice;
import org.bouncycastle.jcajce.provider.symmetric.TEA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Struct;
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
 * @date:2019/12/16:09:57
 * @author:shinelon
 * @Describe:
 */
@Service
@SuppressWarnings("all")
public class FrontlineTinyTechniqueSharingServiceImpl implements FrontlineTinyTechniqueSharingService
{

    @Autowired
    private FrontlineTinyArticleMapper frontlineTinyArticleMapper;

    @Autowired
    private RedisCacheFeignClient client;





    @Override
    public Result gainTechniqueSharingArticle(Integer page, Integer rows)
    {
        try
        {
            PageResult techniqueSharingArticle = this.client.getTechniqueSharingArticle();
            if(techniqueSharingArticle != null && techniqueSharingArticle.getResult().size() > 0){
                return Result.ok(StatusCode.OK, true, Result.MARKED_WORDS_SUCCESS, techniqueSharingArticle);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        PageHelper.startPage(page, rows);
        List<XiaoxiaoArticles> recommend = this.frontlineTinyArticleMapper.gainTechniqueSharingArticleRecommend();
        if(recommend!= null && recommend.size() > 0){
            PageResult result = PageUtils.getResult(new PageInfo<XiaoxiaoArticles>(recommend), page);
            try
            {
                this.client.insertTechniqueSharingArticle(result);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, true, Result.MARKED_WORDS_SUCCESS, result);
        }
        return Result.error(StatusCode.ERROR, Result.MARKED_WORDS_FAULT);
    }
}
