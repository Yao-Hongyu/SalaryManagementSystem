package com.yaohy.salarymanagementsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yaohy.salarymanagementsystem.pojo.domain.WorkRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkRecordMapper extends BaseMapper<WorkRecord> {
}