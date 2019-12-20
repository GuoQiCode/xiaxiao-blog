package com.xiaoxiao.tiny.frontline.controller;

import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyLeaveMessageService;
import com.xiaoxiao.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.support.ResourceHolderSupport;
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
 * @date:2019/12/19:14:54
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/frontline/tiny/leave/message")
public class FrontlineTinyLeaveMessageController
{

    @Autowired
    private FrontlineTinyLeaveMessageService frontlineTinyLeaveMessageService;


    @ApiOperation(value = "获取留言插入的信息",response = Result.class,notes = "获取留言插入的信息")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody XiaoxiaoLeaveMessage leaveMessage){
        return this.frontlineTinyLeaveMessageService.insert(leaveMessage);
    }



    @ApiOperation(value = "分页查询留言",response = Result.class,notes = "分页查询留言")
    @PostMapping(value = "/findAllLeaveMessage")
    public Result findAllLeaveMessage(@RequestParam(name = "page",defaultValue = "1")Integer page,
                               @RequestParam(name = "rows",defaultValue = "10")Integer rows){
        return this.frontlineTinyLeaveMessageService.findAllLeaveMessage(page,rows);
    }



    @ApiOperation(value = "获取留言的个数",response = Result.class,notes = "获取留言的个数")
    @PostMapping(value = "/getLeaveMessageSum")
    public Result getLeaveMessageSum(){
        return this.frontlineTinyLeaveMessageService.getLeaveMessageSum();
    }
}
