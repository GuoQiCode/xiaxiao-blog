package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoHeadPhoto;
import com.xiaoxiao.service.backend.TinyHeadPhotoService;
import com.xiaoxiao.utils.Result;
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
 * @date:2019/12/22:21:07
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/tiny_head_photo_service")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TinyHeadPhotoController
{
    @Autowired
    private TinyHeadPhotoService tinyHeadPhotoService;


    @ApiOperation(value = "获取全部的头像",response = Result.class,notes = "获取全部的头像")
    @PostMapping(value = "/findAll")
    public Result findAllHeadPhoto(@RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "rows",defaultValue = "10")Integer rows){
        return this.tinyHeadPhotoService.findAll(page,rows);
    }

    @ApiOperation(value = "查找一个",response = Result.class,notes = "查找一个")
    @PostMapping(value = "/findOne")
    public  Result findOne(@RequestBody XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto){
        return this.tinyHeadPhotoService.findOne(xiaoxiaoHeadPhoto);
    }


    @ApiOperation(value = "删除",response = Result.class,notes = "删除")
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto){
        return this.tinyHeadPhotoService.delete(xiaoxiaoHeadPhoto);
    }

    @ApiOperation(value = "插入",response = Result.class,notes = "插入")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto){
        return this.tinyHeadPhotoService.insert(xiaoxiaoHeadPhoto);
    }

    @ApiOperation(value = "修改",response = Result.class,notes = "修改")
    @PostMapping(value = "/update")
    public Result  update(@RequestBody XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto){
        return this.tinyHeadPhotoService.update(xiaoxiaoHeadPhoto);
    }

}
