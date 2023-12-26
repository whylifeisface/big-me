package com.example.bigme.service.imp;

import com.example.bigme.pojo.Data;
import com.example.bigme.pojo.FreeImg;
import com.example.bigme.pojo.Result;
import com.example.bigme.service.FreeImgService;
import kong.unirest.*;
import kong.unirest.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
public class FreeImgServiceImp implements FreeImgService {
    @Override
    public FreeImg getProfile() {

        JSONObject object = Unirest.get("https://www.freeimg.cn/api/v1/profile")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer 52|Xj8H4E6LZkF4in8BYk7JlsrcEtHFmLgIivRNGRwU")
                .header("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .asJson().getBody().getObject();

        Object data = object.get("data");
        System.out.println(data);
        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        FreeImg freeImg = jsonObjectMapper.readValue(data.toString(), FreeImg.class);

        System.out.println(freeImg);

        return freeImg;
    }


    //TODO
    @Override
    public FreeImg getStrategies(int num, int seconds) {
        JSONObject object = Unirest.post("https://www.freeimg.cn/api/v1/images/tokens?num="+num+ "&seconds=" + seconds)
                .header("Authorization", "Bearer 52|Xj8H4E6LZkF4in8BYk7JlsrcEtHFmLgIivRNGRwU")
                .header("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .asJson().getBody().getObject();
        Object data = object.get("data");
        System.out.println(data);
        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        return jsonObjectMapper.readValue(data.toString(), FreeImg.class);
    }

    @Override
    public Result<Object> upload(
            MultipartFile multipartFile,
            String token,
            Integer permission,
            Integer strategy_id,
            Integer album_id,
            String expired_at
    ) {
        File file = null;
        try {
            file = new File("d:/big-me/upload.jpg");
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            return Result.error("file");
        }

        HttpResponse<JsonNode> res = Unirest.post("https://www.freeimg.cn/api/v1/upload")
                .header("Accept", "form-data")
                .header("Authorization", "Bearer 43|CkP2xSETFkG1qkQgR0GKvTEqv3ektv3oNT6XCXYU")
                .header("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .field("file", file)
                .asJson();
        if (res.getStatus() != 200) {
            return Result.error(res.getStatus() + "");
        }
        Object data = res.getBody().getObject().get("data");
        System.out.println(data);
        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();

        return Result.success(jsonObjectMapper.readValue(data.toString(), Data.class));
    }

    @Override
    public FreeImg images(Integer page, String order, Integer permission, Integer album_id, String q) {
        JSONObject response = Unirest.get("https://www.freeimg.cn/api/v1/images/images")
                .header("Authorization", "Bearer 52|Xj8H4E6LZkF4in8BYk7JlsrcEtHFmLgIivRNGRwU")
//                .header("Content-Type", "multipart/form-data")
                .header("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
//                .field("file", new File("C:\\Users\\ASUS\\Pictures\\1672714065447.jpg"))
                .asJson().getBody().getObject();

        Object data = response.get("data");
        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        return jsonObjectMapper.readValue(data.toString(), FreeImg.class);
    }


}
