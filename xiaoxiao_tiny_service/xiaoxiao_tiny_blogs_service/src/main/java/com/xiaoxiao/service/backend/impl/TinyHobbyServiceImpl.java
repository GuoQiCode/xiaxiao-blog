package com.xiaoxiao.service.backend.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.mapper.HobbyMapper;
import com.xiaoxiao.pojo.XiaoxiaoHobby;
import com.xiaoxiao.pojo.vo.XiaoxiaoHobbyVo;
import com.xiaoxiao.service.backend.TinyHobbyService;
import com.xiaoxiao.utils.PageUtils;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.spi.ResolveResult;
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
 * @date:2019/12/4:12:04
 * @author:shinelon
 * @Describe:
 */
@Service
public class TinyHobbyServiceImpl implements TinyHobbyService
{


    @Autowired
    private HobbyMapper hobbyMapper;


    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;

    @Value("${MARKED_WORDS_FAULT}}")
    private String MARKED_WORDS_FAULT;


    /**
     * 查找全部
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllHobby(Integer page, Integer rows)
    {
        PageHelper.startPage(page,rows);
        List<XiaoxiaoHobby> allHobby = this.hobbyMapper.findAllHobby();
        if(allHobby != null && allHobby.size() > 0){
            return Result.ok(StatusCode.OK, true, this.MARKED_WORDS_SUCCESS, PageUtils.getResult(new PageInfo<XiaoxiaoHobby>(allHobby), page));
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 删除
     * @param hobbyId
     * @return
     */
    @Override
    public Result deleteHobbyById(String hobbyId)
    {
        if(this.hobbyMapper.deleteHobbyById(hobbyId) > 0){
            return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }

    /**
     * 查找用户的爱好
     * @param userId
     * @return
     */
    @Override
    public Result findUserHobby(Long userId)
    {
        List<XiaoxiaoHobbyVo> userHobby = this.hobbyMapper.findUserHobby(userId);
        if(userHobby != null && userHobby.size() > 0){
            return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,userHobby);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }

    /**
     * 插入
     * @param hobby
     * @return
     */
    @Override
    public Result insert(XiaoxiaoHobby hobby)
    {
        if(this.hobbyMapper.insert(hobby) > 0){
            return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 查询全部的爱好
     * @return
     */
    @Override
    public Result findAllHobby()
    {
        List<XiaoxiaoHobby> allHobby = this.hobbyMapper.findAllHobby();
        if(allHobby != null && allHobby.size() > 0){
            return Result.ok(StatusCode.OK, true,this
            .MARKED_WORDS_SUCCESS,allHobby);
        }
        return Result.error(StatusCode.OK, this.MARKED_WORDS_FAULT);
    }
}
