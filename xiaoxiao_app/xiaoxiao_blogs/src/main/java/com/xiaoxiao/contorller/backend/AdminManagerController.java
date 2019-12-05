package com.xiaoxiao.contorller.backend;

import com.xiaoxiao.pojo.XiaoxiaoAdminMessage;
import com.xiaoxiao.service.backend.AdminManagerService;
import com.xiaoxiao.utils.Result;
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
 * @date:2019/12/2:11:43
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/message")
public class AdminManagerController
{


    @Autowired
    private AdminManagerService adminMessageService;


    /**
     * 查询全部的菜单
     * @return
     */
    @PostMapping(value = "/find_all_manager")
    public Result findAllManager()
    {
        return this.adminMessageService.findAllManager();
    }


    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/find_manager_all")
    public Result findAllManager(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "rows", defaultValue = "10") Integer rows)
    {
        return this.adminMessageService.findAllManager(page, rows);
    }


    @PostMapping(value = "/insert")
    public Result insert(XiaoxiaoAdminMessage message){
        return this.adminMessageService.insert(message);
    }


    @PostMapping(value = "/delete")
    public Result deleteAdminManager(String adminId){
        return this.adminMessageService.deleteAdminManager(adminId);
    }


    @PostMapping(value = "/find_admin_manager_by_id")
    public Result findAdminManagerById(Long adminId){
        return this.adminMessageService.findAdminManagerById(adminId);
    }




}
