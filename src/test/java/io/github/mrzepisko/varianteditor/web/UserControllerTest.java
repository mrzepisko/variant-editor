package io.github.mrzepisko.varianteditor.web;

import io.github.mrzepisko.varianteditor.AbstractControllerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest extends AbstractControllerTest {
    private static final String validPassword = "ab2c1d3e";
    private static final String shortPassword = "a1bcde2";
    private static final String noDigitsPassword = "abcdefgh";

    @Test
    public void registerUser() throws Exception {
        mvc.perform(post("/users")
                .param("password", validPassword))
                .andExpect(status().isCreated())
        ;
    }

    @Test
    public void registerUserPasswordTooShort() throws Exception {
        mvc.perform(post("/users")
                .param("password", shortPassword))
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void registerUserPasswordNoDigits() throws Exception {
        mvc.perform(post("/users")
                .param("password", noDigitsPassword))
                .andExpect(status().isBadRequest())
        ;
    }
}
