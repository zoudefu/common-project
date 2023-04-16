package com.common.project.minio.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.common.project.minio.entity.Result;
import com.common.project.minio.util.MinIoUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: zoudefu
 * Date: 2022/04/12/10:12
 */
@RestController
@RequestMapping("/minio")
public class MinIoController {

    @Resource
    private MinIoUtils minIoUtils;

    // 存储桶名称
    private static final String MINIO_BUCKET = "img";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(@RequestParam(value = "files") MultipartFile files){
        try {
            return Result.ok(minIoUtils.upload(files,MINIO_BUCKET,null));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/download")
    public void download(@RequestParam("minFileName")String minFileName,HttpServletResponse response){
            minIoUtils.download(response,"img",minFileName);
    }
}
