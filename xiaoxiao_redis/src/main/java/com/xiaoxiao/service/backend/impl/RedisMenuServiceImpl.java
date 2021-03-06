package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.pojo.XiaoxiaoMenu;
import com.xiaoxiao.service.RedisMenuService;
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
 * @date:2019/12/2:09:36
 * @author:shinelon
 * @Describe:
 */
@Service
public class RedisMenuServiceImpl implements RedisMenuService
{

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${MENU_KEY}")
    private String MENU_KEY;


    /**
     * 缓存菜单
     * @param menus
     */
    @Override
    public void insertMenuToRedis(List<XiaoxiaoMenu> menus)
    {
        this.redisTemplate.opsForValue().set(MENU_KEY, menus);
    }


    /**
     * 取出菜单缓存
     * @return
     */
    @Override
    public List<XiaoxiaoMenu> getMenuToRedis()
    {
        return (List<XiaoxiaoMenu>) this.redisTemplate.opsForValue().get(this.MENU_KEY);
    }


    /**
     * 删除缓存中的菜单
     */
    @Override
    public void deleteMenuToRedis()
    {
        this.redisTemplate.delete(this.MENU_KEY);
    }
}
