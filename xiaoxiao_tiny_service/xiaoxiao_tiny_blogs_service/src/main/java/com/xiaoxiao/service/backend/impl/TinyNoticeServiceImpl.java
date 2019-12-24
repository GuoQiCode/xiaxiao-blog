package com.xiaoxiao.service.backend.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.mapper.NoticeMapper;
import com.xiaoxiao.pojo.XiaoxiaoNotice;
import com.xiaoxiao.service.backend.TinyNoticeService;
import com.xiaoxiao.utils.IDUtils;
import com.xiaoxiao.utils.PageUtils;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
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
 * @date:2019/12/24:16:46
 * @author:shinelon
 * @Describe:
 */
@Service
@SuppressWarnings("all")
public class TinyNoticeServiceImpl implements TinyNoticeService
{

    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public Result findAll(Integer page, Integer rows)
    {
        PageHelper.startPage(page, rows);
        List<XiaoxiaoNotice> all = this.noticeMapper.findAll();
        if(all != null && all.size() > 0){
            return Result.ok(StatusCode.OK, true, Result.MARKED_WORDS_SUCCESS, PageUtils.getResult(new PageInfo<XiaoxiaoNotice>(all), page));
        }
        return Result.ok(StatusCode.OK, Result.MARKED_WORDS_FAULT);
    }

    @Override
    public Result findOne(XiaoxiaoNotice notice)
    {
        XiaoxiaoNotice one = this.noticeMapper.findOne(notice);
        if(one != null ){
            return Result.ok(StatusCode.OK, true,Result.MARKED_WORDS_SUCCESS,one);
        }
        return Result.ok(StatusCode.OK, Result.MARKED_WORDS_FAULT);
    }

    @Override
    public Result insert(XiaoxiaoNotice notice)
    {
        notice.setId(IDUtils.getUUID());
        notice.setDate(new Date());
        if(this.noticeMapper.insert(notice) > 0){
            return Result.ok(StatusCode.OK,Result.MARKED_WORDS_SUCCESS);
        }
        return Result.ok(StatusCode.OK, Result.MARKED_WORDS_FAULT);
    }

    @Override
    public Result update(XiaoxiaoNotice notice)
    {
        if(this.noticeMapper.update(notice) > 0){
            return Result.ok(StatusCode.OK,Result.MARKED_WORDS_SUCCESS);
        }
        return Result.ok(StatusCode.OK, Result.MARKED_WORDS_FAULT);
    }

    @Override
    public Result delete(XiaoxiaoNotice notice)
    {
        if(this.noticeMapper.delete(notice) > 0){
            return Result.ok(StatusCode.OK,Result.MARKED_WORDS_SUCCESS);
        }
        return Result.ok(StatusCode.OK, Result.MARKED_WORDS_FAULT);
    }
}
