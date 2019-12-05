package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.feign.RedisCacheFeignClient;
import com.xiaoxiao.mapper.MenuMapper;
import com.xiaoxiao.pojo.XiaoxiaoMenu;
import com.xiaoxiao.service.backend.MenuService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
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
 * @project_name:xiaoxiao_blogs
 * @date:2019/11/26:17:18
 * @author:shinelon
 * @Describe:
 */
@Service
public class MenuServiceImpl implements MenuService
{
    @Autowired
    private MenuMapper menuMapper;


    @Autowired
    private RedisCacheFeignClient client;

    /**
     * 查询全部的数据
     * @return
     */
    @Override
    public Result findAllMenu()
    {

        /**
         * 先查询缓存
         */
        List<XiaoxiaoMenu> allMenu = null;
        try
        {
            List<XiaoxiaoMenu> menuToRedis = this.client.getMenuToRedis();
            if(menuToRedis != null){
                return Result.ok(StatusCode.OK, true,menuToRedis);
            }
            allMenu = this.menuMapper.findAllMenu();
            /**
             * 如果没有缓存则插入缓存数据
             */
              this.client.insertMenuToRedis(allMenu);


            if(allMenu != null && allMenu.size() > 0){

                return Result.ok(StatusCode.OK, true, allMenu);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Result.error(StatusCode.ERROR, "暂无数据");
    }
}
