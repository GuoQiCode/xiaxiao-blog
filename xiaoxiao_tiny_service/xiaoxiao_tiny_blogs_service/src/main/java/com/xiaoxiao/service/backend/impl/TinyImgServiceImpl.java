package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.mapper.ImgMapper;
import com.xiaoxiao.pojo.XiaoxiaoImg;
import com.xiaoxiao.service.backend.TinyImgService;
import com.xiaoxiao.utils.IDUtils;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * @date:2019/12/1:17:58
 * @author:shinelon
 * @Describe:
 */
@Service
public class TinyImgServiceImpl implements TinyImgService
{


    @Autowired
    private ImgMapper imgMapper;

    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;

    @Value("${MARKED_WORDS_FAULT}")
    private String MARKED_WORDS_FAULT;




    /**
     * 插入
     * @param img
     * @return
     */
    @Override
    public Result insert(XiaoxiaoImg img)
    {
        img.setImgId(IDUtils.getUUID());
        if (imgMapper.insert(img) > 0)
        {
            return Result.ok(StatusCode.OK, MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 修改
     * @param img
     * @return
     */
    @Override
    public Result update(XiaoxiaoImg img)
    {
        return null;
    }


    /**
     * 删除
     * @param imgId
     * @return
     */
    @Override
    public Result delete(String imgId)
    {
        return null;
    }


    /**
     * 查找一个的
     * @param imgId
     * @return
     */
    @Override
    public Result findImgById(String imgId)
    {
        return null;
    }


    /**
     * 查找全部
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAll(Integer page, Integer rows)
    {
        return null;
    }
}
