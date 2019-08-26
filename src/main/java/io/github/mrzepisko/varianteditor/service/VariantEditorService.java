package io.github.mrzepisko.varianteditor.service;

import io.github.mrzepisko.varianteditor.model.User;
import io.github.mrzepisko.varianteditor.model.Variant;
import io.github.mrzepisko.varianteditor.web.VariantNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface VariantEditorService {
    User register(String password);
    Variant create(Variant variant);

    Variant assignVariant(Long variantId, String userIdentifier) throws VariantNotFoundException, UsernameNotFoundException;
    List<Variant> getUserVariants();

    Optional<Variant> find(Long id);

}
