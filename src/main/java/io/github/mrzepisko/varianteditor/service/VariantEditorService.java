package io.github.mrzepisko.varianteditor.service;

import io.github.mrzepisko.varianteditor.model.User;
import io.github.mrzepisko.varianteditor.model.Variant;
import io.github.mrzepisko.varianteditor.web.DuplicatedVariantException;
import io.github.mrzepisko.varianteditor.web.UserNotFoundException;
import io.github.mrzepisko.varianteditor.web.VariantNotFoundException;

import java.util.List;
import java.util.Optional;

public interface VariantEditorService {
    User registerUser(String password);
    Variant createVariant(Variant variant) throws DuplicatedVariantException;

    Variant assignVariant(Long variantId, String userIdentifier) throws VariantNotFoundException, UserNotFoundException;
    List<Variant> getUserVariants();

    Optional<Variant> find(Long id);

}
