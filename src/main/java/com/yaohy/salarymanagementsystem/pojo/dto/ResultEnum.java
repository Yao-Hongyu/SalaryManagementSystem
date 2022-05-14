package com.yaohy.salarymanagementsystem.pojo.dto;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS("0000","一切正常"),
    USER_WRONG_PASSWORD("1000","用户密码错误"),
    WRONG_TOKEN("1001","token错误"),
    OPERATION_FAILED("1002","操作失败"),
    USER_OPERATION_ERROR("2000","用户操作异常"),
    FORM_ERROR("2001","表单参数错误"),
    VALIDATE_FAIL("2002","参数校验失败");

    private String code;
    private String message;

    ResultEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

}
