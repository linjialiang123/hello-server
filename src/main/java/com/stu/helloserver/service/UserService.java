package com.stu.helloserver.service;

import com.stu.helloserver.common.Result;
import com.stu.helloserver.dto.UserDTO;

public interface UserService {
    /**
     * 用户注册
     * @param userDTO 用户信息
     * @return 注册结果
     */
    Result<String> register(UserDTO userDTO);

    /**
     * 用户登录
     * @param userDTO 用户信息
     * @return 登录结果（包含Token）
     */
    Result<String> login(UserDTO userDTO);
}