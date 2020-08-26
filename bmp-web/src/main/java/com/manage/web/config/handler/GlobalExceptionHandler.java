package com.manage.web.config.handler;


import com.manage.common.util.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @version : V1.0
 * @ClassName: com.ydm.springboot.handlerGlobalExceptionHandler
 * @Description: 全局异常处理类
 * @Auther: ydm
 * @Date: 2019/8/3115:05
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public ServerResponse runtimeExceptionHandler(RuntimeException ex) {
        return resultFormat(1, ex);
    }

    //无权限访问
    @ExceptionHandler(AccessDeniedException.class)
    public ServerResponse AccessDeniedExceptionHandler(RuntimeException ex) {
        return ServerResponse.createByErrorCodeMessage(403,"无权限访问");
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public ServerResponse nullPointerExceptionHandler(NullPointerException ex) {
        return resultFormat(2, ex);
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public ServerResponse classCastExceptionHandler(ClassCastException ex) {
        return resultFormat(3, ex);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public ServerResponse iOExceptionHandler(IOException ex) {
        return resultFormat(4, ex);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public ServerResponse noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return resultFormat(5, ex);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ServerResponse indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return resultFormat(6, ex);
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ServerResponse requestNotReadable(HttpMessageNotReadableException ex) {
        return resultFormat(7, ex);
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public ServerResponse requestTypeMismatch(TypeMismatchException ex) {
        return resultFormat(8, ex);
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ServerResponse requestMissingServletRequest(MissingServletRequestParameterException ex) {
        return resultFormat(9, ex);
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ServerResponse request405(HttpRequestMethodNotSupportedException ex) {
        return resultFormat(10, ex);
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ServerResponse request406(HttpMediaTypeNotAcceptableException ex) {
        return resultFormat(11, ex);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public ServerResponse server500(RuntimeException ex) {
        return resultFormat(12, ex);
    }

    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    public ServerResponse requestStackOverflow(StackOverflowError ex) {
        return resultFormat(13, ex);
    }

    //除数不能为0
    @ExceptionHandler({ArithmeticException.class})
    public ServerResponse arithmeticException(ArithmeticException ex) {
        return resultFormat(13, ex);
    }


    //其他错误
    @ExceptionHandler({Exception.class})
    public ServerResponse exception(Exception ex) {
        return resultFormat(14, ex);
    }

    private <T extends Throwable> ServerResponse resultFormat(int code, T ex) {
        if(ex instanceof RuntimeException){
            return ServerResponse.createByErrorCodeMessage(500,"服务器异常");
        }else if(ex instanceof AuthenticationServiceException){
            return ServerResponse.createByErrorCodeMessage(1,"认证异常");
        }
        return ServerResponse.createByErrorCodeMessage(code,ex.getMessage().toString());
    }



}
