package com.xiaoxiao.tiny.frontline.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.pojo.vo.XiaoxiaoLabelVo;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyLabelMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyLabelService;
import com.xiaoxiao.tiny.frontline.utils.PageUtils;
import com.xiaoxiao.utils.PageResult;
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
 * @date:2019/12/2:20:53
 * @author:shinelon
 * @Describe:
 */
@Service
public class FrontlineTinyLabelServiceImpl implements FrontlineTinyLabelService
{


    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;


    @Value("${MARKED_WORDS_FAULT}")
    private String MARKED_WORDS_FAULT;


    @Autowired
    private RedisCacheFeignClient client;

    @Autowired
    private FrontlineTinyLabelMapper frontlineTinyLabelMapper;




    /**
     * 查询首页标签文章数据
     * @return
     * @param page
     * @param rows
     */
    @Override
    public Result findIndexLabelArticle(Integer page, Integer rows)
    {
        PageResult result = null;
        try
        {
            result = this.client.getIndexArticleLabel();

            if(result.getResult() != null && result.getResult().size() > 0){
                return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS,result);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        PageHelper.startPage(page, rows);
        List<XiaoxiaoLabelVo> labelVos = this.frontlineTinyLabelMapper.findIndexLabelArticle();
        PageResult result1 = PageUtils.getResult(new PageInfo<XiaoxiaoLabelVo>(labelVos), page);
        if(labelVos != null && labelVos.size() > 0){
            try
            {
                this.client.insertIndexArticleLabel(result1);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, true, this.MARKED_WORDS_SUCCESS,result1);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }
}
