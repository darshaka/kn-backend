package com.kn.booking.utils;

import com.kn.booking.domain.dto.BaseResponse;
import com.kn.booking.exception.KNException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler  {

    @ExceptionHandler(value = { KNException.class })
    protected ResponseEntity<Object> handleConflict(KNException exception) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message(exception.getMessage())
                .status("500")
                .build());
    }
}
