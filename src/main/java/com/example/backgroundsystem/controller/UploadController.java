package com.example.backgroundsystem.controller;

import com.alibaba.fastjson.JSON;
import com.example.backgroundsystem.utils.LoggerUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * 用于上传资源
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @ResponseBody
    @RequestMapping("uploadImages")
    public String uploadsImages(HttpServletRequest request, @RequestParam(value = "editormd-image-file", required = false) MultipartFile upload, Map<String,Object> map){
        String path = request.getSession().getServletContext().getRealPath("/uploads/blog");
        System.out.println(path);
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        String filename = upload.getOriginalFilename();

        //把文件名称设置为唯一值
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+filename;
        try {
            upload.transferTo(new File(path,filename));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("异常");
        }
        String url = path + File.separator+filename;

        LoggerUtils.debug(url);
        //editor.md希望返回的消息格式
        map.put("success",1);
        map.put("message","上传成功");
        map.put("url",url);
        return JSON.toJSONString(map);
    }
}
