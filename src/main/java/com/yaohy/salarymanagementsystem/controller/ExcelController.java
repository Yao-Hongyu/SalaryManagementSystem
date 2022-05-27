package com.yaohy.salarymanagementsystem.controller;

import com.yaohy.salarymanagementsystem.pojo.domain.MonthReportModel;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @GetMapping("/generate")
    public void generate(HttpServletResponse response){
        ArrayList<MonthReportModel> modelArrayList = new ArrayList<>();
        modelArrayList.add(new MonthReportModel("qhm","qhm","qhm","qhm","qhm","qhm"));
        modelArrayList.add(new MonthReportModel("qhm","qhm","qhm","qhm","qhm","qhm"));
        modelArrayList.add(new MonthReportModel("qhm","qhm","qhm","qhm","qhm","qhm"));
        modelArrayList.add(new MonthReportModel("qhm","qhm","qhm","qhm","qhm","qhm"));
        modelArrayList.add(new MonthReportModel("qhm","qhm","qhm","qhm","qhm","qhm"));
        exportExcel(response,modelArrayList);
    }

    public HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook workbook) {
        // 创建一个HSSFWorkbook，对应一个Excel文件
        if (workbook == null) {
            workbook = new HSSFWorkbook();
        }
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 在sheet中添加表头第0行
        HSSFRow row = sheet.createRow(0);
        // 创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 声明列对象
        HSSFCell cell = null;
        // 创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(cellStyle);
        }
        // 创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                // 将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return workbook;

    }

    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportExcel(HttpServletResponse response, List<MonthReportModel> monthReportModels) {
        // Excel标题
        String[] title = {"姓名", "违纪次数", "违纪原因", "请假次数", "请假类型", "请假累计时间（小时）", "请假原因描述"};
        // Excel文件名
        String fileName =  "请假统计.xls";
        // sheet名
        String sheetName = "请假统计";
        // 将数据放到数组中
        String[][] content = new String[monthReportModels.size()][title.length];
        for (int i = 0; i < monthReportModels.size(); i++) {
            MonthReportModel monthReportModel = monthReportModels.get(i);
            content[i][0] = monthReportModel.getUsername();
            content[i][1] = monthReportModel.getDisobedientNum();
            content[i][2] = monthReportModel.getDisobedientNum();
            content[i][3] = monthReportModel.getLeaveNum();
            content[i][4] = monthReportModel.getLeaveType();
            content[i][5] = monthReportModel.getLeaveTime() + "小时";
            content[i][6] = monthReportModel.getLeaveReason();
        }
        // 导出Excel
        try {
            HSSFWorkbook hssfWorkbook = getHSSFWorkbook(sheetName, title, content, null);
            setResponseHeader(response, fileName);
            OutputStream outputStream = response.getOutputStream();
            hssfWorkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
