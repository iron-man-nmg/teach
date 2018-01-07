package com.nmg.teach.core.exception;

/**
 * 封装teach的异常
 *
 * @author xiadingli
 * @Date 2017/12/28 下午10:32
 */
public class TeachException extends RuntimeException {

    private Integer code;

    private String message;

    public TeachException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
