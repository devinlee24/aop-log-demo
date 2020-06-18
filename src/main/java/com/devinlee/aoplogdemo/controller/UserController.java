package com.devinlee.aoplogdemo.controller;

import com.devinlee.aoplogdemo.annotation.Log;
import com.devinlee.aoplogdemo.enums.OperateType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    /**
     * 访问路径 http://localhost:8080/user/get?tel=24
     * @param tel
     * @return
     */
    @GetMapping("/user/get")
    @Log(desc = "通过手机号获取用户", level = 1, operateType = OperateType.SELECT)
    public String get(@RequestParam(name = "tel") String tel) {

        return String.format("返回用户[%s]", tel);
    }
}
