package io.github.mrzepisko.varianteditor.web;

import io.github.mrzepisko.varianteditor.model.RegistrationForm;
import io.github.mrzepisko.varianteditor.model.User;
import io.github.mrzepisko.varianteditor.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseBody
    public User newUser(@Valid RegistrationForm password) {
        return userService.register(password.getPassword());
    }
}
