package com.java.test.ss.controller;

import com.java.test.ss.commons.exception.ExceptionDTO;
import com.java.test.ss.commons.rest.AbstractRestController;
import com.java.test.ss.commons.rest.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BaseController extends AbstractRestController implements ErrorController {

    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
