package com.xiaoxiao.contorller.frontline;

import com.xiaoxiao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import javax.jws.WebParam;

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
 * @date:2019/12/5:17:33
 * @author:shinelon
 * @Describe:
 */
@Controller
@CrossOrigin(origins = {"*"},maxAge = 3600)

public class FrontlinePageController
{

    /**
     * 首页
     * @return
     */
    @GetMapping(value = "/")
    public String index(){
        return "page";
    }


    /**
     * 关于我
     * @return
     */
    @GetMapping(value = "/about")
    public String about(){
        return "about";
    }


    @Autowired
    private FrontlineArticleController frontlineArticleController;
    @Autowired
    private FrontlineLabelController frontlineLabelController;

    /**
     * 博客详情页面跳转
     * @return
     */
    @GetMapping(value = "/blog_details/{articleId}")
    public String blogDetails(@PathVariable Long articleId,Model model){
        Result result = frontlineArticleController.findBlogById(Long.parseLong(String.valueOf(articleId)));
        Result articleLabelName = frontlineLabelController.findArticleLabelName(articleId);
        model.addAttribute("article", result.getData());
        model.addAttribute("label", articleLabelName.getData());
        return "blog";
    }





    /**
     * 归档页面的跳转
     * @return
     */
    @GetMapping(value = "/archive")
    public String archive(){
        return "archives";
    }


    /**
     * 跳转到标签分类页
     * @return
     */
    @GetMapping(value = "/label")
    public String label(){
        return "tags";
    }


    /**
     * 根据分类展示文章
     * @return
     */
    @GetMapping(value = "/blog_type")
    public String blogType(){
        return "blog-type";
    }


    @GetMapping(value = "/blogLabel")
    public String blogLabel(){
        return "blog-label";
    }

}
