package com.leone.seckill.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.login.FailedLoginException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * @author leone
 **/
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SecKillException.class)
    public ExceptionResult handleBaseException(SecKillException e) {
        logger.error("{}", e.getMessage());
        return new ExceptionResult(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    public ExceptionResult handleAuthorizationException(Throwable e) {
        logger.error("{}", e.getMessage());
        return new ExceptionResult(ExceptionMessage.PERMISSION_DENIED.getCode(), ExceptionMessage.PERMISSION_DENIED.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ExceptionResult handleAuthenticationException(Throwable e) {
        logger.error("{}", e.getMessage());
        return new ExceptionResult(40003, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FailedLoginException.class)
    public ExceptionResult handleFailedLoginException(FailedLoginException e) {
        logger.error("{}", e.getMessage());
        return new ExceptionResult(40010, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionResult handleBaseException(IllegalArgumentException e) {
        logger.error("{}", e.getMessage());
        return new ExceptionResult(40000, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ExceptionResult handleBaseException(Throwable e) {
        e.printStackTrace();
        return new ExceptionResult(50000, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldError = e.getBindingResult().getFieldErrors();
        String[] arr = new String[fieldError.size()];
        for (int i = 0; i < fieldError.size(); i++) {
            arr[i] = fieldError.get(i).getDefaultMessage();
        }
        String result = Arrays.toString(arr);
        return new ExceptionResult(40007, result);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ExceptionResult bindException(BindException e) {
        List<FieldError> fieldError = e.getBindingResult().getFieldErrors();
        String[] arr = new String[fieldError.size()];
        for (int i = 0; i < fieldError.size(); i++) {
            arr[i] = fieldError.get(i).getDefaultMessage();
        }
        String result = Arrays.toString(arr);
        return new ExceptionResult(40008, result);
    }

}
