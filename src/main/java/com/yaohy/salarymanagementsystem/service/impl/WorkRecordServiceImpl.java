package com.yaohy.salarymanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaohy.salarymanagementsystem.mapper.WorkRecordMapper;
import com.yaohy.salarymanagementsystem.pojo.domain.WorkRecord;
import com.yaohy.salarymanagementsystem.service.WorkRecordService;
import org.springframework.stereotype.Service;

@Service
public class WorkRecordServiceImpl extends ServiceImpl<WorkRecordMapper, WorkRecord> implements WorkRecordService {
}
