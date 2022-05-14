package com.yaohy.salarymanagementsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yaohy.salarymanagementsystem.pojo.dto.CommonResult;
import com.yaohy.salarymanagementsystem.pojo.domain.Admin;

public interface AdminService extends IService<Admin> {

    public CommonResult register(String username,String password);

    public CommonResult login(String username,String password);

}
