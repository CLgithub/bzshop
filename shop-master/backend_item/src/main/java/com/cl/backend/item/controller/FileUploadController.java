package com.cl.backend.item.controller;

import com.cl.backend.item.service.FileUploadService;
import com.cl.gzshop.utils.Result;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 图片上传
 * @Author l
 * @Date 2020/9/28 20:27
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Resource
    FileUploadService fileUploadService;


    /**
     * 图片上传
     * @return
     */
    @RequestMapping("/upload")
    public Result fileUpload(MultipartFile file){
        try {
            return fileUploadService.fileUpload(file);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
