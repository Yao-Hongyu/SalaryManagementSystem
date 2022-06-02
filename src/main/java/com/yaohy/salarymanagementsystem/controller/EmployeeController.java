package com.yaohy.salarymanagementsystem.controller;

import com.yaohy.salarymanagementsystem.mapper.EmployeeMapper;
import com.yaohy.salarymanagementsystem.pojo.domain.Allowance;
import com.yaohy.salarymanagementsystem.pojo.domain.Employee;
import com.yaohy.salarymanagementsystem.pojo.domain.YearBonus;
import com.yaohy.salarymanagementsystem.pojo.dto.CommonResult;
import com.yaohy.salarymanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping("/add")
    public CommonResult add(@RequestBody Employee employee){
        employeeService.save(employee);

        return CommonResult.success();
    }

    @RequestMapping("/update")
    public CommonResult update(@RequestBody Employee employee){
        employeeService.updateById(employee);

        return CommonResult.success();
    }

    @RequestMapping("/delete")
    public CommonResult delete(@RequestBody Employee employee){
        employeeService.removeById(employee.getId());

        return CommonResult.success();
    }

    @RequestMapping("/getAll")
    public CommonResult getAll(){
        List<Employee> list = employeeService.list();

        return CommonResult.success(list);
    }

    @RequestMapping("/get")
    public CommonResult getById(@RequestParam("id") Integer id){
        Employee employee = employeeService.getById(id);

        return CommonResult.success(employee);
    }

    @RequestMapping("/getYearBonus")
    public CommonResult getYearBonus(){
        List<YearBonus> yearBonusList = employeeMapper.getYearBonus();

        return CommonResult.success(yearBonusList);
    }

}
