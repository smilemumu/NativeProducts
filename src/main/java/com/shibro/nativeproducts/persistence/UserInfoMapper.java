package com.shibro.nativeproducts.persistence;

import com.shibro.nativeproducts.data.entity.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserInfo record);

    void updateByPrimaryKeySelective(UserInfo record);

    UserInfo selectByUserInfo(UserInfo record);

    UserInfo isExistsUserName(UserInfo parseObject);
}