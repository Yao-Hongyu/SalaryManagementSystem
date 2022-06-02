package com.yaohy.salarymanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yaohy.salarymanagementsystem.pojo.domain.EmployeeType;
import com.yaohy.salarymanagementsystem.pojo.domain.MonthSalary;
import com.yaohy.salarymanagementsystem.pojo.dto.CommonResult;
import com.yaohy.salarymanagementsystem.service.MonthSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/monthSalary")
public class MonthSalaryController {

    @Autowired
    private MonthSalaryService monthSalaryService;

    @RequestMapping("/add")
    public CommonResult add(@RequestBody MonthSalary monthSalary){
        monthSalaryService.save(monthSalary);

        return CommonResult.success();
    }

    @RequestMapping("/update")
    public CommonResult update(@RequestBody MonthSalary monthSalary){
        monthSalaryService.updateById(monthSalary);

        return CommonResult.success();
    }

    @RequestMapping("/delete")
    public CommonResult delete(@RequestBody MonthSalary monthSalary){
        monthSalaryService.removeById(monthSalary.getEmployeeId());

        return CommonResult.success();
    }

    @RequestMapping("/getAll")
    public CommonResult getAll(){
        List<MonthSalary> list = monthSalaryService.list();

        return CommonResult.success(list);
    }

    @RequestMapping("/get")
    public CommonResult getById(@RequestParam("id") Integer id){
        MonthSalary monthSalary = monthSalaryService.getOne(new QueryWrapper<MonthSalary>().eq("employee_id",id));

        return CommonResult.success(monthSalary);
    }

}
