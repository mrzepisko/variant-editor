package io.github.mrzepisko.varianteditor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class VariantNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(VariantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String variantNotFoundHandler(VariantNotFoundException ex) {
        return ex.getMessage();
    }
}
