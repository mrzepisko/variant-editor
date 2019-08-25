package io.github.mrzepisko.varianteditor.service;

import io.github.mrzepisko.varianteditor.dto.User;

public interface UserService {
    User register(String password);
    void assignVariant(Long variantId);
}
