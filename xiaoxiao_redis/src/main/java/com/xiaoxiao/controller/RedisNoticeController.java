package com.xiaoxiao.controller;

import com.xiaoxiao.service.RedisNoticeService;
import com.xiaoxiao.utils.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
 * @date:2019/12/24:18:57
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_notice_service")
@CrossOrigin
@Api("缓存公告")
public class RedisNoticeController
{
    @Autowired
    private RedisNoticeService redisNoticeService;


    @ApiOperation(value ="缓存公告信息")
    @PostMapping(value = "/insert")
    public void insert(@RequestBody PageResult result){
        this.redisNoticeService.insert(result);
    }


    @ApiOperation(value ="获取缓存公告信息")
    @PostMapping(value = "/get")
    public PageResult get(){
        return this.redisNoticeService.get();
    }


    @ApiOperation(value ="缓删除存公告信息")
    @PostMapping(value = "/delete")
    public void delete(){
        this.redisNoticeService.delete();
    }

}
