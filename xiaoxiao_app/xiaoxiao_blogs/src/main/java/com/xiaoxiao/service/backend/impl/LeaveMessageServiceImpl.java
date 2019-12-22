package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.feign.BlogsFeignServiceClient;
import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import com.xiaoxiao.service.backend.LeaveMessageService;
import com.xiaoxiao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @date:2019/12/22:10:53
 * @author:shinelon
 * @Describe:
 */
@Service
@SuppressWarnings("all")
public class LeaveMessageServiceImpl implements LeaveMessageService
{


    @Autowired
    private BlogsFeignServiceClient client;


    @Override
    public Result findAll(Integer page, Integer rows)
    {
        return this.client.findAllLeaveMessage(page,rows);
    }

    @Override
    public Result delete(XiaoxiaoLeaveMessage messageId)
    {
        return this.client.delete(messageId);
    }

    @Override
    public Result findOne(XiaoxiaoLeaveMessage messageId)
    {
        return this.client.findOne(messageId);
    }

    @Override
    public Result update(XiaoxiaoLeaveMessage xiaoxiaoVisit)
    {
        return this.client.update(xiaoxiaoVisit);
    }

    @Override
    public Result insert(XiaoxiaoLeaveMessage xiaoxiaoVisit)
    {
        return this.client.insert(xiaoxiaoVisit);
    }
}
