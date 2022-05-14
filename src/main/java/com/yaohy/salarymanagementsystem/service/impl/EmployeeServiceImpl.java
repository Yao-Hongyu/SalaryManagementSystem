package com.yaohy.salarymanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaohy.salarymanagementsystem.mapper.EmployeeMapper;
import com.yaohy.salarymanagementsystem.pojo.domain.Employee;
import com.yaohy.salarymanagementsystem.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
