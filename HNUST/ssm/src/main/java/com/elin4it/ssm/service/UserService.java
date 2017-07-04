package com.elin4it.ssm.service;
import com.elin4it.ssm.pojo.User;

import java.util.List;
/**
 * Created by songll on 2017/6/29.
 */
public interface UserService {
    /**
     * 查找所有用户
     * @return
     * @throws Exception
     */
    List<User> findUser()throws Exception;
}
