package com.xiaoxiao.service.frontline;

import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import com.xiaoxiao.utils.Result;

import javax.servlet.http.HttpServletRequest;

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
 * @date:2019/12/19:10:26
 * @author:shinelon
 * @Describe:
 */
public interface FrontlineLeaveMessageService
{

    /**
     * 获取留言总数
     * @return
     */
    Result getLeaveMessageSum();


    /**
     * 获取访问总数
     * @return
     */
    Result getVisitSum();


    /**
     * 获取今日访问次数
     * @return
     */
    Result getTodayVisitSum();


    /**
     * 插入留言
     * @param leaveMessage
     * @param request
     * @return
     */
    Result insert(XiaoxiaoLeaveMessage leaveMessage, HttpServletRequest request);


    /**
     * 分页查询全部的留言
     * @param page
     * @param rows
     * @return
     */
    Result findAllLeaveMessage(Integer page, Integer rows);
}
