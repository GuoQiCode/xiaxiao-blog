package com.xiaoxiao.contorller.backend;

import com.xiaoxiao.pojo.XiaoxiaoHobby;
import com.xiaoxiao.service.backend.HobbyFeignService;
import com.xiaoxiao.utils.CookieUtils;
import com.xiaoxiao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * @date:2019/12/4:11:29
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/hobby")
@CrossOrigin
public class HobbyController
{

    @Autowired
    private HobbyFeignService hobbyFeignService;


    @Value("${COOKIE_NAME}")
    private String COOKIE_NAME;

    /**
     * 获取全部的爱好
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/find_all")
    public Result findAll(@RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "rows",defaultValue = "10")Integer rows){
        return this.hobbyFeignService.findAllHobby(page, rows);
    }

    @PostMapping(value = "/find_all_hobby")
    public Result findAll(){
        return this.hobbyFeignService.findAll();
    }


    @PostMapping(value = "/insert")
    public Result insert(XiaoxiaoHobby hobby){
        return this.hobbyFeignService.insert(hobby);
    }


    @PostMapping(value = "/delete_hobby_by_id")
    public Result deleteHobbyById(String hobbyId){
        return this.hobbyFeignService.deleteHobbyById(hobbyId);
    }


    /**
     * 查找用户的爱好
     * @return
     */
    @PostMapping(value = "/find_user_hobby")
    public Result findUserHobby(Long userId){
        return this.hobbyFeignService.findUserHobby(userId);
    }

}
