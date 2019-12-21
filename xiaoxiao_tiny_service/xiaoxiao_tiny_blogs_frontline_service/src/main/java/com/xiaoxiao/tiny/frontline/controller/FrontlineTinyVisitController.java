package com.xiaoxiao.tiny.frontline.controller;

import com.xiaoxiao.tiny.frontline.service.FrontlineTinyVisitService;
import com.xiaoxiao.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @date:2019/12/21:14:34
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/frontline/tiny/visit")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api("获取访问量数据")
public class FrontlineTinyVisitController
{


    @Autowired
    private FrontlineTinyVisitService frontlineTinyVisitService;




    @PostMapping(value = "/getVisitMessages")
    @ApiOperation(value = "获取浏览的信息",response = Result.class,notes = "获取浏览的信息")
    public Result getVisitMessages(){
        return this.frontlineTinyVisitService.getVisitMessages();
    }



    @RequestMapping(value = "/getVisitSum")
    @ApiOperation(value = "获取的总访问量",response = Result.class,notes = "获取的总访问量")
    public Result getVisitSum(){
        return this.frontlineTinyVisitService.frontlineTinyVisitService();
    }


    @RequestMapping(value = "/getTodaySum")
    @ApiOperation(value = "获取今日的访问量",response = Result.class,notes = "获取今日的访问量")
    public Result getTodaySum(){
        return this.frontlineTinyVisitService.getTodaySum();
    }

}
