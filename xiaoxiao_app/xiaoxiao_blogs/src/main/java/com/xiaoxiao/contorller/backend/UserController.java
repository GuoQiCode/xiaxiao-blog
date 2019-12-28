package com.xiaoxiao.contorller.backend;

import com.xiaoxiao.feign.UserSSOFeignClient;
import com.xiaoxiao.pojo.XiaoxiaoUsers;
import com.xiaoxiao.service.backend.UserFeignService;
import com.xiaoxiao.utils.Cookie;
import com.xiaoxiao.utils.CookieUtils;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
@Controller
@RequestMapping(value = "/admin")
@CrossOrigin
public class UserController
{
    @Autowired
    private UserSSOFeignClient userSSOFeignClient;

    @Autowired
    private UserFeignService userFeignService;

    @Value("${COOKIE_NAME}")
    private String COOKIE_NAME;

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @param response
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    public String login(@RequestParam(name = "userName") String userName,
                        @RequestParam(name = "password") String password,
                        HttpServletResponse response,
                        HttpServletRequest request) throws Exception
    {
        try
        {
            /**
             * 获取Cookie
             */
            String token = CookieUtils.getCookieValue(request, COOKIE_NAME, "utf-8");
            Result login = this.userSSOFeignClient.login(userName, password, token);
            if (login.getData() != null && login.getCode() == StatusCode.OK)
            {
                if (StringUtils.isEmpty(token) || !token.equals(login.getMessage()))
                {
                    Cookie.setCookie(request, response, COOKIE_NAME, login.getMessage(), 259200, true);
                    /*CookieUtils.setCookie(request, response, COOKIE_NAME, login.getMessage(), 1800, "utf-8");*/
                }
                return "redirect:/admin/blogs";
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/login";
    }


    /**
     * 登出
     *
     * @param request
     * @param response
     */
    @GetMapping(value = "/login_out")
    public String loginOut(HttpServletRequest request, HttpServletResponse response)
    {
        String token = CookieUtils.getCookieValue(request, COOKIE_NAME, "utf-8");
        CookieUtils.deleteCookie(request, response, this.COOKIE_NAME);
        this.userSSOFeignClient.loginOut(token);
        return "redirect:/";
    }


    /**
     * 展示我的个人信息
     *
     * @return
     */
    @GetMapping(value = "/show_me")
    @ResponseBody
    public Result showMe(HttpServletRequest request)
    {
        String token = CookieUtils.getCookieValue(request, COOKIE_NAME, "utf-8");
        return this.userSSOFeignClient.showMe(token);
    }


    /**
     * 修改用户个人信息
     * @return
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public Result update(XiaoxiaoUsers users){
        return this.userFeignService.update(users);
    }


    /**
     * 修改密码
     * @return
     */
    @PostMapping(value = "/updatePassword")
    @ResponseBody
    public Result updatePassword(XiaoxiaoUsers users,HttpServletRequest request){
        String token = CookieUtils.getCookieValue(request, COOKIE_NAME, "utf-8");
        return this.userFeignService.updatePassword(users,token);
    }

}
