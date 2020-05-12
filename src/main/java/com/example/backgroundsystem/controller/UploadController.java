package com.example.backgroundsystem.controller;

import com.alibaba.fastjson.JSON;
import com.example.backgroundsystem.utils.LoggerUtils;
import com.example.backgroundsystem.utils.UploadUtils;
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

/**
 * 用于上传资源
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @ResponseBody
    @RequestMapping("uploadImages")
    public String uploadsImages(HttpServletRequest request, @RequestParam(value = "editormd-image-file", required = false) MultipartFile upload, Map<String,Object> map){
        if(upload.isEmpty()){
            map.put("success",0);
            map.put("message","上传失败");
            map.put("url","null");
            return JSON.toJSONString(map);
        }
        // 拿到文件名
        String filename = upload.getOriginalFilename();

        // 存放上传图片的文件夹
        File fileDir = UploadUtils.getImgDirFile();
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        System.out.println(fileDir.getAbsolutePath());


        try {
            // 构建真实的文件路径
            filename = UUID.randomUUID().toString().replace("-", "")+filename;
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
            LoggerUtils.debug(newFile.getAbsolutePath());

            // 上传图片到 -》 “绝对路径”
            upload.transferTo(newFile);

            String contextPath = request.getContextPath();
            if(contextPath.length()<1){
                contextPath += File.separator;
            }

            String url = contextPath+UploadUtils.IMG_PATH_PREFIX.replace("/","\\")+File.separator+filename;
            map.put("success",1);
            map.put("message","上传成功");
            map.put("url",url);
            return JSON.toJSONString(map);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("success",0);
            map.put("message","上传失败");
            map.put("url","null");
            return JSON.toJSONString(map);
        }

    }
}
