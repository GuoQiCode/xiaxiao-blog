package com.xiaoxiao.service.backend;

import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import com.xiaoxiao.utils.Result;

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
 * @date:2019/12/22:11:06
 * @author:shinelon
 * @Describe:
 */
public interface LeaveMessageService
{

    /**
     * 查询全部数据
     * @param page
     * @param rows
     * @return
     */
    Result findAllLeaveMessage(Integer page, Integer rows);


    /**
     * 删除
     * @param messageId
     * @return
     */
    Result delete(XiaoxiaoLeaveMessage messageId);


    /**
     * 获取一个
     * @param messageId
     * @return
     */
    Result findOne(XiaoxiaoLeaveMessage messageId);


    /**
     * 修改
     * @param xiaoxiaoVisit
     * @return
     */
    Result update(XiaoxiaoLeaveMessage xiaoxiaoVisit);


    /**
     *  插入
     * @param xiaoxiaoVisit
     * @return
     */
    Result insert(XiaoxiaoLeaveMessage xiaoxiaoVisit);
}
