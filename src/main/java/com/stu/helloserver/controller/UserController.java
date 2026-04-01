package com.stu.helloserver.controller;

import com.stu.helloserver.common.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping("/login")
    public Result<String> login() {
        return Result.success("登录成功，返回Token：mock_token_123");
    }

    @PostMapping
    public Result<String> createUser() {
        return Result.success("新增用户成功");
    }

    @GetMapping("/{id}")
    public Result<String> getUser(@PathVariable("id") Long id) {
        // 将原本直接返回的 String 包装入 Result.success() 中
        String data = "查询成功，正在返回 ID 为 " + id + " 的用户信息";
        return Result.success(data);
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable("id") Long id) {
        return Result.success("删除成功，删除了 ID 为 " + id + " 的用户");
    }
}
