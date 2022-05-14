package com.yaohy.salarymanagementsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yaohy.salarymanagementsystem.pojo.domain.Employee;
import com.yaohy.salarymanagementsystem.pojo.domain.EmployeeType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeTypeMapper extends BaseMapper<EmployeeType> {

}