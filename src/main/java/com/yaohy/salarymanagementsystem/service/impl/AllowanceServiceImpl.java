package com.yaohy.salarymanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaohy.salarymanagementsystem.mapper.AllowanceMapper;
import com.yaohy.salarymanagementsystem.pojo.domain.Allowance;
import com.yaohy.salarymanagementsystem.service.AllowanceService;
import org.springframework.stereotype.Service;

@Service
public class AllowanceServiceImpl extends ServiceImpl<AllowanceMapper,Allowance> implements AllowanceService {
}
