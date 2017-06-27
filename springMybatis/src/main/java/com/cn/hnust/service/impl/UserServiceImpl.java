package com.cn.hnust.service.impl;

import com.cn.hnust.IDao.UserMapper;
import com.cn.hnust.domain.User;
import com.cn.hnust.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by songll on 2017/6/27.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(int userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }
}
