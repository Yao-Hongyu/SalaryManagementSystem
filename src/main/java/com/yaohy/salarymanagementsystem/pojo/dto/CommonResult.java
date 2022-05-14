package com.yaohy.salarymanagementsystem.pojo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResult<T> {

    private String code;

    private String message;

    private T data;


    public static <T> CommonResult<T> success() {
        return new CommonResult<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    public static <T> CommonResult<T> success(String message) {
        return new CommonResult<>(ResultEnum.SUCCESS.getCode(), message, null);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> fail() {
        return new CommonResult<>(ResultEnum.OPERATION_FAILED.getCode(),ResultEnum.OPERATION_FAILED.getMessage(), null);
    }

    public static <T> CommonResult<T> fail(String message) {
        return new CommonResult<>(ResultEnum.OPERATION_FAILED.getCode(),message, null);
    }

    public static <T> CommonResult<T> fail(String code, String message, T data) {
        return new CommonResult<>(code, message, data);
    }

    public static <T> CommonResult<T> formError() {
        return new CommonResult<>(ResultEnum.FORM_ERROR.getCode(), ResultEnum.FORM_ERROR.getMessage(), null);
    }

    public static <T> CommonResult<T> formError(String message) {
        return new CommonResult<>(ResultEnum.FORM_ERROR.getCode(), message, null);
    }

}
