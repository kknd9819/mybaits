package com.zz.mybaits.service;

import com.zz.mybaits.entity.User;

public interface UserService {


    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 操作结果
     */
    void register(User user);


}
