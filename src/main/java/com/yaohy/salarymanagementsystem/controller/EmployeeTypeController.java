package com.yaohy.salarymanagementsystem.controller;

import com.yaohy.salarymanagementsystem.pojo.domain.Employee;
import com.yaohy.salarymanagementsystem.pojo.domain.EmployeeType;
import com.yaohy.salarymanagementsystem.pojo.dto.CommonResult;
import com.yaohy.salarymanagementsystem.service.EmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employeeType")
public class EmployeeTypeController {

    @Autowired
    private EmployeeTypeService employeeTypeService;

    @RequestMapping("/add")
    public CommonResult add(@RequestBody EmployeeType employeeType){
        employeeTypeService.save(employeeType);

        return CommonResult.success();
    }

    @RequestMapping("/update")
    public CommonResult update(@RequestBody EmployeeType employeeType){
        employeeTypeService.updateById(employeeType);

        return CommonResult.success();
    }

    @RequestMapping("/delete")
    public CommonResult delete(@RequestBody EmployeeType employeeType){
        employeeTypeService.removeById(employeeType.getId());

        return CommonResult.success();
    }

    @RequestMapping("/getAll")
    public CommonResult getAll(){
        List<EmployeeType> list = employeeTypeService.list();

        return CommonResult.success(list);
    }

    @RequestMapping("/get")
    public CommonResult getById(@RequestParam("id") Integer id){
        EmployeeType employeeType = employeeTypeService.getById(id);

        return CommonResult.success(employeeType);
    }

}
