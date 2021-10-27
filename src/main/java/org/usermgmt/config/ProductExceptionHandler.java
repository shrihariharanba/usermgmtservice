package org.usermgmt.config;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.usermgmt.util.ExceptionType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice(annotations = RestController.class)
@Component
public class ProductExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public void resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Exception exception) {
        if(exception instanceof ProductException) {
            ProductException e = (ProductException) exception;
            response.setStatus(e.getCode());
            response.setHeader("Error",e.getType());
            response.setHeader("Error-Message",e.getMessage());
        }else {
            exception.printStackTrace();
            response.setStatus(ExceptionType.INTERNAL_SERVER_ERROR.getCode());
            response.setHeader("Error", ExceptionType.INTERNAL_SERVER_ERROR.getType());
            response.setHeader("Error-Message", exception.getMessage());
        }
    }
}
