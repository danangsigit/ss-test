package com.java.test.ss.commons.rest;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.java.test.ss.commons.exception.BadBehaviorException;
import com.java.test.ss.commons.exception.ExceptionDTO;
import com.java.test.ss.commons.exception.RestClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.java.test.ss.commons.core.ResourceBundle;

public class AbstractRestController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRestController.class);

    @Autowired
    protected ResourceBundle resourceBundle;

    protected ResponseEntity<RestResult<String>> createErrorResponse(HttpStatus status, String message) {
        RestResult<String> restResult = new RestResult<>();
        restResult.fail(message);
        return new ResponseEntity<>(restResult, status);
    }

    @ExceptionHandler({RestClientException.class})
    @ResponseBody
    public ResponseEntity<RestResult<Object>> handleRestClientException(RestClientException exception, HttpServletRequest request) {
        logException(exception, request);
        List<String> errors = getRecMsgErrors(exception, null);
        return createExceptionResponse(
                HttpStatus.valueOf(exception.getHttpStatusCode()), errors,
                new ExceptionDTO(exception.getMessage(), exception.getErrorCode(), getTraceId()));
    }

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseBody
    public ResponseEntity<RestResult<Object>> handleNotFoundException(NoSuchElementException exception, HttpServletRequest request) {
        logException(exception, request);
        List<String> exceptions = getRecMsgErrors(exception, null);
        return createExceptionResponse(
                HttpStatus.BAD_REQUEST, exceptions,
                new ExceptionDTO(exception.getMessage(), "err.client.data_not_found", getTraceId()));
    }

    @ExceptionHandler({BadBehaviorException.class})
    @ResponseBody
    public ResponseEntity<RestResult<Object>> handleBackendException(BadBehaviorException exception, HttpServletRequest request) {
        logException(exception, request);
        List<String> exceptions = getRecMsgErrors(exception, null);
        return createExceptionResponse(
                HttpStatus.NOT_ACCEPTABLE, exceptions,
                new ExceptionDTO(exception.getMessage(), exception.getErrorCode(), getTraceId()));
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseBody
    public ResponseEntity<RestResult<Object>> handleIllegalException(Exception exception, HttpServletRequest request) {
        logException(exception, request);
        List<String> exceptions = getRecMsgErrors(exception, null);
        return createExceptionResponse(
                HttpStatus.BAD_REQUEST, exceptions,
                new ExceptionDTO(exception.getMessage(), "err.client.illegal_argument", getTraceId()));
    }

    @ExceptionHandler({ServletException.class, HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseEntity<RestResult<Object>> handleClientException(Exception exception, HttpServletRequest request) {
        logException(exception, request);
        List<String> exceptions = getRecMsgErrors(exception, null);
        return createExceptionResponse(
                HttpStatus.BAD_REQUEST, exceptions,
                new ExceptionDTO(exception.getMessage(), "err.client.illegal_argument", getTraceId()));
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public ResponseEntity<RestResult<Object>> handleUnexpectedException(RuntimeException exception, HttpServletRequest request) {
        logException(exception, request);
        String generalMsg = resourceBundle.getMessage("error.server.unexpected");
        List<String> exceptions = getRecMsgErrors(exception, null);
        exceptions.add(0, generalMsg);
        return createExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, exceptions,
                new ExceptionDTO(generalMsg, "error.server.unexpected", getTraceId()));
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<RestResult<Object>> handleException(Exception exception, HttpServletRequest request) {
        logException(exception, request);
        String generalMsg = resourceBundle.getMessage("error.server.unexpected");
        List<String> exceptions = getRecMsgErrors(exception, null);
        exceptions.add(0, generalMsg);
        return createExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, exceptions,
                new ExceptionDTO(generalMsg, "error.server.unexpected", getTraceId()));
    }

    protected Logger getLogger(){
        return LOG;
    }
    protected ResponseEntity<RestResult<Object>> createExceptionResponse(
            HttpStatus httpStatus,
            List<String> messages,
            ExceptionDTO data) {
        RestResult<Object> result = new RestResult<>();
        result.setStatus(RestResult.STATUS_ERROR).setData(data);
        for (String message : messages) {
            result.addMessage(message);
        }
        return new ResponseEntity<>(result, httpStatus);
    }

    protected void logException(Exception e, HttpServletRequest request) {
        String actor = "SYSTEM";
        getLogger().error("{}@{} - {} : {}", actor, request.getHeader("User-Agent"), request.getRequestURI(), e.getMessage(), e);

    }

    protected List<String> getRecMsgErrors(Throwable e, List<String> msgs) {
        ArrayList<String> errors = new ArrayList<>();
        if (msgs != null) errors.addAll(msgs);
        errors.add(e.getMessage());
        if (e.getCause() != null) return getRecMsgErrors(e.getCause(), errors);
        return errors;
    }

    protected String getTraceId() {
        return "0001";
    }

}
