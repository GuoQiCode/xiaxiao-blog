package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoSorts;
import com.xiaoxiao.service.backend.SortsService;
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
 * @project_name:xiaoxiao_blogs
 * @date:2019/11/26:16:59
 * @author:shinelon
 * @Describe:头部菜单
 */
@RestController
@RequestMapping(value = "/tiny_service_sort")
@CrossOrigin(origins = {"*"},maxAge = 3600)
@Api(value = "分类接口")
public class SortsController
{


    @Autowired
    private SortsService sortsService;


    @ApiOperation(value = "、查询首页菜单",response = Result.class,notes = "查询首页菜单")
    @GetMapping(value = "/find_all_sorts")
    public Result findAllSorts(@RequestParam(name = "page",defaultValue = "1") Integer page,
                               @RequestParam(name = "rows",defaultValue = "7") Integer rows){
        return this.sortsService.findAllSorts(page,rows);
    }





    @ApiOperation(value = "查询分类菜单",response = Result.class,notes = "查询分类菜单")
    @GetMapping(value = "/find_all")
    public Result findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "rows",defaultValue = "10") Integer rows){
        return this.sortsService.findAll(page, rows);
    }



    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除一个的分类",response = Result.class,notes = "删除一个分类")
    public Result delete(Long sortId){
        return this.sortsService.delete(sortId);
    }



    @GetMapping(value = "/find_sort_by_id")
    @ApiOperation(value = "根据ID获取信息",response = Result.class,notes = "根据ID获取信息")
    public Result findSortById(Long sortId){
        return this.sortsService.findSortById(sortId);
    }



    @PostMapping(value = "/update")
    @ApiOperation(value = "修改分类信息",response = Result.class,notes = "修改分类信息")
    public Result update(@RequestBody XiaoxiaoSorts sorts){
        return this.sortsService.update(sorts);
    }


    @ApiOperation(value = "新增分类",response = Result.class,notes = "新增分类")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody XiaoxiaoSorts sorts){
        return this.sortsService.insert(sorts);
    }

}
