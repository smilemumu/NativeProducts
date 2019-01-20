package com.shibro.nativeproducts.persistence;

import com.shibro.nativeproducts.data.entity.PictureInfo;
import org.springframework.stereotype.Component;

@Component
public interface PictureInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(PictureInfo record);

    PictureInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PictureInfo record);

}