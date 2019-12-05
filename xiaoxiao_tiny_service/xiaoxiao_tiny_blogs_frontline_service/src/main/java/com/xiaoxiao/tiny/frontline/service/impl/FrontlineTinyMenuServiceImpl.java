package com.xiaoxiao.tiny.frontline.service.impl;

import com.xiaoxiao.pojo.XiaoxiaoMenu;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyMenuMapper;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyUserMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyMenuService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
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
 * @date:2019/12/2:16:11
 * @author:shinelon
 * @Describe:
 */
@Service
public class FrontlineTinyMenuServiceImpl implements FrontlineTinyMenuService
{

    @Autowired
    private RedisCacheFeignClient redisCacheFeignClient;

    @Autowired
    private FrontlineTinyMenuMapper frontlineTinyMenuMapper;


    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;


    @Value("${MARKED_WORDS_FAULT}")
    private String MARKED_WORDS_FAULT;


    /**
     * 查詢首页菜单
     * @return
     */
    @Override
    public Result findAllMenu()
    {
        /**
         * 查询redis
         */
        List<XiaoxiaoMenu> menus = null;
        try
        {
            menus = this.redisCacheFeignClient.getMenuToRedis();
            if (menus != null && menus.size() > 0)
            {
                return Result.ok(StatusCode.OK, true, this.MARKED_WORDS_SUCCESS, menus);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        /**
         * 获取数据库中的数据
         */
        menus = this.frontlineTinyMenuMapper.findAll();
        /**
         * 插入redis
         */
        if(menus != null && menus.size() > 0){
            try
            {
                this.redisCacheFeignClient.insertMenuToRedis(menus);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, true, this.MARKED_WORDS_SUCCESS, menus);
        }

        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }





}
