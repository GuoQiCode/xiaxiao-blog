package com.xiaoxiao.service.frontline.impl;

import com.xiaoxiao.feign.FrontlineFeignServiceClient;
import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import com.xiaoxiao.service.frontline.FrontlineLeaveMessageService;
import com.xiaoxiao.utils.IDUtils;
import com.xiaoxiao.utils.IPUtils;
import com.xiaoxiao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
 * @date:2019/12/19:10:34
 * @author:shinelon
 * @Describe:
 */
@Service
@SuppressWarnings("all")
public class FrontlineLeaveMessageServiceImpl implements FrontlineLeaveMessageService
{

    @Autowired
    private FrontlineFeignServiceClient client;


    @Override
    public Result getLeaveMessageSum()
    {
        return this.client.getLeaveMessageSum();
    }

    @Override
    public Result getVisitSum()
    {
        return null;
    }

    @Override
    public Result getTodayVisitSum()
    {
        return null;
    }




    @Override
    public Result insert(XiaoxiaoLeaveMessage leaveMessage, HttpServletRequest request)
    {
        /**
         * 获取用户的IP
         */
        leaveMessage.setMessageIp(IPUtils.userIp(request));
        return this.client.insert(leaveMessage);
    }

    @Override
    public Result findAllLeaveMessage(Integer page, Integer rows)
    {
        return this.client.findAllLeaveMessage(page,rows);
    }
}
