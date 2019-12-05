package com.xiaoxiao.service.backend.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.mapper.SortsMapper;
import com.xiaoxiao.pojo.XiaoxiaoSorts;
import com.xiaoxiao.pojo.vo.XiaoxiaoSortsVo;
import com.xiaoxiao.service.backend.SortsService;
import com.xiaoxiao.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @project_name:xiaoxiao_blogs
 * @date:2019/11/26:17:02
 * @author:shinelon
 * @Describe:
 */
@Service
public class SortsServiceImpl implements SortsService
{

    @Autowired
    private SortsMapper sortsMapper;

    @Override
    public Result findAllSorts(Integer page, Integer rows)
    {
        PageHelper.startPage(page, rows);
        List<XiaoxiaoSortsVo> allSorts = this.sortsMapper.findAllSorts();
        PageInfo<XiaoxiaoSortsVo> info = new PageInfo<>(allSorts);
        if (info.getList() != null && info.getList().size() > 0)
        {
            return Result.ok(StatusCode.OK, true, PageUtils.getResult(info, page));
        }
        return Result.error(StatusCode.ERROR, "暂无数据");
    }


    /**
     * 分页展示
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAll(Integer page, Integer rows)
    {
        PageHelper.startPage(page, rows);
        List<XiaoxiaoSorts> all = this.sortsMapper.findAll();
        PageInfo<XiaoxiaoSorts> info = new PageInfo<>(all);
        if (info.getList() != null && info.getList().size() > 0)
        {
            return Result.ok(StatusCode.OK, true, PageUtils.getResult(info, page));
        }
        return Result.error(StatusCode.ERROR, "暂无数据");
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
        if (this.sortsMapper.delete(sortsId) > 0)
        {
            return Result.ok(StatusCode.OK, "操作成功");
        }
        return Result.error(StatusCode.ERROR, "请求失败");
    }


    /**
     * 根据ID获取分类
     *
     * @param sortId
     * @return
     */
    @Override
    public Result findSortById(Long sortId)
    {
        XiaoxiaoSorts sortById = this.sortsMapper.findSortById(sortId);
        if (sortById != null)
        {
            return Result.ok(StatusCode.OK, sortById);
        }
        return Result.error(StatusCode.ERROR, "请求失败");
    }


    /**
     * 修改信息
     *
     * @param sorts
     * @return
     */
    @Override
    public Result update(XiaoxiaoSorts sorts)
    {
        if (this.sortsMapper.update(sorts) > 0)
        {
            return Result.ok(StatusCode.OK, "操作成功");
        }
        return Result.error(StatusCode.ERROR, "操作失败");
    }


    /**
     * 新增分类信息
     * @param sorts
     * @return
     */
    @Override
    public Result insert(XiaoxiaoSorts sorts)
    {
        sorts.setSortId(IDUtils.genItemId());
        if(this.sortsMapper.insert(sorts) > 0){
            return Result.ok(StatusCode.OK, "操作成功");
        }
        return Result.error(StatusCode.ERROR, "操作失败");
    }
}
