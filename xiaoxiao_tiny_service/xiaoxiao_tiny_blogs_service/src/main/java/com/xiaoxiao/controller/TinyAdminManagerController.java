package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoAdminMessage;
import com.xiaoxiao.service.backend.TinyAdminManagerService;
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
 * @date:2019/12/2:11:50
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/tiny_admin_manager_service")
public class TinyAdminManagerController
{


    @Autowired
    private TinyAdminManagerService tinyAdminManagerService;


    @ApiOperation(value = "查找全部的后台管理菜单", response = Result.class, notes = "查找全部的后台管理菜单")
    @PostMapping(value = "/find_all_manager")
    public Result findAllManager()
    {
        return this.tinyAdminManagerService.findAllManager();
    }


    @ApiOperation(value = "查询全部的后台管理信息", response = Result.class, notes = "查询全部的后台管理信息")
    @PostMapping(value = "/find_manager_all")
    public Result findAllManager(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                 @RequestParam(name = "rows", defaultValue = "10") Integer rows)
    {

        return this.tinyAdminManagerService.findAllManager(page,rows);
    }



    /**
     * 插入
     * @param message
     * @return
     */
    @ApiOperation(value = "插入一个的新的后台菜单",response = Result.class,notes = "插入一个的新的后台菜单")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody XiaoxiaoAdminMessage message){
        return this.tinyAdminManagerService.insert(message);
    }


    /**
     * 删除
     * @param adminId
     * @return
     */
    @ApiOperation(value = "删除一个后台菜单",response = Result.class,notes = "删除一个后台菜单")
    @PostMapping(value = "/delete")
    public Result deleteAdminManager(@RequestParam(name = "adminId") String adminId){
        return this.tinyAdminManagerService.deleteAdminManager(adminId);
    }

}
