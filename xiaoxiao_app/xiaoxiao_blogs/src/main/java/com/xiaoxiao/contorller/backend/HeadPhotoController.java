package com.xiaoxiao.contorller.backend;

import com.xiaoxiao.pojo.XiaoxiaoHeadPhoto;
import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import com.xiaoxiao.service.backend.HeadPhotoService;
import com.xiaoxiao.utils.BackendUploadResult;
import com.xiaoxiao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * _ooOoo_ o8888888o 88" . "88 (| -_- |) O\ = /O ____/`---'\____ .' \\| |// `. / \\||| : |||// \ / _||||| -:- |||||- \ |
 * | \\\ - /// | | | \_| ''\---/'' | | \ .-\__ `-` ___/-. / ___`. .' /--.--\ `. . __ ."" '< `.___\_<|>_/___.' >'"". | |
 * : `- \`.;`\ _ /`;.`/ - ` : | | \ \ `-. \_ __\ /__ _/ .-` / / ======`-.____`-.___\_____/___.-`____.-'====== `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 佛祖保佑 永无BUG 佛曰: 写字楼里写字间，写字间里程序员； 程序人员写程序，又拿程序换酒钱。 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。 但愿老死电脑间，不愿鞠躬老板前； 奔驰宝马贵者趣，公交自行程序员。 别人笑我忒疯癫，我笑自己命太贱； 不见满街漂亮妹，哪个归得程序员？
 *
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/22:22:08
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/admin/head/photo")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HeadPhotoController {
    @Autowired
    private HeadPhotoService headPhotoService;

    /**
     * 获取全部的头像
     *
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/findAll")
    public Result findAllHeadPhoto(@RequestParam(name = "page", defaultValue = "1") Integer page,
        @RequestParam(name = "rows", defaultValue = "10") Integer rows) {
        return this.headPhotoService.findAllHeadPhoto(page, rows);
    }

    /**
     * 获取一个
     * 
     * @param xiaoxiaoHeadPhoto
     * @return
     */
    @PostMapping(value = "/findOne")
    public Result findOne(XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto) {
        return this.headPhotoService.findOne(xiaoxiaoHeadPhoto);
    }

    /**
     * 删除
     * 
     * @param xiaoxiaoHeadPhoto
     * @return
     */
    @PostMapping(value = "/delete")
    public Result delete(XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto) {
        return this.headPhotoService.delete(xiaoxiaoHeadPhoto);
    }

    /**
     * 插入
     * 
     * @param xiaoxiaoHeadPhoto
     * @return
     */
    @PostMapping(value = "/insert")
    public Result insert(XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto) {
        return this.headPhotoService.insert(xiaoxiaoHeadPhoto);
    }

    /**
     * 修改
     * 
     * @param xiaoxiaoHeadPhoto
     * @return
     */
    @PostMapping(value = "/update")
    public Result update(XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto) {
        return this.headPhotoService.update(xiaoxiaoHeadPhoto);
    }

    /**
     * 上传
     * 
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    public BackendUploadResult upload(@RequestParam(name = "file") MultipartFile file) {
        return this.headPhotoService.upload(file);
    }
}
