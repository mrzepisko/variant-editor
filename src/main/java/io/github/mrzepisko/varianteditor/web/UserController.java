package io.github.mrzepisko.varianteditor.web;

import io.github.mrzepisko.varianteditor.model.RegistrationForm;
import io.github.mrzepisko.varianteditor.model.User;
import io.github.mrzepisko.varianteditor.service.VariantEditorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private VariantEditorService userService;

    public UserController(VariantEditorService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@Valid RegistrationForm password) {
        return userService.registerUser(password.getPassword());
    }
}
