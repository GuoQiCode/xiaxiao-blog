package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoHobby;
import com.xiaoxiao.service.backend.TinyHobbyService;
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
 * @date:2019/12/4:11:46
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/tiny_admin_hobby_service")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api("愛好接口")
public class TinyHobbyController
{
    @Autowired
    private TinyHobbyService tinyHobbyService;

    /**
     *
     * @param page
     * @param rows
     * @return
     */
    @ApiOperation(value = "分頁查询爱好数据",response = Result.class,notes = "分頁查询爱好数据")
    @PostMapping(value = "/find_all")
    public Result findAllHobby(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "rows",defaultValue = "10") Integer rows){
        return this.tinyHobbyService.findAllHobby(page,rows);
    }


    /**
     * 删除爱好
     * @param hobbyId
     * @return
     */
    @ApiOperation(value = "删除",response = Result.class,notes = "删除")
    @PostMapping(value = "/delete_hobby_by_id")
    public Result deleteHobbyById(@RequestParam(name = "hobbyId") String hobbyId){
        return this.tinyHobbyService.deleteHobbyById(hobbyId);
    }

    /**
     * 获取用户的爱好
     * @param userId
     * @return
     */
    @ApiOperation(value = "查询用户的爱好",response = Result.class,notes = "查询用户的爱好")
    @PostMapping(value = "/find_user_hobby")
    public  Result findUserHobby(@RequestParam(name = "userId") Long userId){
        return this.tinyHobbyService.findUserHobby(userId);
    }

    /**
     * 插入新的爱好
     * @param hobby
     * @return
     */
    @ApiOperation(value = "插入新的爱好",response = Result.class,notes = "插入新的爱好")
    @PostMapping(value = "/insert")
    public  Result insert(@RequestBody XiaoxiaoHobby hobby){
        return this.tinyHobbyService.insert(hobby);
    }



    @ApiOperation(value = "查询全部的爱好",response = Result.class,notes = "查询全部的爱好")
    @PostMapping(value = "/find_all_hobby")
    public Result findAllHobby(){
        return this.tinyHobbyService.findAllHobby();
    }
}
