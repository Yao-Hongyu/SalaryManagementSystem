package com.yaohy.salarymanagementsystem.controller;

import com.yaohy.salarymanagementsystem.pojo.domain.Allowance;
import com.yaohy.salarymanagementsystem.pojo.dto.CommonResult;
import com.yaohy.salarymanagementsystem.service.AllowanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/allowance")
public class AllowanceController {

    @Autowired
    private AllowanceService allowanceService;

    @RequestMapping("/add")
    public CommonResult add(@RequestBody Allowance allowance){
        allowanceService.save(allowance);

        return CommonResult.success();
    }

    @RequestMapping("/update")
    public CommonResult update(@RequestBody Allowance allowance){
        allowanceService.updateById(allowance);

        return CommonResult.success();
    }

    @RequestMapping("/delete")
    public CommonResult delete(@RequestBody Allowance allowance){
        allowanceService.removeById(allowance.getId());

        return CommonResult.success();
    }

    @RequestMapping("/getAll")
    public CommonResult getAll(){
        List<Allowance> list = allowanceService.list();

        return CommonResult.success(list);
    }

    @RequestMapping("/get")
    public CommonResult getById(@RequestParam("id") Integer id){
        Allowance allowance = allowanceService.getById(id);

        return CommonResult.success(allowance);
    }

}
