package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import com.xiaoxiao.pojo.XiaoxiaoVisit;
import com.xiaoxiao.service.backend.LeaveMessageService;
import com.xiaoxiao.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * _ooOoo_ o8888888o 88" . "88 (| -_- |) O\ = /O ____/`---'\____ .' \\| |// `. / \\||| : |||// \ / _||||| -:- |||||- \ |
 * | \\\ - /// | | | \_| ''\---/'' | | \ .-\__ `-` ___/-. / ___`. .' /--.--\ `. . __ ."" '< `.___\_<|>_/___.' >'"". | |
 * : `- \`.;`\ _ /`;.`/ - ` : | | \ \ `-. \_ __\ /__ _/ .-` / / ======`-.____`-.___\_____/___.-`____.-'====== `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 佛祖保佑 永无BUG 佛曰: 写字楼里写字间，写字间里程序员； 程序人员写程序，又拿程序换酒钱。 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。 但愿老死电脑间，不愿鞠躬老板前； 奔驰宝马贵者趣，公交自行程序员。 别人笑我忒疯癫，我笑自己命太贱； 不见满街漂亮妹，哪个归得程序员？
 *
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/22:11:01
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/backend_leave_message")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api("留言管理")
public class TinyLeaveMessageController {

    @Autowired
    private LeaveMessageService leaveMessageService;

    /**
     * 分页展示数据
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/findAll")
    @ApiOperation(value = "分页展示数据",response = Result.class,notes = "分页展示数据")
    public Result findAllLeaveMessage(@RequestParam(name = "page", defaultValue = "1") Integer page,
        @RequestParam(name = "rows", defaultValue = "10") Integer rows) {
        return this.leaveMessageService.findAllLeaveMessage(page,rows);
    }

    /**
     * 删除
     * 
     * @param xiaoxiaoLeaveMessage
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除",response = Result.class,notes = "删除")
    public Result delete(@RequestBody XiaoxiaoLeaveMessage xiaoxiaoLeaveMessage) {
        return this.leaveMessageService.delete(xiaoxiaoLeaveMessage);
    }

    /**
     * 获取一个
     * 
     * @param xiaoxiaoLeaveMessage
     * @return
     */
    @PostMapping(value = "/findOne")
    @ApiOperation(value = "获取一个",response = Result.class,notes = "获取一个")
    public Result findOne(@RequestBody XiaoxiaoLeaveMessage xiaoxiaoLeaveMessage) {
        return this.leaveMessageService.findOne(xiaoxiaoLeaveMessage);
    }

    /**
     * 修改
     * @param xiaoxiaoLeaveMessage
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation(value = "修改",response = Result.class,notes = "修改")
    public Result update(@RequestBody XiaoxiaoLeaveMessage xiaoxiaoLeaveMessage) {
        return this.leaveMessageService.update(xiaoxiaoLeaveMessage);
    }

    /**
     * 插入
     * @param xiaoxiaoLeaveMessage
     * @return
     */
    @PostMapping(value = "/insert")
    @ApiOperation(value = "插入",response = Result.class,notes = "插入")
    public Result insert(@RequestBody XiaoxiaoLeaveMessage xiaoxiaoLeaveMessage) {
        return this.leaveMessageService.insert(xiaoxiaoLeaveMessage);
    }

}
