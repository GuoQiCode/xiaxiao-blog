package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoVisit;
import com.xiaoxiao.service.RedisVisitService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * _ooOoo_ o8888888o 88" . "88 (| -_- |) O\ = /O ____/`---'\____ .' \\| |// `. / \\||| : |||// \ / _||||| -:- |||||- \ |
 * | \\\ - /// | | | \_| ''\---/'' | | \ .-\__ `-` ___/-. / ___`. .' /--.--\ `. . __ ."" '< `.___\_<|>_/___.' >'"". | |
 * : `- \`.;`\ _ /`;.`/ - ` : | | \ \ `-. \_ __\ /__ _/ .-` / / ======`-.____`-.___\_____/___.-`____.-'====== `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 佛祖保佑 永无BUG 佛曰: 写字楼里写字间，写字间里程序员； 程序人员写程序，又拿程序换酒钱。 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。 但愿老死电脑间，不愿鞠躬老板前； 奔驰宝马贵者趣，公交自行程序员。 别人笑我忒疯癫，我笑自己命太贱； 不见满街漂亮妹，哪个归得程序员？
 *
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/21:15:25
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_visit_service")
@CrossOrigin(origins = "*", maxAge = 3600)
@ApiOperation("缓存网站信息")
public class RedisVisitController {

    @Autowired
    private RedisVisitService redisVisitService;


    @ApiOperation(value = "缓存网站信息", notes = "缓存网站信息")
    @PostMapping(value = "/insertVisitToRedis")
    public void insertVisitToRedis(@RequestBody XiaoxiaoVisit xiaoxiaoVisit) {
        this.redisVisitService.insertVisitToRedis(xiaoxiaoVisit);
    }


    @ApiOperation(value = "缓存网站信息", notes = "缓存网站信息")
    @PostMapping(value = "/deleteVisitToRedis")
    public void deleteVisitToRedis() {
        this.redisVisitService.deleteVisitToRedis();
    }


    @ApiOperation(value = "缓存网站信息", notes = "缓存网站信息")
    @PostMapping(value = "/getVisitToRedis")
    public XiaoxiaoVisit getVisitToRedis() {
       return this.redisVisitService.getVisitToRedis();
    }


}
