package com.java.test.ss.commons.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionDTO {
    private static final long serialVersionUID = 1L;

    private String message;
    private String code;
    private String traceId;

    public ExceptionDTO(String message, String code){
        this(message, code, null);
    }
    public ExceptionDTO(String message, String code, String traceId){
        this.message = message;
        this.code = code;
        this.traceId = traceId;
    }
}
