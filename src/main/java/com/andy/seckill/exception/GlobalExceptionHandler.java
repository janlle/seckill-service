package com.andy.seckill.exception;

import com.andy.seckill.common.MessageEnum;
import com.andy.seckill.common.Response;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Leone
 **/
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidateException.class)
    public Response handleBaseException(ValidateException e) {
        logger.error("{}", e.getMessage());
        return Response.build(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    public Response handleAuthorizationException(Throwable e) {
        logger.error("{}", e.getMessage());
        return Response.build(MessageEnum.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public Response handleAuthenticationException(Throwable e) {
        logger.error("{}", e.getMessage());
        return Response.build(MessageEnum.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Response handleBaseException(IllegalArgumentException e) {
        logger.error("{}", e.getMessage());
        return Response.build(MessageEnum.BAD_REQUEST);
    }

}
