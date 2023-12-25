package com.example.bigme.controller.api;

import com.example.bigme.pojo.FreeImg;
import com.example.bigme.pojo.Result;
import com.example.bigme.service.FreeImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/freeimg")
public class FreeImgController {

    @Autowired
    private FreeImgService freeImgService;

    @GetMapping("/profile")
    //参数
    public Result<Object> getProfile() {

       return Result.success(freeImgService.getProfile());

    }

    @GetMapping("/strategies")
    public Result<FreeImg> getStrategies() {
//        *num	Integer	生成数量，最大 100
//                *seconds	Integer	有效期(秒)，最大 2626560 (一个月)
        return Result.success(freeImgService.getStrategies(1, 100));

    }
    @PostMapping("/upload")
    private Result<Object> upload(
          @RequestParam(required = true) MultipartFile file,
          @RequestParam(required = false)  String token,
          @RequestParam(required = false)  Integer permission,
          @RequestParam(required = false)  Integer strategy_id,
          @RequestParam(required = false)  Integer album_id,
          @RequestParam(required = false)  String expire_at
    ) {
//      * file	File	图片文件 //必须
//        token	String	临时上传 Token
//        permission	Integer	权限，1=公开，0=私有
//        strategy_id	Integer	储存策略ID
//        album_id	Integer	相册ID
//        expired_at	String	图片过期时间(yyyy-MM-dd HH:mm:ss)
        return freeImgService.upload(file, token, permission, strategy_id, album_id, expire_at);
    }

    @PostMapping("/images")
    //参数

    private Result<Object> images(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false)  String order,
            @RequestParam(required = false)  Integer permission,
            @RequestParam(required = false)  Integer album_id,
            @RequestParam(required = false)  String q
    ) {
//        page	Integer	页码
//        order	String	排序方式，newest=最新，earliest=最早，utmost=最大，least=最小
//        permission	String	权限，public=公开的，private=私有的
//        album_id	Integer	相册 ID
//        q	String	筛选关键字
        return Result.success(freeImgService.images(page, order, permission, album_id, q));
    }
}
