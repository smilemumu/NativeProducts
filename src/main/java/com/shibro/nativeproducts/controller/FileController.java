package com.shibro.nativeproducts.controller;

import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.service.FileService;
import com.shibro.nativeproducts.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class FileController {



    @Resource
    private FileService fileService;

    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    public BaseResponseVo uploadFile(@RequestBody  MultipartFile file, HttpServletRequest request){
        BaseResponseVo responseVo = fileService.uploadFile(file,request);
        return responseVo;
    }

    @RequestMapping(value = "/files/upload",method = RequestMethod.POST)
    public BaseResponseVo uploadFiles(@RequestBody List<MultipartFile> files,HttpServletRequest request){
        BaseResponseVo responseVo = fileService.uploadFile(files,request);
        return responseVo;
    }
}
