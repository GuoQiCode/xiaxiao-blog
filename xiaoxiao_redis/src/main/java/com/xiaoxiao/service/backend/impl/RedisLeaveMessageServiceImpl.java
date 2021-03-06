package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.pojo.vo.XiaoxiaoLeaveMessageVo;
import com.xiaoxiao.service.RedisLeaveMessageService;
import com.xiaoxiao.utils.PageResult;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
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
 * @date:2019/12/19:15:56
 * @author:shinelon
 * @Describe:缓存留言类型hash
 */
@Service
@SuppressAjWarnings("all")
public class RedisLeaveMessageServiceImpl implements RedisLeaveMessageService
{

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Value("${LEAVE_MESSAGE_SUM}")
    private String LEAVE_MESSAGE_SUM;


    @Value("${LEAVE_MESSAGE}")
    private String LEAVE_MESSAGE;

    @Override
    public void insertLeaveMessage(PageResult result, Integer page)
    {
        this.redisTemplate.opsForValue().set(this.LEAVE_MESSAGE+page,result,1,TimeUnit.DAYS);
    }


    @Override
    public PageResult getLeaveMessage(Integer page)
    {

        return (PageResult) this.redisTemplate.opsForValue().get(this.LEAVE_MESSAGE+page);
    }


    @Override
    public void deleteLeaveMessage()
    {
        Set<String> keys = this.redisTemplate.keys(this.LEAVE_MESSAGE + "*");
        this.redisTemplate.delete(keys);
    }

    @Override
    public void insertLeaveMessageSum(XiaoxiaoLeaveMessageVo leaveMessageVo)
    {
        this.redisTemplate.opsForValue().set(LEAVE_MESSAGE_SUM, leaveMessageVo);
    }

    @Override
    public XiaoxiaoLeaveMessageVo getLeaveMessageSum()
    {
        return (XiaoxiaoLeaveMessageVo) this.redisTemplate.opsForValue().get(LEAVE_MESSAGE_SUM);
    }

    @Override
    public void deleteLeaveMessageSum()
    {
        this.redisTemplate.delete(LEAVE_MESSAGE_SUM);

    }
}
