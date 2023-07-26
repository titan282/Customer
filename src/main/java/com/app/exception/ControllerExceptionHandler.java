package com.app.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

@RestControllerAdvice
@Component
public class ControllerExceptionHandler {
//
//    /**
//     * handle controller methods parameter validation exceptions
//     *
//     * @param exception ex
//     * @return wrapped result
//     */
//    @ExceptionHandler
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    public DataContainer handle(ConstraintViolationException exception) {
//
//        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
//        StringBuilder builder = new StringBuilder();
//        for (ConstraintViolation<?> violation : violations) {
//            builder.append(violation.getMessage());
//            break;
//        }
//        DataContainer container = new DataContainer(CommonCode.PARAMETER_ERROR_CODE, builder.toString());
//        return container;
//    }
}
