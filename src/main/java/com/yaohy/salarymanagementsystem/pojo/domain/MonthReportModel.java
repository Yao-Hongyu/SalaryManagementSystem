package com.yaohy.salarymanagementsystem.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthReportModel {

    private String username;
    private String disobedientNum;
    private String leaveNum;
    private String leaveType;
    private String leaveTime;
    private String leaveReason;

}
