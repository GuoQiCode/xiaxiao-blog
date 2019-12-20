package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoAdvertising;
import com.xiaoxiao.service.RedisAdvertisingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
 * @date:2019/12/16:18:08
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_advertising_service")
@CrossOrigin(origins = "*",maxAge = 3600)
public class RedisAdvertisingController
{
    @Autowired
    private RedisAdvertisingService redisAdvertisingService;


    @ApiOperation(value = "缓存首页广告信息",notes = "缓存首页广告信息")
    @PostMapping(value = "/insertAdvertisingToRedis")
    public void insertAdvertisingToRedis(@RequestBody List<XiaoxiaoAdvertising> list){
        this.redisAdvertisingService.insertAdvertisingToRedis(list);
    }


    @ApiOperation(value = "获取首页文章缓存",response = List.class,notes = "获取首页文章缓存")
    @PostMapping(value = "/getAdvertisingToRedis")
    public List<XiaoxiaoAdvertising> getAdvertisingToRedis(){
        return this.redisAdvertisingService.getAdvertisingToRedis();
    }


    @ApiOperation(value = "删除首页广告缓存",notes = "删除首页广告缓存")
    @PostMapping(value = "/deleteAdvertisingToRedis")
    public void deleteAdvertisingToRedis(){
        this.redisAdvertisingService.deleteAdvertisingToRedis();
    }

}
