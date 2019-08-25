package io.github.mrzepisko.varianteditor.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
}