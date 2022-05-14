package com.yaohy.salarymanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaohy.salarymanagementsystem.mapper.EmployeeTypeMapper;
import com.yaohy.salarymanagementsystem.pojo.domain.EmployeeType;
import com.yaohy.salarymanagementsystem.service.EmployeeTypeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeTypeServiceImpl extends ServiceImpl<EmployeeTypeMapper,EmployeeType> implements EmployeeTypeService {
}
