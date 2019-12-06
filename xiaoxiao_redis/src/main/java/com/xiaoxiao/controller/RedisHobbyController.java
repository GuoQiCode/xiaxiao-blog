package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoHobby;
import com.xiaoxiao.service.RedisHobbyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
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
 * @date:2019/12/5:18:02
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_hobby_service")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api(value = "缓存爱好")
public class RedisHobbyController
{
    @Autowired
    private RedisHobbyService redisHobbyService;


    @ApiOperation(value = "缓存我的爱好",notes = "缓存我的爱好")
    @PostMapping(value = "/insert_hobby_to_redis")
    public void insertHobbyToRedis(@RequestBody List<XiaoxiaoHobby> list){
        this.redisHobbyService.insertHobbyToRedis(list);
    }

    @ApiOperation(value = "缓存我的爱好",notes = "缓存我的爱好")
    @PostMapping(value = "/get_hobby_to_redis")
    public List<XiaoxiaoHobby> getHobbyToRedis(){
        return this.redisHobbyService.getHobbyToRedis();
    }

}
