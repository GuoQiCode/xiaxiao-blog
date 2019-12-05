package com.xiaoxiao.feign;

import com.xiaoxiao.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
 * @date:2019/11/27:10:50
 * @author:shinelon
 * @Describe:
 */
@FeignClient("xiaoxiao-sso")
public interface UserSSOFeignClient
{

    /**
     * 用户登录
     * @param userName
     * @param password
     * @param token
     * @return
     */
    @PostMapping(value = "/tiny_user/login")
    Result login(@RequestParam(name = "userName") String userName,
                 @RequestParam(name = "password") String password,
                 @RequestParam(name = "token",required = false) String token);


    @GetMapping(value = "/tiny_user/login_out")
    void loginOut(@RequestParam(name = "token") String token);


    /**
     * 展示个人信息
     * @param token
     * @return
     */
    @GetMapping(value = "/tiny_user/show_me")
    Result showMe(@RequestParam(name = "token") String token);
}
