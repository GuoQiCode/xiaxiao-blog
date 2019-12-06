package com.xiaoxiao.tiny.frontline.controller;

import com.xiaoxiao.tiny.frontline.service.FrontlineTinyLabelService;
import com.xiaoxiao.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
 * @date:2019/12/2:20:29
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/frontline/tiny/label")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api(value = "前端标签")
public class FrontlineTinyLabelController
{

    @Autowired
    private FrontlineTinyLabelService frontlineTinyLabelService;


    @ApiOperation(value = "查询首页标签文章",response = Result.class,notes = "查询首页标签文章")
    @PostMapping(value = "/find_index_label_article")
    public Result findIndexLabelArticle(@RequestParam(name = "page",defaultValue = "1")Integer page,
                                        @RequestParam(name = "rows",defaultValue = "10")Integer rows){
        return  this.frontlineTinyLabelService.findIndexLabelArticle(page,rows);
    }


    @ApiOperation(value = "查询全部的标签",response = Result.class,notes = "查询全部的标签")
    @PostMapping(value = "/find_all_label")
    public Result findAllLabel(@RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "rows",defaultValue = "10")Integer rows){
        return this.frontlineTinyLabelService.findAllLabel(page,rows);
    }

}
