package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.feign.BlogsFeignServiceClient;
import com.xiaoxiao.pojo.XiaoxiaoAdminMessage;
import com.xiaoxiao.service.backend.AdminManagerService;
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
 * @date:2019/12/2:11:46
 * @author:shinelon
 * @Describe:
 */
@Service
public class AdminManagerServiceImpl implements AdminManagerService
{

    @Autowired
    private BlogsFeignServiceClient client;


    /**
     * 查找全部的后台管理菜单
     * @return
     */
    @Override
    public Result findAllManager()
    {
        return this.client.findAllAdminManager();
    }


    /**
     * 查询全
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllManager(Integer page, Integer rows)
    {
        return this.client.findAllManager(page,rows);
    }


    /**
     * 插入
     * @param message
     * @return
     */
    @Override
    public Result insert(XiaoxiaoAdminMessage message)
    {
        return this.client.insert(message);
    }


    /**
     * 删除
     * @param adminId
     * @return
     */
    @Override
    public Result deleteAdminManager(String adminId)
    {
        return this.client.deleteAdminManager(adminId);
    }


    /**
     * 获取一个的
     * @param adminId
     * @return
     */
    @Override
    public Result findAdminManagerById(Long adminId)
    {
        return this.client.findAdminManagerById(adminId);
    }
}
