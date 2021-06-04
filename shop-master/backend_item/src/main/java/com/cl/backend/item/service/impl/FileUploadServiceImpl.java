package com.cl.backend.item.service.impl;

import com.cl.backend.item.service.FileUploadService;
import com.cl.gzshop.utils.FtpUtil;
import com.cl.gzshop.utils.IDUtils;
import com.cl.gzshop.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 上传图片service
 * @Author l
 * @Date 2020/9/28 20:31
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${ftpUrl}") String ftpUrl;
    @Value("${ftpPort}") String ftpPort;
    @Value("${ftpUserName}") String ftpUserName;
    @Value("${ftpPassword}") String ftpPassword;
    @Value("${ftpBasePath}") String ftpBasePath;
    @Value("${la_lp}") String la_lp;


    @Override
    public Result fileUpload(MultipartFile multipartFile) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
            String path = sdf.format(new Date());
            String newFileName = IDUtils.genImageName() + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            // 修改图片名称
//            String newFileName =System.currentTimeMillis()+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
//            boolean storeFile = FtpUtil.fileUploadByFtp(ftpUrl, Integer.parseInt(ftpPort), ftpUserName, ftpPassword,
//                    ftpBasePath+path  , multipartFile.getInputStream(), newFileName, Boolean.parseBoolean(la_lp));

            boolean b = FtpUtil.uploadFile(ftpUrl, Integer.parseInt(ftpPort), ftpUserName, ftpPassword, ftpBasePath, path, newFileName, multipartFile.getInputStream());
            if(b){
                String url="http://"+this.ftpUrl+path+newFileName;
                return Result.ok(url);
            }else {
                return  Result.error("上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  Result.error("上传失败");
    }
}
