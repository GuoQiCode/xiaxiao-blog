package com.xiaoxiao.service.backend.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.mapper.LabelMapper;
import com.xiaoxiao.pojo.XiaoxiaoLabels;
import com.xiaoxiao.service.backend.TinyLabelService;
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
 * @date:2019/11/29:11:14
 * @author:shinelon
 * @Describe:
 */
@Service
public class TinyLabelServiceImpl implements TinyLabelService
{

    @Autowired
    private LabelMapper labelMapper;

    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;

    @Value("${MARKED_WORDS_FAULT}}")
    private String MARKED_WORDS_FAULT;


    /**
     * 查询全部的标签
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllLabel(Integer page, Integer rows)
    {
        PageHelper.startPage(page, rows);
        List<XiaoxiaoLabels> allLabel =
                this.labelMapper.findAllLabel();
        if(allLabel.size() > 0 && allLabel != null){
            return Result.ok(StatusCode.OK, true, PageUtils.getResult(new PageInfo<XiaoxiaoLabels>(allLabel), page));
        }
        return Result.error(StatusCode.ERROR, "暂无数据");
    }


    @Override
    public Result update(XiaoxiaoLabels labels)
    {
        if(this.labelMapper.update(labels) > 0){
            return Result.ok(StatusCode.OK, MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }

    @Override
    public Result findLabelById(Long labelId)
    {
       XiaoxiaoLabels labels =  this.labelMapper.findLabelById(labelId);
        if (labels != null)
        {
            return Result.ok(StatusCode.OK, true,labels);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }

    @Override
    public Result delete(Long labels)
    {
        if(this.labelMapper.delete(labels) > 0){
            return Result.ok(StatusCode.OK,this.MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    @Override
    public Result insert(XiaoxiaoLabels labels)
    {
        labels.setLabelId(IDUtils.genItemId());
        if(this.labelMapper.insert(labels) > 0){
            return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }
}
