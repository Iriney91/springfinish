package ru.itfbgroup.telecom.services.notificationservice.component;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itfbgroup.telecom.services.notificationservice.common.web.Result;

import javax.persistence.EntityNotFoundException;


@Slf4j
@Component
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {EntityNotFoundException.class, RuntimeException.class})
    public ResponseEntity<Object> handleAllException(RuntimeException ex) {

        HttpStatus status;
        if (ex instanceof EntityNotFoundException) {
            log.warn(ex.getMessage());
            status = HttpStatus.NOT_FOUND;
        } else {
            log.error(ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Object>(
                Result.error(1, ex.getMessage(), null), status);
    }
}
