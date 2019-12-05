package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoUsers;
import com.xiaoxiao.service.backend.UserService;
import com.xiaoxiao.utils.MD5Utils;
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
 * @date:2019/11/26:18:51
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/tiny_user_admin")
@CrossOrigin(origins = {"*"},maxAge = 3600)
@Api(value = "用户接口")
public class UserController
{

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录",response = Result.class,notes = "用户登录")
    @PostMapping(value = "/login")
    public Result login(@RequestParam(name = "userName") String userName,
                        @RequestParam(name = "password") String password){

        return this.userService.checkUser(userName, MD5Utils.createMD5(password));
    }



    @ApiOperation(value = "修改用户的信息",response = Result.class,notes = "修改用户的信息")
    @PostMapping(value = "/update")
    public Result update(@RequestBody XiaoxiaoUsers users){
        return this.userService.update(users);
    }
}
