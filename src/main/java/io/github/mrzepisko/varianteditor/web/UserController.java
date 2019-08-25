package io.github.mrzepisko.varianteditor.web;

import io.github.mrzepisko.varianteditor.model.User;
import io.github.mrzepisko.varianteditor.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseBody
    public User newUser(@RequestParam @Pattern(regexp = ".{8,}(?!\\d+)") String password) {
        return userService.register(password);
    }
}
