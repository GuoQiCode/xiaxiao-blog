package com.xiaoxiao.tiny.frontline.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiaoxiao.pojo.vo.XiaoxiaoSortsVo;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinySortsMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinySortsService;
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
 * @date:2019/12/2:17:01
 * @author:shinelon
 * @Describe:
 */
@Service
public class FrontlineTinySortsServiceImpl implements FrontlineTinySortsService
{


    @Autowired
    private FrontlineTinySortsMapper frontlineTinySortsMapper;

    @Autowired
    private RedisCacheFeignClient client;


    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;


    @Value("${MARKED_WORDS_FAULT}")
    private String MARKED_WORDS_FAULT;

    /**
     * 查询全部的文章信息
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllSorts(Integer page, Integer rows)
    {
        List<XiaoxiaoSortsVo> sorts = null;

        /**
         * 查询缓存
         */
        try
        {
            sorts = this.client.getIndexSortsAllToRedis();
            if(sorts != null && sorts .size() > 0){
                return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,sorts);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        PageHelper.startPage(page, rows);
        sorts = this.frontlineTinySortsMapper.findAllSorts();

        /**
         * 缓存插入
         */
        if(sorts != null && sorts.size() > 0){
            try
            {
                this.client.insertIndexSortsAllToRedis(sorts);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,sorts);
        }

        return Result.error(StatusCode.OK, this.MARKED_WORDS_FAULT);
    }
}
