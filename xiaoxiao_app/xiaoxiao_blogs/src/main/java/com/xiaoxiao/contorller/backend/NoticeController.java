package com.xiaoxiao.contorller.backend;

import com.xiaoxiao.pojo.XiaoxiaoNotice;
import com.xiaoxiao.service.backend.NoticeService;
import com.xiaoxiao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * _ooOoo_ o8888888o 88" . "88 (| -_- |) O\ = /O ____/`---'\____ .' \\| |// `. / \\||| : |||// \ / _||||| -:- |||||- \ |
 * | \\\ - /// | | | \_| ''\---/'' | | \ .-\__ `-` ___/-. / ___`. .' /--.--\ `. . __ ."" '< `.___\_<|>_/___.' >'"". | |
 * : `- \`.;`\ _ /`;.`/ - ` : | | \ \ `-. \_ __\ /__ _/ .-` / / ======`-.____`-.___\_____/___.-`____.-'====== `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 佛祖保佑 永无BUG 佛曰: 写字楼里写字间，写字间里程序员； 程序人员写程序，又拿程序换酒钱。 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。 但愿老死电脑间，不愿鞠躬老板前； 奔驰宝马贵者趣，公交自行程序员。 别人笑我忒疯癫，我笑自己命太贱； 不见满街漂亮妹，哪个归得程序员？
 *
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/24:17:18
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/notice")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @PostMapping(value = "/findAll")
    public Result findAll(@RequestParam(name = "page", defaultValue = "1") Integer page,
        @RequestParam(name = "rows", defaultValue = "10") Integer rows) {

        return this.noticeService.findAll(page, rows);
    }

    @PostMapping(value = "/findOne")
    public Result findOne(XiaoxiaoNotice notice) {

        return this.noticeService.findOne(notice);
    }

    @PostMapping(value = "/insert")
    public Result insert(XiaoxiaoNotice notice) {

        return this.noticeService.insert(notice);
    }

    @PostMapping(value = "/update")
    public Result update(XiaoxiaoNotice notice) {

        return this.noticeService.update(notice);
    }

    @PostMapping(value = "/delete")
    public Result delete(XiaoxiaoNotice notice) {
        return this.noticeService.delete(notice);
    }
}
