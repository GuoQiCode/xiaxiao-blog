package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoHeadPhoto;
import com.xiaoxiao.service.RedisHeadPhotoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/23:12:22
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis/head_photo_service")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RedisHeadPhotoController {

    @Autowired
    private RedisHeadPhotoService redisHeadPhotoService;

    @ApiOperation(value = "缓存留言头像", notes = "缓存留言头像")
    @PostMapping(value = "/insertHeadPhoto")
    public void insertHeadPhoto(@RequestBody List<XiaoxiaoHeadPhoto> xiaoxiaoHeadPhotos) {
        this.redisHeadPhotoService.insertHeadPhoto(xiaoxiaoHeadPhotos);
    }

    @ApiOperation(value = "获取缓存留言头像", notes = "获取缓存留言头像")
    @PostMapping(value = "/getHeadPhoto")
    public List<XiaoxiaoHeadPhoto> getHeadPhoto() {
       return  this.redisHeadPhotoService.getHeadPhoto();
    }

    @ApiOperation(value = "删除留言头像", notes = "删除缓存留言头像")
    @PostMapping(value = "/deleteHeadPhoto")
    public void deleteHeadPhoto() {
        this.redisHeadPhotoService.deleteHeadPhoto();
    }

}
