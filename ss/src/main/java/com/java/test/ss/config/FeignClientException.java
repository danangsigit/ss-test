package com.java.test.ss.config;

import feign.FeignException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class FeignClientException extends FeignException {

    @Getter
    private int httpCode;

    public FeignClientException(String message) {
        this(HttpStatus.BAD_REQUEST.value(), message);
    }

    public FeignClientException(int httpCode, String message) {
        super(message);
        this.httpCode = httpCode;
    }


}
