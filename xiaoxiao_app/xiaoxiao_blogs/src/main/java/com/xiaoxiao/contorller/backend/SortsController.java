package com.xiaoxiao.contorller.backend;

import com.xiaoxiao.pojo.XiaoxiaoSorts;
import com.xiaoxiao.service.backend.SortFeignService;
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
 * @project_name:xiaoxiao_blogs
 * @date:2019/11/26:16:59
 * @author:shinelon
 * @Describe:头部菜单
 */
@RestController
@RequestMapping(value = "/admin/sort")
@CrossOrigin
public class SortsController
{

    @Autowired
    private SortFeignService sortFeignService;

    @GetMapping(value = "/find_all_sorts")
    public Result findAllSorts(@RequestParam(name = "page",defaultValue = "1") Integer page,
                               @RequestParam(name = "rows",defaultValue = "10") Integer rows){
        return this.sortFeignService.findAllSorts(page,rows);
    }


    @GetMapping(value = "/find_all")
    public Result findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "rows",defaultValue = "10") Integer rows){
        return this.sortFeignService.findAll(page,rows);
    }



    @GetMapping(value = "/delete")
    public Result delete(Long sortId){
        return this.sortFeignService.delete(sortId);
    }


    @GetMapping(value = "/find_sort_by_id")
    public Result findSortById(Long sortId){
        return this.sortFeignService.findSortById(sortId);
    }


    @PostMapping(value = "/update")
    public Result update(XiaoxiaoSorts sorts){
        return this.sortFeignService.update(sorts);
    }

    /**
     * 新增
     * @param sorts
     * @return
     */
    @PostMapping(value = "/insert")
    public Result insert(XiaoxiaoSorts sorts){
        return this.sortFeignService.insert(sorts);
    }
}
