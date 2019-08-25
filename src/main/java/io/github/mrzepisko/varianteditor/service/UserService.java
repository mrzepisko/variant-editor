package io.github.mrzepisko.varianteditor.service;

import io.github.mrzepisko.varianteditor.model.User;

public interface UserService {
    User register(String password);
    void assignVariant(Long variantId);
}
