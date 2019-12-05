package com.xiaoxiao.tiny.frontline.service.impl;

import com.xiaoxiao.pojo.XiaoxiaoHobby;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyHobbyMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyHobbyService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import net.bytebuddy.asm.Advice;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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
 * @date:2019/12/5:17:58
 * @author:shinelon
 * @Describe:
 */
@Service
public class FrontlineTinyHobbyServiceImpl implements FrontlineTinyHobbyService
{

    @Autowired
    private FrontlineTinyHobbyMapper frontlineTinyHobbyMapper;

    @Autowired
    private RedisCacheFeignClient client;

    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;


    @Value("${MARKED_WORDS_FAULT}")
    private String MARKED_WORDS_FAULT;

    /**
     * 获取我的爱好
     * @return
     */
    @Override
    public Result findMeHobby()
    {
        List<XiaoxiaoHobby> hobbyToRedis = this.client.getHobbyToRedis();
        if(hobbyToRedis != null && hobbyToRedis.size() > 0){
            return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,hobbyToRedis);
        }

        List<XiaoxiaoHobby> meHobby = this.frontlineTinyHobbyMapper.findMeHobby();

        if(meHobby != null && meHobby.size() > 0){
            try
            {
                this.client.insertHobbyToRedis(meHobby);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.ERROR, true,this.MARKED_WORDS_SUCCESS,meHobby);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }
}
