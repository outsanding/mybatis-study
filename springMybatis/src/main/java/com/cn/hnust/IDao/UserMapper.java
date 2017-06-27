package com.cn.hnust.IDao;

import com.cn.hnust.domain.User;
import com.cn.hnust.domain.UserWithBLOBs;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(UserWithBLOBs record);

    int insertSelective(UserWithBLOBs record);

    UserWithBLOBs selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(UserWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UserWithBLOBs record);

    int updateByPrimaryKey(User record);
}