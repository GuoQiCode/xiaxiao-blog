package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoLabels;
import com.xiaoxiao.service.backend.TinyLabelService;
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
 * @date:2019/11/29:11:12
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/tiny_service_label")
@CrossOrigin(origins = {"*"},maxAge = 3600)
@Api(value = "标签接口")
public class TinyLabelController
{


    @Autowired
    private TinyLabelService tinyLabelService;

    @GetMapping(value = "/find_all_label")
    @ApiOperation(value = "查询全部的数据",response = Result.class,notes = "查询全部的数据")
    public Result findAllLabel(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                               @RequestParam(name = "rows", defaultValue = "10", required = false) Integer rows)
    {
        return this.tinyLabelService.findAllLabel(page, rows);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改",response = Result.class,notes = "修改")
    public Result update(@RequestBody XiaoxiaoLabels labels){
        return this.tinyLabelService.update(labels);
    }


    @GetMapping(value = "/find_label_by_id")
    @ApiOperation(value = "查询一个的标签",response = Result.class,notes = "查询一个的标签")
    public Result findLabelById(Long labelId){
        return this.tinyLabelService.findLabelById(labelId);
    }


    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除",response = Result.class,notes = "删除")
    public Result delete(Long labelId){
        return this.tinyLabelService.delete(labelId);
    }


    @PostMapping(value = "/insert")
    @ApiOperation(value = "插入",response = Result.class,notes = "插入")
    public Result insert(@RequestBody XiaoxiaoLabels labels){
        return this.tinyLabelService.insert(labels);
    }

}
