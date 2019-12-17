package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoAdvertising;
import com.xiaoxiao.service.backend.AdvertisingService;
import com.xiaoxiao.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
 * @date:2019/12/16:18:41
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/backend_advertising")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api(value = "广告管理")
public class AdvertisingController
{

    @Autowired
    private AdvertisingService advertisingService;

    /**
     *查询全部的广告数据
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/findAllAdvertising")
   public Result findAllAdvertising(@RequestParam(name = "page",defaultValue = "1") Integer page,
                              @RequestParam(name = "rows",defaultValue = "10") Integer rows){
        return this.advertisingService.findAllAdvertising(page,rows);
    }

    /**
     * 删除广告数据
     * @param advertisingId
     * @return
     */
    @PostMapping(value = "/deleteAdvertising")
    public Result deleteAdvertising(@RequestParam(name = "advertisingId") String advertisingId){
        return this.advertisingService.deleteAdvertising(advertisingId);
    }

    /**
     * 根据ID获取
     * @param advertisingId
     * @return
     */
    @PostMapping(value = "/findAdvertisingById")
    public Result findAdvertisingById(@RequestParam(name = "advertisingId") String advertisingId){
        return this.advertisingService.findAdvertisingById(advertisingId);
    }


    /**
     * 插入
     * @param advertising
     * @return
     */
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody XiaoxiaoAdvertising advertising){
        return this.advertisingService.insert(advertising);
    }


    /**
     * 修改
     * @param advertising
     * @return
     */
    @PostMapping(value = "/update")
    public Result update(@RequestBody XiaoxiaoAdvertising advertising){
        return this.advertisingService.update(advertising);
    }

    @ApiOperation(value = "获取首页的广告位值",response = Result.class,notes = "获取首页的广告位值")
    @PostMapping(value = "/onto")
    public Result onto(@RequestParam(name = "advertisingId") String advertisingId){
        return this.advertisingService.onto(advertisingId);
    }

}
