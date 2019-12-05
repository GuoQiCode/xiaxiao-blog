package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.pojo.XiaoxiaoUsers;
import com.xiaoxiao.service.RedisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
 * @date:2019/11/27:09:31
 * @author:shinelon
 * @Describe:
 */
@Service
public class RedisUserServiceImpl implements RedisUserService
{

    @Value("${USER_REDIS_ID}")
    private String USER_REDIS_ID;


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    /**
     * 记录用户的token
     */
    private String token = null;


    /**
     * 将用户信息存储在redis中
     * @param users
     * @param token
     */
    @Override
    public void insertUserToRedis(XiaoxiaoUsers users, String token)
    {
        this.token = token;
        users.setUserPassword("");
        this.redisTemplate.opsForValue().set(this.USER_REDIS_ID+token, users, 1800, TimeUnit.SECONDS);
    }


    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @Override
    public Object getUserToRedis(String token)
    {
        this.token = token;
        return this.redisTemplate.opsForValue().get(this.USER_REDIS_ID+token);
    }


    /**
     * 登出
     * @param token
     */
    @Override
    public void loginOut(String token)
    {
        this.redisTemplate.delete(this.USER_REDIS_ID+token);
    }


    /**
     * 修改
     * @param users
     */
    @Override
    public void updateUserToRedis(XiaoxiaoUsers users)
    {
         this.redisTemplate.opsForValue().set(this.USER_REDIS_ID+token, users);
    }
}
