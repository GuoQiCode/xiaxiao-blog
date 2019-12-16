package com.xiaoxiao.controller;

import com.xiaoxiao.service.RedisTechniqueSharingService;
import com.xiaoxiao.utils.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @date:2019/12/16:10:15
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_technique_sharing")
public class RedisTechniqueSharingController
{

    @Autowired
    private RedisTechniqueSharingService redisTechniqueSharingService;


    @ApiOperation(value = "缓存首页推荐文章数据",notes = "缓存首页推荐文章数据")
    @PostMapping(value = "/insertTechniqueSharingArticle")
    public void insertTechniqueSharingArticle(@RequestBody PageResult result){
        this.redisTechniqueSharingService.insertTechniqueSharingArticle(result);
    }


    @ApiOperation(value = "获取缓存首页推荐文章数据",notes = "获取缓存首页推荐文章数据")
    @PostMapping(value = "/getTechniqueSharingArticle")
    public PageResult getTechniqueSharingArticle(){
        return  this.redisTechniqueSharingService.getTechniqueSharingArticle();
    }


    @ApiOperation(value = "删除缓存首页推荐文章数据",notes = "删除缓存首页推荐文章数据")
    @PostMapping(value = "/deleteTechniqueSharingArticle")
    public void deleteTechniqueSharingArticle(){
        this.redisTechniqueSharingService.deleteTechniqueSharingArticle();
    }
}
