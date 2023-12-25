package com.example.bigme.service;

import com.example.bigme.pojo.FreeImg;
import com.example.bigme.pojo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FreeImgService {

    FreeImg getProfile();

    FreeImg getStrategies(int num, int seconds);

    Result<Object> upload(MultipartFile file, String token, Integer permission, Integer strategy_id, Integer album_id, String expired_at);

    FreeImg images(Integer page, String order, Integer permission, Integer album_id, String q);

}
