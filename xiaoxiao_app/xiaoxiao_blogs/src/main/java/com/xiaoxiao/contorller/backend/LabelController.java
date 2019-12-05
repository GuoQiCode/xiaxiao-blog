package com.xiaoxiao.contorller.backend;

import com.xiaoxiao.pojo.XiaoxiaoLabels;
import com.xiaoxiao.service.backend.LabelFeignService;
import com.xiaoxiao.utils.Result;
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
 * @date:2019/11/29:11:03
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/label")
@CrossOrigin
public class LabelController
{




    @Autowired
    private LabelFeignService labelFeignService;

    /**
     * 查询全部的标签
     * @param page
     * @param rows
     * @return
     */
    @GetMapping(value = "/find_all_label")
    public Result findAllLabel(@RequestParam(name = "page",defaultValue = "1",required = false) Integer page,
                               @RequestParam(name = "rows",defaultValue = "10",required = false) Integer rows){
        return this.labelFeignService.findAllLabel(page, rows);
    }


    @PostMapping(value = "/update")
    public Result update(XiaoxiaoLabels labels){
        return this.labelFeignService.update(labels);
    }


    @GetMapping(value = "/find_label_by_id")
    public Result findLabelById(Long labelId){
        return this.labelFeignService.findLabelById(labelId);
    }


    @GetMapping(value = "/delete")
    public Result delete(Long labelId){
        return this.labelFeignService.delete(labelId);
    }


    @PostMapping(value = "/insert")
    public Result insert(XiaoxiaoLabels labels){
        return this.labelFeignService.insert(labels);
    }

}
