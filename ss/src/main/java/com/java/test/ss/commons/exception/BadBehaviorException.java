package com.java.test.ss.commons.exception;

import org.springframework.util.StringUtils;

public class BadBehaviorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected final String errorCode;

    public BadBehaviorException(String message, String code){
        super(message);
        this.errorCode = StringUtils.isEmpty(code) ? "err.business.bad_behavior" : code;
    }

    public String getErrorCode(){
        return this.errorCode;
    }

}
