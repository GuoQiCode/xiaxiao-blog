package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoImg;
import com.xiaoxiao.service.backend.TinyImgService;
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
 * @date:2019/12/1:17:55
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/tiny_img_service")
@Api(value = "图片接口")
public class TinyImgController
{

    @Autowired
    public TinyImgService tinyImgService;


    @ApiOperation(value = "图片插入",response = Result.class,notes = "图片插入")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody XiaoxiaoImg img){
        return this.tinyImgService.insert(img);
    }




    @ApiOperation(value = "修改信息",response = Result.class,notes = "修改信息")
    @PostMapping(value = "/update")
    public Result update(@RequestBody XiaoxiaoImg img){
        return this.tinyImgService.update(img);
    }



    @ApiOperation(value = "删除",response = Result.class,notes = "删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam("imgId") String imgId){
        return this.tinyImgService.delete(imgId);
    }



    @ApiOperation(value = "查找一个的图片信息",response = Result.class,notes = "查找一个的图片信息")
    @GetMapping(value = "/find_img_by_id")
    public Result findImgById(@RequestParam(name = "imgId") String imgId){
        return this.tinyImgService.findImgById(imgId);
    }



    @ApiOperation(value = "查找全部",response = Result.class,notes = "查找全部")
    @GetMapping(value = "/find_all")
    public Result findAll(@RequestParam(name = "page")Integer page,
                          @RequestParam(name = "rows")Integer rows){
        return this.tinyImgService.findAll(page,rows);
    }


}
