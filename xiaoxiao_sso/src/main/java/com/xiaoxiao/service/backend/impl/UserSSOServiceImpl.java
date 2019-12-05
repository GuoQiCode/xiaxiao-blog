package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.feign.RedisFeignServiceClient;
import com.xiaoxiao.feign.UserFeignServiceClient;
import com.xiaoxiao.service.UserSSOService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
 * @date:2019/11/27:10:28
 * @author:shinelon
 * @Describe:
 */
@Service
public class UserSSOServiceImpl implements UserSSOService
{


    @Autowired
    private RedisFeignServiceClient redisFeignServiceClient;

    @Autowired
    private UserFeignServiceClient userFeignServiceClient;

    @Value("${COOKIE_NAME}")
    private String COOKIE_NAME;

    @Override
    public Result login(String userName, String password, HttpServletRequest request, HttpServletResponse response,String token)
    {
        /**
         * 先去redis中获取用户用户的信息
         */
        Object userToRedis = this.redisFeignServiceClient.getUserToRedis(token);
        if(userToRedis != null){
            return Result.ok(StatusCode.OK,token,userToRedis);
        }
        /**
         * redis中没有去请求登录服务
         */
        Result login = this.userFeignServiceClient.login(userName, password);
        if(login.getData() != null){
            String token1 = UUID.randomUUID().toString();
            /**
             * 将用户存在redis中
             */
            this.redisFeignServiceClient.insertUserToRedis(login.getData(),token1);
            return Result.ok(StatusCode.OK, true,token1,login.getData());
        }
        return Result.error(StatusCode.ERROR, "失败");
    }


    /**
     * 登出
     * @param token
     */
    @Override
    public void loginOut(String token)
    {
        this.redisFeignServiceClient.loginOut(token);
    }


    /**
     * 展示我的个人信息
     * @param token
     * @return
     */
    @Override
    public Result showMe(String token)
    {
        Object userToRedis = this.redisFeignServiceClient.getUserToRedis(token);
        if(userToRedis != null){
            return Result.ok(StatusCode.OK, userToRedis);
        }
        return Result.error(StatusCode.ERROR, "获取个人信息失败");
    }
}
