package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoSorts;
import com.xiaoxiao.pojo.vo.XiaoxiaoSortsVo;
import com.xiaoxiao.service.RedisSortsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
 * @date:2019/12/1:21:18
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_sorts_service")
@Api(value = "分类缓存服务")
@CrossOrigin(origins = "*",maxAge = 3600)
public class RedisSortsController
{

    @Autowired
    private RedisSortsService redisSortsService;


    @ApiOperation(value = "插入分类到缓存中",notes = "插入分类到缓存中")
    @PostMapping(value = "/insert_sort_all_to_redis")
    public void insertSortAllToRedis(@RequestBody List<XiaoxiaoSorts> sorts){
        this.redisSortsService.insertSortAllToRedis(sorts);
    }


    @ApiOperation(value = "获取redis中的缓存中的数据",response = XiaoxiaoSorts.class,notes = "获取redis中的缓存中的数据")
    @PostMapping(value = "/get_sorts_all_to_redis")
    public List<XiaoxiaoSorts> getSortsAllToRedis(){
        return this.redisSortsService.getSortsAllToRedis();
    }


    @PostMapping(value = "/删除redis中缓存分类列表")
    public void deleteSortsAllToRedis(){
        this.redisSortsService.deleteSortsAllToRedis();
    }




    @ApiOperation(value = "缓存首页文章分类信息展示",notes = "缓存首页文章分类信息展示")
    @PostMapping(value = "/insert_index_sorts_all_to_redis")
    public void insertIndexSortsAllToRedis(@RequestBody List<XiaoxiaoSortsVo> sortsVos){
        this.redisSortsService.insertIndexSortsAllToRedis(sortsVos);
    }



    @ApiOperation(value = "获取首页文章分类信息",response = List.class,notes = "获取首页文章分类信息")
    @PostMapping(value = "/get_index_sorts_all_to_redis")
    public List<XiaoxiaoSortsVo> getIndexSortsAllToRedis(){
        return this.redisSortsService.getIndexSortsAllToRedis();
    }



    @ApiOperation(value = "删除首页分类文章显示缓存信息",notes = "删除首页分类文章显示缓存信息")
    @PostMapping(value = "/delete_index_sorts_all_to_redis")
    public void deleteIndexSortsAllToRedis(){
        this.redisSortsService.deleteIndexSortsAllToRedis();
    }


}
