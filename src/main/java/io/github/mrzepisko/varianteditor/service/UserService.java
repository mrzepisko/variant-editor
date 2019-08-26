package io.github.mrzepisko.varianteditor.service;

import io.github.mrzepisko.varianteditor.model.User;
import io.github.mrzepisko.varianteditor.model.Variant;
import io.github.mrzepisko.varianteditor.web.VariantNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    User register(String password);
    Variant assignVariant(Long variantId, String userIdentifier) throws VariantNotFoundException, UsernameNotFoundException;
    List<Variant> getUserVariants();
}
