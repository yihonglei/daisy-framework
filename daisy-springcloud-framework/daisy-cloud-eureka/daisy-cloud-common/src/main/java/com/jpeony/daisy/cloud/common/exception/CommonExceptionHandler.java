package com.jpeony.daisy.cloud.common.exception;

import com.jpeony.daisy.cloud.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    R exception(Exception e) {
        logger.error(e.getMessage(), e);
        return R.error(500, e.getMessage());
    }
}
