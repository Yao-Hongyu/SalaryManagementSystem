package com.yaohy.salarymanagementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaohy.salarymanagementsystem.mapper.AdminMapper;
import com.yaohy.salarymanagementsystem.pojo.dto.CommonResult;
import com.yaohy.salarymanagementsystem.pojo.domain.Admin;
import com.yaohy.salarymanagementsystem.pojo.dto.result.TokenResult;
import com.yaohy.salarymanagementsystem.service.AdminService;
import com.yaohy.salarymanagementsystem.util.EncryptUtil;
import com.yaohy.salarymanagementsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public CommonResult register(String username, String password) {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username));


        if (Objects.nonNull(admin)) return CommonResult.fail();

        admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(EncryptUtil.md5Encrypt(password));
        adminMapper.insert(admin);

        return CommonResult.success();
    }

    @Override
    public CommonResult login(String username, String password) {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username));

        if (Objects.isNull(admin)) return CommonResult.fail();

        if (!admin.getPassword().equals(EncryptUtil.md5Encrypt(password))) return CommonResult.fail();

        TokenResult token = new TokenResult();
        token.setToken(JwtUtil.sign(admin.getId().toString()));

        return CommonResult.success(token);
    }
}
