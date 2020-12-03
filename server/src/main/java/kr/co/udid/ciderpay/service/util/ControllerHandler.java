package kr.co.udid.ciderpay.service.util;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerHandler
{
    @ExceptionHandler(BindException.class)
    public String validationException (BindException e)
    {
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("입력된 값: [").append(fieldError.getRejectedValue()).append("]\n");
            builder.append("[").append(fieldError.getField()).append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
        }

        return builder.toString();
    }
}
