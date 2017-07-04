package com.elin4it.ssm.service;
import com.elin4it.ssm.mapper.UserMapper;
import com.elin4it.ssm.pojo.User;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by songll on 2017/6/29.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    //User接口
    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl() {
        init();
    }

    public List<User> findUser() throws Exception {
        //调用mapper类中的selectByExample方法，如果传入类型为null，则表示无条件查找
        List<User> users = userMapper.selectByExample(null);

        return users;
    }

    void init() {
        try {
            while (true){
                System.out.println(Thread.currentThread().getName()+"-"+System.currentTimeMillis());
                Thread.currentThread().sleep(1000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}