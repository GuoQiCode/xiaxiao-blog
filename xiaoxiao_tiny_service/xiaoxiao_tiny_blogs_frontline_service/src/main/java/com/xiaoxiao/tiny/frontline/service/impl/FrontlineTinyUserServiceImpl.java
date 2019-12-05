package com.xiaoxiao.tiny.frontline.service.impl;

import com.xiaoxiao.pojo.XiaoxiaoUsers;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyUserMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyUserService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.mappers.LicenseMapper;

import java.awt.font.TextHitInfo;

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
 * @date:2019/12/5:13:19
 * @author:shinelon
 * @Describe:
 */
@Service
public class FrontlineTinyUserServiceImpl implements FrontlineTinyUserService
{

    @Autowired
    private FrontlineTinyUserMapper frontlineTinyUserMapper;

    @Autowired
    private RedisCacheFeignClient clientl;


    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;


    @Value("${MARKED_WORDS_FAULT}")
    private String MARKED_WORDS_FAULT;

    /**
     *
     * 获取我的个人信息
     * @return
     */
    @Override
    public Result showMe()
    {
        try
        {
            XiaoxiaoUsers showMeToRedis = this.clientl.getShowMeToRedis();
            if(showMeToRedis != null){
                return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,showMeToRedis);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        XiaoxiaoUsers users = this.frontlineTinyUserMapper.showMe();
        if(users != null){
            try
            {
                this.clientl.insertShowMeToRedis(users);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,users);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }
}
