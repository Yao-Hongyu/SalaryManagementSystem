package com.yaohy.salarymanagementsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yaohy.salarymanagementsystem.pojo.domain.Employee;
import com.yaohy.salarymanagementsystem.pojo.domain.YearBonus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select("Select * from year_bonus")
    public List<YearBonus> getYearBonus();

}