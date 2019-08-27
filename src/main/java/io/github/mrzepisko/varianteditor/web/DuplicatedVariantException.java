package io.github.mrzepisko.varianteditor.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "${exception.duplicated.variant}")
public class DuplicatedVariantException extends Exception {

}
