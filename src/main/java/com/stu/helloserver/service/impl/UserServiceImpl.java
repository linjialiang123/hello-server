package com.stu.helloserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.helloserver.common.Result;
import com.stu.helloserver.common.ResultCode;
import com.stu.helloserver.dto.UserDTO;
import com.stu.helloserver.entity.User;
import com.stu.helloserver.mapper.UserMapper;
import com.stu.helloserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<String> register(UserDTO userDTO) {
        // 1. 查询该用户名是否已存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);
        
        if (dbUser != null) {
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }

        // 2. 组装实体对象
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        // 3. 插入数据库
        userMapper.insert(user);

        return Result.success("注册成功!");
    }

    @Override
    public Result<String> login(UserDTO userDTO) {
        // 1. 根据用户名查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);

        // 2. 校验用户是否存在
        if (dbUser == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }

        // 3. 校验密码是否正确
        // 注意:实际生产中密码应加密存储和比对,此处暂按明文比对以符合当前简单逻辑
        if (!dbUser.getPassword().equals(userDTO.getPassword())) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }

        // 4. 生成 Token (简单模拟,实际项目应使用 JWT 等)
        String token = "Bearer " + UUID.randomUUID().toString().replace("-", "");

        return Result.success(token);
    }

    @Override
    public Result<String> getUserById(Long id) {
        // 1. 根据id查询数据库
        User user = userMapper.selectById(id);

        // 2. 校验用户是否存在
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }

        return Result.success("查询成功,用户名为: " + user.getUsername());
    }

    @Override
    public Result<Object> getUserPage(Integer pageNum, Integer pageSize) {
        // 1. 创建分页对象(参数1:当前页码,参数2:每页显示条数)
        Page<User> pageParam = new Page<>(pageNum, pageSize);

        // 2. 执行分页查询(参数1:分页对象,参数2:查询条件Wrapper,这里传null代表查询全部)
        // 框架会自动执行一条COUNT语句查总数,再拼接LIMIT执行分页
        Page<User> resultPage = userMapper.selectPage(pageParam, null);

        // 3. 返回结果(resultPage中包含了records数据列表、total总条数、pages总页数等)
        return Result.success(resultPage);
    }
}