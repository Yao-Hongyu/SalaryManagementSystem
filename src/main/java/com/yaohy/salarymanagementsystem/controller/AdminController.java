package com.yaohy.salarymanagementsystem.controller;

import com.yaohy.salarymanagementsystem.pojo.domain.Admin;
import com.yaohy.salarymanagementsystem.pojo.dto.CommonResult;
import com.yaohy.salarymanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public CommonResult register(@RequestBody Admin admin){

        return adminService.register(admin.getUsername(),admin.getPassword());
    }

    @PostMapping("/login")
    public CommonResult login(@RequestBody Admin admin){

        return adminService.login(admin.getUsername(),admin.getPassword());
    }

}
