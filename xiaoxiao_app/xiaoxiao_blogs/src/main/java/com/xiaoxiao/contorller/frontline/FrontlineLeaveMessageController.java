package com.xiaoxiao.contorller.frontline;

import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import com.xiaoxiao.service.frontline.FrontlineLeaveMessageService;
import com.xiaoxiao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
 * @date:2019/12/19:10:25
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/frontline/leave/message")
public class FrontlineLeaveMessageController
{
    @Autowired
    private FrontlineLeaveMessageService frontlineLeaveMessageService;


    /**
     * 获取留言总数
     * @return
     */
    @PostMapping(value = "/getLeaveMessageSum")
    public Result getLeaveMessageSum(){
        return this.frontlineLeaveMessageService.getLeaveMessageSum();
    }


    /**
     * 获取访问总数
     * @return
     */
    @PostMapping(value = "/getVisitSum")
    public Result getVisitSum(){
        return this.frontlineLeaveMessageService.getVisitSum();
    }


    /**
     * 获取今日访问总数
     * @return
     */
    @PostMapping(value = "/getTodayVisitSum")
    public Result getTodayVisitSum(){
        return this.frontlineLeaveMessageService.getTodayVisitSum();
    }


    /**
     * 提交留言
     * @return
     */
    @PostMapping(value = "/insert")
    public Result insert(XiaoxiaoLeaveMessage leaveMessage, HttpServletRequest request){
        return this.frontlineLeaveMessageService.insert(leaveMessage,request);
    }


    /**
     * 查询分页的留言
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/findAllLeaveMessage")
    public Result findAllLeaveMessage(@RequestParam(name = "page",defaultValue = "1")Integer page,
                                      @RequestParam(name = "rows",defaultValue = "10")Integer rows){
        return this.frontlineLeaveMessageService.findAllLeaveMessage(page,rows);
    }

}
