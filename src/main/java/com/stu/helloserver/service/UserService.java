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
     * @return 登录结果(包含Token)
     */
    Result<String> login(UserDTO userDTO);

    /**
     * 根据ID获取用户
     * @param id 用户ID
     * @return 用户信息
     */
    Result<String> getUserById(Long id);

    /**
     * 获取用户分页数据
     * @param pageNum 页码(从1开始)
     * @param pageSize 每页显示条数
     * @return 分页结果(包含records、total、current、pages等)
     */
    Result<Object> getUserPage(Integer pageNum, Integer pageSize);
}