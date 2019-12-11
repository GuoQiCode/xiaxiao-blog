package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.feign.RedisCacheFeignClient;
import com.xiaoxiao.mapper.UserHobbyMapper;
import com.xiaoxiao.mapper.UserMapper;
import com.xiaoxiao.pojo.XiaoxiaoUserHobby;
import com.xiaoxiao.pojo.XiaoxiaoUsers;
import com.xiaoxiao.service.backend.UserService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
 * @date:2019/11/26:19:18
 * @author:shinelon
 * @Describe:
 */
@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserHobbyMapper userHobbyMapper;

    @Autowired
    private RedisCacheFeignClient client;

    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;

    @Value("${MARKED_WORDS_FAULT}}")
    private String MARKED_WORDS_FAULT;


    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Result checkUser(String userName, String password)
    {
        XiaoxiaoUsers xiaoxiaoUsers = this.userMapper.checkUser(userName, password);
        if(xiaoxiaoUsers != null){
            xiaoxiaoUsers.setUserPassword("");
            xiaoxiaoUsers.setUserIp("");

            return Result.ok(StatusCode.OK, xiaoxiaoUsers);
        }
        return Result.error(StatusCode.ERROR, "用户不存在");
    }


    /**
     * 修改用户信息
     * @param users
     * @return
     */
    @Override
    public Result update(XiaoxiaoUsers users)
    {
        try
        {
            if(this.userMapper.update(users) > 0){
                if(users.getUserHobby() != null && users.getUserHobby() != ""){
                    this.insertUserHobby(users.getUserId(),users.getUserHobby().split(","));
                }

                try
                {
                    /**
                     * 修改我的个人信息
                     */
                    this.client.updateUserToRedis(users);

                    /**
                     * 删除展示我的信息的缓存
                     */
                    this.client.deleteShowMe();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

                return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 插爱好到中间表
     */
    public void insertUserHobby(Long userId,String[] ids){
        /**
         *
         * 删除原先的爱好
         */
        try
        {
            this.userHobbyMapper.delete(userId);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        /**
         * 插入最新的爱好
         */
        for (String id:ids
             )
        {
            this.userHobbyMapper.insert(new XiaoxiaoUserHobby(userId, id));
        }
    }
}
