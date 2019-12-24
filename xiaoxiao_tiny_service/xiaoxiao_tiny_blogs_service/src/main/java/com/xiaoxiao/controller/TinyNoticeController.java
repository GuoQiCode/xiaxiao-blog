package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoNotice;
import com.xiaoxiao.service.backend.TinyNoticeService;
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
 * @date:2019/12/24:16:35
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/tiny/notice")
@CrossOrigin
@Api("公告")
public class TinyNoticeController
{
    @Autowired
    private TinyNoticeService tinyNoticeService;


    @ApiOperation(value = "分页查询",response = Result.class,notes = "分页查询")
    @PostMapping(value = "/findAll")
    public Result findAll(@RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "rows",defaultValue = "10")Integer rows){

        return this.tinyNoticeService.findAll(page,rows);
    }

    @ApiOperation(value = "查询一个",response = Result.class,notes = "查询一个")
    @PostMapping(value = "/findOne")
    public Result findOne(@RequestBody XiaoxiaoNotice notice){

        return this.tinyNoticeService.findOne(notice);
    }

    @ApiOperation(value = "插入",response = Result.class,notes = "插入")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody XiaoxiaoNotice notice){

        return this.tinyNoticeService.insert(notice);
    }

    @ApiOperation(value = "修改",response = Result.class,notes = "修改")
    @PostMapping(value = "/update")
    public Result update(@RequestBody XiaoxiaoNotice notice){

        return this.tinyNoticeService.update(notice);
    }

    @ApiOperation(value = "删除",response = Result.class,notes = "删除")
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody XiaoxiaoNotice notice){

        return this.tinyNoticeService.delete(notice);
    }
}
