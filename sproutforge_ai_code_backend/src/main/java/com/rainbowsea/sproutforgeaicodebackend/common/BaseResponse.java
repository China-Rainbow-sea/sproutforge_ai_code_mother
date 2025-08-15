package com.rainbowsea.sproutforgeaicodebackend.common;

import com.rainbowsea.sproutforgeaicodebackend.exception.ErrorCode;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 通用响应类
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -7534002905486949682L;
    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}