package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.pojo.XiaoxiaoAdminMessage;
import com.xiaoxiao.service.RedisAdminManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
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
 * @date:2019/12/2:11:59
 * @author:shinelon
 * @Describe:
 */
@Service
public class RedisAdminManagerServiceImpl implements RedisAdminManagerService
{


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${ADMIN_MANAGER_KEY}")
    private String ADMIN_MANAGER_KEY;


    /**
     * 插入
     * @param messages
     */
    @Override
    public void insertAdminManagerToRedis(List<XiaoxiaoAdminMessage> messages)
    {
           this.redisTemplate.opsForValue().set(this.ADMIN_MANAGER_KEY, messages);
    }


    /**
     * 获取
     * @return
     */
    @Override
    public List<XiaoxiaoAdminMessage> getAdminManagerToRedis()
    {
        return (List<XiaoxiaoAdminMessage>)this.redisTemplate.opsForValue().get(this.ADMIN_MANAGER_KEY);
    }


    /**
     * 删除
     */
    @Override
    public void delete()
    {
        this.redisTemplate.delete(this.ADMIN_MANAGER_KEY);
    }
}
