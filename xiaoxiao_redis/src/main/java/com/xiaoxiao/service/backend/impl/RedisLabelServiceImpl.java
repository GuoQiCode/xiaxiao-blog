package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.pojo.XiaoxiaoLabels;
import com.xiaoxiao.service.RedisLabelService;
import com.xiaoxiao.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
 * @date:2019/12/1:22:33
 * @author:shinelon
 * @Describe:
 */
@Service
public class RedisLabelServiceImpl implements RedisLabelService
{


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 标签缓存
     */
    @Value("${LABEL_KEY}")
    private String LABEL_KEY;

    /**
     * 首页标签文章
     */
    @Value("${INDEX_LABEL_ARTICLE}")
    private String INDEX_LABEL_ARTICLE;


    /**
     * 插入
     * @param labels
     */
    @Override
    public void insertLabelToRedis(List<XiaoxiaoLabels> labels)
    {
        redisTemplate.opsForValue().set(this.LABEL_KEY, labels);
    }


    /**
     * 获取s
     * @return
     */
    @Override
    public List<XiaoxiaoLabels> getLabelToRedis()
    {
        return (List<XiaoxiaoLabels>)this.redisTemplate.opsForValue().get(this.LABEL_KEY);
    }


    /**
     * 缓存首页标签文章
     * @param result
     */
    @Override
    public void insertIndexArticleLabel(PageResult result)
    {
        this.redisTemplate.opsForValue().set(this.INDEX_LABEL_ARTICLE, result, 1, TimeUnit.DAYS);
    }

    /**
     *获取首页标签文章
     * @return
     */
    @Override
    public PageResult getIndexArticleLabel()
    {
        return (PageResult) this.redisTemplate.opsForValue().get(this.INDEX_LABEL_ARTICLE);
    }


    /**
     * 删除首页标签文章
     */
    @Override
    public void deleteIndexArticleLabel()
    {
        this.redisTemplate.delete(this.INDEX_LABEL_ARTICLE);
    }
}
