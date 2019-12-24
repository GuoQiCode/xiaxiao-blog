package com.xiaoxiao.contorller.backend;

import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * @date:2019/11/26:15:02
 * @author:shinelon
 * @Describe:页面的跳转
 */
@Controller
public class PageController
{




    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping(value = "/login")
    public String login (){
        return "admin/login";
    }


    /**
     * 跳转到博客管理界面
     * @return
     */
    @GetMapping(value = "/admin/blogs")
    public String blogs(){
        return "admin/blogs";
    }


    /**
     * 分类管理界面
     * @return
     */
    @GetMapping(value = "/admin/sorts")
    public String sorts(){
        return "admin/sorts";
    }


    @GetMapping(value = "/admin/blogs_input")
    public String blogsInput(){
        return "admin/blogs-input";
    }


    @GetMapping(value = "/admin/label")
    public String label(){
        return "admin/label";
    }


    @GetMapping(value = "/admin/article/flowchart")
    @ResponseBody
    public Result articleUpdate(){
        return Result.ok(StatusCode.OK, "/article/blogs_input");
    }
    @GetMapping(value = "/article/blogs_input")
    public String jump(){
        return "admin/blogs-input";
    }

    @GetMapping(value = "/admin/showBlogs")
    public String showBlogs(){
        return "admin/image-upload";
    }


    @GetMapping(value = "/admin/admin_manager")
    public String adminManager(){
        return "admin/admin-manager";
    }


    @GetMapping(value = "/admin/me")
    public String meAbout(){
        return "admin/admin-me-about";
    }


    /**
     * 我的博文页面
     * @return
     */
    @GetMapping(value = "/me_blog")
    public String meBlog(){
        return "me-blog";
    }


    /**
     * 跳转到修改密码的页面
     * @return
     */
    @GetMapping(value = "/admin/update_password")
    public String updatePassword(){
        return "admin/update-password";
    }

    @GetMapping(value = "/admin/advertising")
    public String advertising(){
        return "admin/advertising";
    }

    /**
     * 留言管理页面
     * @return
     */
    @GetMapping(value = "/admin/leave_message")
    public String leaveMessage(){
        return "admin/leave-message";
    }


    /**
     * 头像管理
     * @return
     */
    @GetMapping(value = "/admin/head_photo")
    public String headPhoto(){
        return "admin/head-photo";
    }

    /**
     * 公告
     * @return
     */
    @GetMapping(value = "/admin/notice")
    public String notice(){
        return "admin/notice";
    }

}
