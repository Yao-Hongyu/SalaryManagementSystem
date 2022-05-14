package com.yaohy.salarymanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaohy.salarymanagementsystem.mapper.MonthSalaryMapper;
import com.yaohy.salarymanagementsystem.pojo.domain.MonthSalary;
import com.yaohy.salarymanagementsystem.service.MonthSalaryService;
import org.springframework.stereotype.Service;

@Service
public class MonthSalaryServiceImpl extends ServiceImpl<MonthSalaryMapper, MonthSalary> implements MonthSalaryService {
}
