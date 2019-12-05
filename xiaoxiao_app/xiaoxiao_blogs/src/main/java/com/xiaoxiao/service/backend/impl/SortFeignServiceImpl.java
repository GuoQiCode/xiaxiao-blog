package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.feign.BlogsFeignServiceClient;
import com.xiaoxiao.pojo.XiaoxiaoSorts;
import com.xiaoxiao.service.backend.SortFeignService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/11/27:09:07
 * @author:shinelon
 * @Describe:
 */
@Service
public class SortFeignServiceImpl implements SortFeignService
{

    @Autowired
    private BlogsFeignServiceClient blogsFeignServiceClient;


    /**
     * 首页展示分类
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllSorts(Integer page, Integer rows)
    {
        return blogsFeignServiceClient.findAllSorts(page, rows);
    }


    /**
     * 后台分页展示
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAll(Integer page, Integer rows)
    {
        return this.blogsFeignServiceClient.findAll(page, rows);
    }


    /**
     * 删除一个分类
     *
     * @param sortsId
     * @return
     */
    @Override
    public Result delete(Long sortsId)
    {
        return this.blogsFeignServiceClient.delete(sortsId);
    }


    /**
     * 根据ID查询
     *
     * @param sortId
     * @return
     */
    @Override
    public Result findSortById(Long sortId)
    {
        return this.blogsFeignServiceClient.findSortById(sortId);
    }


    /**
     * 修改
     *
     * @param sorts
     * @return
     */
    @Override
    public Result update(XiaoxiaoSorts sorts)
    {
        Result update = this.blogsFeignServiceClient.update(sorts);
        if (update.getCode() == StatusCode.OK)
        {
            return update;
        }
        return Result.error(StatusCode.ERROR, "操作失败");
    }


    /**
     * 新增分类
     *
     * @param sorts
     * @return
     */
    @Override
    public Result insert(XiaoxiaoSorts sorts)
    {
        Result result = this.blogsFeignServiceClient.insert(sorts);
        if(result.getCode() != StatusCode.ERROR){
            return result;
        }

        return Result.error(StatusCode.ERROR, "操作失败");
    }
}
