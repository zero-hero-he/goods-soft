package com.sw.goods.controller;

import com.sw.goods.constent.Result;
import com.sw.goods.exception.SoftException;
import com.sw.goods.vo.HttpResult;
import com.sw.goods.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/5
 */
@Slf4j
@ControllerAdvice
public class ExceptionhandlerController {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HttpResult<?> exception(HttpServletRequest request, Exception exception) {
        log.error("raised exception : ", exception);
        return new HttpResult<>(Result.ERROR_CODE, exception.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HttpResult<?> constraintViolationException(HttpServletRequest request, ConstraintViolationException exception) {
        log.error("ConstraintViolationException : ", exception);
        String msg = "";
        Set<ConstraintViolation<?>> constraintViolationSet =  exception.getConstraintViolations();
        Iterator<ConstraintViolation<?>> constraintViolationIterator =  constraintViolationSet.iterator();
        if (constraintViolationIterator.hasNext()) {
            msg = constraintViolationIterator.next().getMessageTemplate();
        }
        return new HttpResult<>(Result.ERROR_CODE, msg);
    }

    @ExceptionHandler(value = SoftException.class)
    @ResponseBody
    public HttpResult<?> softException(HttpServletRequest request, SoftException exception) {
        return new HttpResult<>(exception.getErrorCode(), exception.getMessage());
    }

    /**
     * 处理实体字段校验不通过异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public HttpResult<?> validationError(MethodArgumentNotValidException ex) {
        log.error("raised MethodArgumentNotValidException : ", ex);
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();
        for (FieldError error : fieldErrors) {
            builder.append(error.getDefaultMessage() + "\n");
        }
        return new HttpResult<>(Result.FEILD_ERROR, builder.toString());
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<HttpResult<String>> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HttpResult<>(e.getMessage()));
    }
}
