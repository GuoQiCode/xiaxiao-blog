package com.xiaoxiao.tiny.frontline.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyLeaveMessageMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyLeaveMessageService;
import com.xiaoxiao.tiny.frontline.utils.PageUtils;
import com.xiaoxiao.utils.IDUtils;
import com.xiaoxiao.utils.PageResult;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
 * @date:2019/12/19:14:57
 * @author:shinelon
 * @Describe:
 */
@Service
@SuppressWarnings("all")
public class FrontlineTinyLeaveMessageServiceImpl implements FrontlineTinyLeaveMessageService
{

    @Autowired
    private FrontlineTinyLeaveMessageMapper frontlineTinyLeaveMessageMapper;

    @Autowired
    private RedisCacheFeignClient client;


    @Override
    public Result insert(XiaoxiaoLeaveMessage leaveMessage)
    {
        leaveMessage.setMessageId(IDUtils.getUUID());
        leaveMessage.setMessageDate(new Date());
        if(this.frontlineTinyLeaveMessageMapper.insert(leaveMessage) > 0){

            try
            {
                /**
                 * 删除分页缓存
                 */
                this.client.deleteLeaveMessage();
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            return Result.ok(StatusCode.OK, Result.MARKED_WORDS_SUCCESS);
        }
        return Result.ok(StatusCode.OK, Result.MARKED_WORDS_FAULT);
    }

    @Override
    public Result findAllLeaveMessage(Integer page, Integer rows)
    {
        try
        {
            PageResult leaveMessage = this.client.getLeaveMessage(page);
            if(leaveMessage != null && leaveMessage.getResult().size()>0){
                return Result.ok(StatusCode.OK, Result.MARKED_WORDS_SUCCESS,leaveMessage);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        PageHelper.startPage(page, rows);
        List<XiaoxiaoLeaveMessage> allLeaveMessage = this.frontlineTinyLeaveMessageMapper.findAllLeaveMessage();
        if(allLeaveMessage.size() > 0 && allLeaveMessage !=null){
            PageResult result = PageUtils.getResult(new PageInfo<XiaoxiaoLeaveMessage>(allLeaveMessage), page);
            try
            {

                /**
                 * 插入缓存
                 */
                this.client.insertLeaveMessage(result,page);
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            return Result.ok(StatusCode.OK,true ,Result.MARKED_WORDS_SUCCESS,result);
        }
        return Result.error(StatusCode.ERROR, Result.MARKED_WORDS_FAULT);
    }
}
