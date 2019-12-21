package com.xiaoxiao.tiny.frontline.service.impl;

import com.xiaoxiao.pojo.XiaoxiaoVisit;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyVisitMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyVisitService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date:2019/12/21:14:41
 * @author:shinelon
 * @Describe:
 */
@Service
@SuppressWarnings("all")
public class FrontlineTinyVisitServiceImpl implements FrontlineTinyVisitService
{


    @Autowired
    private FrontlineTinyVisitMapper frontlineTinyVisitMapper;

    @Autowired
    private RedisCacheFeignClient client;




    @Override
    public Result getTodaySum()
    {
        return null;
    }

    @Override
    public Result frontlineTinyVisitService()
    {
        return null;
    }

    @Override
    public Result getVisitMessages()
    {
        try
        {
            XiaoxiaoVisit visitToRedis = this.client.getVisitToRedis();
            if(visitToRedis != null){
                return Result.ok(StatusCode.OK, true,Result.MARKED_WORDS_SUCCESS,visitToRedis);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        List<XiaoxiaoVisit> visitMessages = this.frontlineTinyVisitMapper.getVisitMessages();
        if(visitMessages.size() == 1 && visitMessages != null){
            try
            {
                this.client.insertVisitToRedis(visitMessages.get(0));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, true,Result.MARKED_WORDS_SUCCESS,visitMessages.get(0));
        }
        return Result.error(StatusCode.ERROR, Result.MARKED_WORDS_FAULT);
    }
}
