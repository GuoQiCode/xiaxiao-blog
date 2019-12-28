package com.xiaoxiao.contorller.backend;

import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import com.xiaoxiao.pojo.XiaoxiaoVisit;
import com.xiaoxiao.service.backend.LeaveMessageService;
import com.xiaoxiao.utils.IPUtils;
import com.xiaoxiao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
 * @date:2019/12/22:10:46
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/leave/message")
@CrossOrigin
public class LeaveMessageController
{
    @Autowired
    private LeaveMessageService leaveMessageService;

    @PostMapping(value = "/findAll")
    public Result findAll(@RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "rows",defaultValue = "10")Integer rows)
    {
        return this.leaveMessageService.findAll(page,rows);
    }




    @PostMapping(value = "/findOne")
    public Result findOne(XiaoxiaoLeaveMessage messageId){
        return this.leaveMessageService.findOne(messageId);
    }



    @PostMapping(value = "/delete")
    public Result delete(XiaoxiaoLeaveMessage messageId){
        return this.leaveMessageService.delete(messageId);
    }


    @PostMapping(value = "/update")
    public Result update(XiaoxiaoLeaveMessage xiaoxiaoVisit){
        return this.leaveMessageService.update(xiaoxiaoVisit);
    }

    @PostMapping(value = "/insert")
    public Result insert(XiaoxiaoLeaveMessage xiaoxiaoLeaveMessage, HttpServletRequest request){
        xiaoxiaoLeaveMessage.setMessageIp(IPUtils.userIp(request));
        return this.leaveMessageService.insert(xiaoxiaoLeaveMessage);
    }

}
