package com.shibro.nativeproducts.service;

import com.shibro.nativeproducts.data.dto.FileUploadResponse;
import com.shibro.nativeproducts.data.dto.FileUploadResponseItem;
import com.shibro.nativeproducts.data.enums.ErrorCodeEnum;
import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private static final Logger LOG = LoggerFactory.getLogger(FileService.class);

    /**
     *   d:/web_picture/native_products/
     */
    @Value("${file.path.local.url}")
    private String  localUrl;

    public BaseResponseVo uploadFile(MultipartFile file) {
        BaseResponseVo baseResponseVo = new BaseResponseVo(ErrorCodeEnum.SUCCESS);
        String fileOriginName = file.getOriginalFilename();
        String uuidName = getUUIDName(fileOriginName);
        String url = saveFile(uuidName,file);
        FileUploadResponseItem item = new FileUploadResponseItem();
        item.setName(file.getOriginalFilename());
        item.setUrl(url);
        baseResponseVo.setData(item);
        return baseResponseVo;
    }


    public BaseResponseVo uploadFile( List<MultipartFile> files) {
        BaseResponseVo baseResponseVo = new BaseResponseVo(ErrorCodeEnum.SUCCESS);
        FileUploadResponse responseData = new FileUploadResponse();
        List<FileUploadResponseItem> items = new ArrayList<>();
        for(MultipartFile file:files){
            String fileOriginName = file.getOriginalFilename();
            String uuidName = getUUIDName(fileOriginName);
            String url = saveFile(uuidName,file);
            FileUploadResponseItem item = new FileUploadResponseItem();
            item.setName(uuidName);
            item.setUrl(url);
            items.add(item);
        }
        responseData.setUploadResults(items);
        baseResponseVo.setData(responseData);
        return baseResponseVo;
    }

    private String saveFile(String uuidName, MultipartFile file) {
            String url = localUrl+uuidName;
        try {
            String savePath = localUrl;
            File saveFile  = new File(savePath);
            FileUtil.mkDir(saveFile);
            BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(localUrl+uuidName));
            byte [] buffer = new byte[1024*8];
            while (true){
                int length = bis.read(buffer);
                if(length == -1){
                    break;
                }
                bos.write(buffer,0,length);
            }
            bis.close();
            bos.close();
        } catch (IOException e) {
            LOG.error("文件上传失败，文件名:{}",file.getOriginalFilename());
        }
        return url;
    }

    private String getUUIDName(String fileOriginName) {
        String uuid  = UUID.randomUUID().toString().replaceAll("-","");
        return uuid+"."+fileOriginName.split("\\.")[1];
    }


}
