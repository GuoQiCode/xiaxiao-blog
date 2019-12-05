package com.xiaoxiao.service.backend.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.feign.RedisCacheFeignClient;
import com.xiaoxiao.mapper.AdminManagerMapper;
import com.xiaoxiao.pojo.XiaoxiaoAdminMessage;
import com.xiaoxiao.service.backend.TinyAdminManagerService;
import com.xiaoxiao.utils.IDUtils;
import com.xiaoxiao.utils.PageUtils;
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
 * @date:2019/12/2:11:53
 * @author:shinelon
 * @Describe:
 */
@Service
public class TinyAdminManagerServiceImpl implements TinyAdminManagerService
{

    @Autowired
    private AdminManagerMapper adminManagerMapper;


    @Autowired
    private RedisCacheFeignClient client;


    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;

    @Value("${MARKED_WORDS_FAULT}")
    private String MARKED_WORDS_FAULT;


    @Override
    public Result findAllManager()
    {

        /**
         *
         */
        List<XiaoxiaoAdminMessage> messages = null;
        try
        {
            /**
             * 获取缓存
             */
            messages =  this.client.getAdminManagerToRedis();
            if(messages != null && messages.size() > 0){
                return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,messages);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        /**
         * 获取数据库
         */
        messages = this.adminManagerMapper.findAll();

        /**
         * 插入缓存
         */
        try
        {
            this.client.insertAdminManagerToRedis(messages);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        if(messages != null && messages.size() > 0){
            return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,messages);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllManager(Integer page, Integer rows)
    {
        PageHelper.startPage(page, rows);
        List<XiaoxiaoAdminMessage> managerMapperAll = this.adminManagerMapper.findAll();
        if(managerMapperAll != null && managerMapperAll.size() > 0){
            return Result.ok(StatusCode.OK, true, this.MARKED_WORDS_SUCCESS, PageUtils.getResult(new PageInfo<XiaoxiaoAdminMessage>(managerMapperAll),page));
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 新增
     * @param adminId
     * @return
     */
    @Override
    public Result deleteAdminManager(String adminId)
    {
        if(this.adminManagerMapper.delete(adminId) > 0){
            try
            {
                this.client.deleteAdminManager();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }

    /**
     *刪除
     * @param message
     * @return
     */
    @Override
    public Result insert(XiaoxiaoAdminMessage message)
    {
        message.setAdminId(IDUtils.getUUID());
        if(this.adminManagerMapper.insert(message)> 0){
            try
            {
                this.client.deleteAdminManager();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }
}
