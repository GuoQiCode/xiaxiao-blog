package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.vo.XiaoxiaoLeaveMessageVo;
import com.xiaoxiao.service.RedisLeaveMessageService;
import com.xiaoxiao.utils.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/19:15:51
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_leave_message")
@CrossOrigin(origins = "*",maxAge = 3600)
public class RedisLeaveMessageController {

    @Autowired
    private RedisLeaveMessageService redisLeaveMessageService;

    @ApiOperation(value = "缓存留言页面留言信息", notes = "缓存留言页面留言信息")
    @PostMapping(value = "/insertLeaveMessage")
    public void insertLeaveMessage(@RequestBody PageResult result, @RequestParam(name = "page") Integer page) {
        this.redisLeaveMessageService.insertLeaveMessage(result, page);
    }

    @ApiOperation(value = "获取缓存留言页面留言", notes = "虎丘留言页面留言")
    @PostMapping(value = "/getLeaveMessage")
    public PageResult getLeaveMessage(@RequestParam(name = "page") Integer page) {
        return this.redisLeaveMessageService.getLeaveMessage(page);
    }

    @ApiOperation(value = "删除留言页面留言", notes = "删除留言页面留言")
    @PostMapping(value = "/deleteLeaveMessage")
    public void deleteLeaveMessage() {
        this.redisLeaveMessageService.deleteLeaveMessage();
    }

    @ApiOperation(value = "缓存留言个数")
    @PostMapping(value = "/insertLeaveMessageSum")
    public void insertLeaveMessageSum(@RequestBody XiaoxiaoLeaveMessageVo leaveMessageVo) {
        this.redisLeaveMessageService.insertLeaveMessageSum(leaveMessageVo);
    }

    @ApiOperation(value = "获取留言个数")
    @PostMapping(value = "/getLeaveMessageSum")
    public XiaoxiaoLeaveMessageVo getLeaveMessageSum() {
        return this.redisLeaveMessageService.getLeaveMessageSum();
    }

    @ApiOperation(value = "删除留言")
    @PostMapping(value = "/deleteLeaveMessageSum")
    public void deleteLeaveMessageSum() {
        this.redisLeaveMessageService.deleteLeaveMessageSum();
    }
}
