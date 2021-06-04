package com.cl.backend.item.service;

import com.cl.gzshop.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author l
 * @Date 2020/9/28 20:30
 */
public interface FileUploadService {

    Result fileUpload(MultipartFile multipartFile);
}
