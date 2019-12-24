package com.xiaoxiao.tiny.frontline.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageSerializable;
import com.xiaoxiao.pojo.XiaoxiaoNotice;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyNoticeMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyNoticeService;
import com.xiaoxiao.tiny.frontline.utils.PageUtils;
import com.xiaoxiao.utils.PageResult;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

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
 * @date:2019/12/24:18:48
 * @author:shinelon
 * @Describe:
 */
@Service
@SuppressWarnings("all")
public class FrontlineTinyNoticeServiceImpl implements FrontlineTinyNoticeService
{
    @Autowired
    private FrontlineTinyNoticeMapper frontlineTinyNoticeMapper;

    @Autowired
    private RedisCacheFeignClient client;

    @Override
    public Result findAll(Integer page, Integer rows)
    {
        try
        {
            PageResult result1 = this.client.getNotice();
            if(result1 !=null
            && result1.getResult().size() > 0){
                return Result.ok(StatusCode.OK, Result.MARKED_WORDS_SUCCESS,result1);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        PageHelper.startPage(page, rows);
        List<XiaoxiaoNotice> all = this.frontlineTinyNoticeMapper.findAll();
        if(all != null && all.size() > 0){
            PageResult result = PageUtils.getResult(new PageInfo<XiaoxiaoNotice>(all), page);
            try
            {
                this.client.insertNotice(result);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, Result.MARKED_WORDS_SUCCESS,result);
        }
        return Result.error(StatusCode.ERROR, Result.MARKED_WORDS_FAULT);
    }
}
