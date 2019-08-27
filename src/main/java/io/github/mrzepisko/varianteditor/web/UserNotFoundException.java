package io.github.mrzepisko.varianteditor.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "@exception.not_found.user@")
public class UserNotFoundException extends Exception {
}
