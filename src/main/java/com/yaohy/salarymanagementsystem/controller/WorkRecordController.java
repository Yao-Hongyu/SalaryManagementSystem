package com.yaohy.salarymanagementsystem.controller;

import com.yaohy.salarymanagementsystem.pojo.domain.MonthSalary;
import com.yaohy.salarymanagementsystem.pojo.domain.WorkRecord;
import com.yaohy.salarymanagementsystem.pojo.dto.CommonResult;
import com.yaohy.salarymanagementsystem.service.WorkRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workRecord")
public class WorkRecordController {

    @Autowired
    private WorkRecordService workRecordService;

    @RequestMapping("/add")
    public CommonResult add(@RequestBody WorkRecord workRecord){
        workRecordService.save(workRecord);

        return CommonResult.success();
    }

    @RequestMapping("/update")
    public CommonResult update(@RequestBody WorkRecord workRecord){
        workRecordService.updateById(workRecord);

        return CommonResult.success();
    }

    @RequestMapping("/delete")
    public CommonResult delete(@RequestBody WorkRecord workRecord){
        workRecordService.removeById(workRecord.getId());

        return CommonResult.success();
    }

    @RequestMapping("/getAll")
    public CommonResult getAll(){
        List<WorkRecord> list = workRecordService.list();

        return CommonResult.success(list);
    }

    @RequestMapping("/get")
    public CommonResult getById(@RequestParam("id") Integer id){
        WorkRecord workRecord = workRecordService.getById(id);

        return CommonResult.success(workRecord);
    }

}
