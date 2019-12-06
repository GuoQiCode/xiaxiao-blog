package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoUsers;
import com.xiaoxiao.service.RedisUserService;
import com.xiaoxiao.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
 * @date:2019/11/27:09:34
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_service")
@Api(value = "缓存用户")
@CrossOrigin(origins = "*",maxAge = 3600)
public class RedisUserController
{
    @Autowired
    private RedisUserService redisUserService;


    /**
     * 将用户信息插入到redis中
     * @param users
     * @param token
     */
    @PostMapping(value = "/insert_user_to_redis")
    public void insertUserToRedis(@RequestBody XiaoxiaoUsers users,String token){
        this.redisUserService.insertUserToRedis(users,token);
    }



    @PostMapping(value = "/get_user_to_redis")
    public Object getUserToRedis(String token){
        return this.redisUserService.getUserToRedis(token);
    }



    @GetMapping(value = "/login_out")
    @ApiOperation(value = "登出",notes = "登出")
    public void loginOut(String token){
        this.redisUserService.loginOut(token);
    }


    @ApiOperation(value = "删除用户的信息到redis中",notes = "删除用户的信息到redis中")
    @PostMapping(value = "/update_user_to_redis")
    public void updateUserToRedis(@RequestBody XiaoxiaoUsers users){
        this.redisUserService.updateUserToRedis(users);
    }


    @ApiOperation(value = "缓存首页展示我的个人信息",notes = "缓存首页展示我的个人信息")
    @PostMapping(value = "/insert_show_me_to_redis")
    public void insertShowMeToRedis(@RequestBody XiaoxiaoUsers users){
        this.redisUserService.insertShowMeToRedis(users);
    }

    @ApiOperation(value = "获取缓存首页关于我的信息",notes = "获取缓存首页关于我的信息")
    @PostMapping(value = "/get_show_me_to_redis")
    public XiaoxiaoUsers getShowMeToRedis(){
        return this.redisUserService.getShowMeToRedis();
    }
}
