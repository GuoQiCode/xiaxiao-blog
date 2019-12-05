package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.pojo.XiaoxiaoSorts;
import com.xiaoxiao.pojo.vo.XiaoxiaoSortsVo;
import com.xiaoxiao.service.RedisSortsService;
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
 * @date:2019/12/1:21:32
 * @author:shinelon
 * @Describe:
 */
@Service
public class RedisSortsServiceImpl implements RedisSortsService
{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 缓存后台信息分类列表
     */
    @Value("${SORTS_KEY}")
    private String SORTS_KEY;


    /**
     * 缓存首页分类文章个数
     */
    @Value("${INDEX_SORTS_KEY}")
    private String INDEX_SORTS_KEY;


    /**
     * 缓存分类信息到redis中
     * @param sorts
     */
    @Override
    public void insertSortAllToRedis(List<XiaoxiaoSorts> sorts)
    {
        this.redisTemplate.opsForValue().set(this.SORTS_KEY, sorts);
    }


    /**
     *获取redis中的缓存
     * @return
     */
    @Override
    public List<XiaoxiaoSorts> getSortsAllToRedis()
    {
        return (List<XiaoxiaoSorts>) this.redisTemplate.opsForValue().get(this.SORTS_KEY);
    }


    /**
     * 删除后台缓存的分类列表
     */
    @Override
    public void deleteSortsAllToRedis()
    {
        this.redisTemplate.delete(this.SORTS_KEY);
    }




    /**
     *缓存首页分类文章信息
     * @param sortsVos
     */
    @Override
    public void insertIndexSortsAllToRedis(List<XiaoxiaoSortsVo> sortsVos)
    {
        this.redisTemplate.opsForValue().set(this.INDEX_SORTS_KEY, sortsVos, 1, TimeUnit.DAYS);
    }


    /**
     * 获取首页分类信息
     * @return
     */
    @Override
    public List<XiaoxiaoSortsVo> getIndexSortsAllToRedis()
    {
        return (List<XiaoxiaoSortsVo>) this.redisTemplate.opsForValue().get(this.INDEX_SORTS_KEY);
    }


    /**
     * 删除首页分类文章显示缓存信息
     */
    @Override
    public void deleteIndexSortsAllToRedis()
    {
        this.redisTemplate.delete(this.INDEX_SORTS_KEY);
    }



}
