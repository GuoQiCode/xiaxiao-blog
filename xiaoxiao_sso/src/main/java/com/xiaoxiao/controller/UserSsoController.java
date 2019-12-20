package com.xiaoxiao.controller;

import com.xiaoxiao.feign.RedisFeignServiceClient;
import com.xiaoxiao.feign.UserFeignServiceClient;
import com.xiaoxiao.service.UserSSOService;
import com.xiaoxiao.utils.CookieUtils;
import com.xiaoxiao.utils.Result;
import io.swagger.annotations.ApiOperation;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @date:2019/11/27:09:42
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/tiny_user")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserSsoController
{


    @Autowired
    private UserSSOService userSSOService;


    @PostMapping(value = "/login")
    @ApiOperation(value = "登录",response = Result.class,notes = "登录")
    public Result login(@RequestParam(name = "userName") String userName,
                        @RequestParam(name = "password") String password, HttpServletRequest request, HttpServletResponse response,
                        @RequestParam(name = "token",required = false)String token){

        return  this.userSSOService.login(userName, password,request,response,token);
    }



    @GetMapping(value = "/login_out")
    @ApiOperation(value = "/登出",notes = "登出")
    public void loginOut(@RequestParam(name = "token") String token){
        this.userSSOService.loginOut(token);
    }


    @GetMapping(value = "/show_me")
    public Result showMe(String token){
        return this.userSSOService.showMe(token);
    }

}
