package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoAdminMessage;
import com.xiaoxiao.service.RedisAdminManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

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
 * @date:2019/12/2:11:55
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_admin_manager_service")
public class RedisAdminManagerController
{


    @Autowired
    private RedisAdminManagerService redisAdminManagerService;


    @ApiOperation(value = "将后台的管理菜单缓存到redis中",notes = "将后台的管理菜单缓存到redis中")
    @PostMapping(value = "/insert_admin_manager_to_redis")
    public void insertAdminManagerToRedis(@RequestBody List<XiaoxiaoAdminMessage> messages)
    {
        this.redisAdminManagerService.insertAdminManagerToRedis(messages);
    }


    @ApiOperation(value = "获取redis中的缓存后台菜单的数据",response = List.class,notes = "获取redis中的缓存后台菜单的数据")
    @PostMapping(value = "/get_admin_manager_to_redis")
    public List<XiaoxiaoAdminMessage> getAdminManagerToRedis(){
        return this.redisAdminManagerService.getAdminManagerToRedis();
    }



    @PostMapping(value = "/delete")
    public void deleteAdminManager(){
        this.redisAdminManagerService.delete();
    }



}
