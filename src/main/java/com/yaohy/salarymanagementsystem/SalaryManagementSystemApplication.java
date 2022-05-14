package com.yaohy.salarymanagementsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yaohy.salarymanagementsystem.mapper")
@SpringBootApplication
public class SalaryManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalaryManagementSystemApplication.class, args);
    }

}
