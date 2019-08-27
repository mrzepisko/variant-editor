package io.github.mrzepisko.varianteditor.service;

import io.github.mrzepisko.varianteditor.dao.UserRepository;
import io.github.mrzepisko.varianteditor.dao.VariantRepository;
import io.github.mrzepisko.varianteditor.model.User;
import io.github.mrzepisko.varianteditor.model.Variant;
import io.github.mrzepisko.varianteditor.web.DuplicatedVariantException;
import io.github.mrzepisko.varianteditor.web.UserNotFoundException;
import io.github.mrzepisko.varianteditor.web.VariantNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class VariantEditorServiceImpl implements VariantEditorService {
    private PasswordEncoder passwordEncoder;
    private IdentifierGenerator identifierGenerator;
    private UserRepository userRepository;
    private VariantRepository variantRepository;

    public VariantEditorServiceImpl(PasswordEncoder passwordEncoder,
                                    UserRepository userRepository,
                                    VariantRepository variantRepository,
                                    @Qualifier("AlphanumericGenerator") IdentifierGenerator identifierGenerator) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.variantRepository = variantRepository;
        this.identifierGenerator = identifierGenerator;
    }

    @Override
    public User registerUser(String password) {
        User user = new User();
        user.setIdentifier(generateUniqueIdentifier());
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    @Override
    public Variant createVariant(Variant variant) throws DuplicatedVariantException {
        try {
            return variantRepository.save(variant);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicatedVariantException();
        }
    }

    @Override
    public Variant assignVariant(Long variantId, String userIdentifier) throws VariantNotFoundException, UserNotFoundException {
        User user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(UserNotFoundException::new);
        Variant variant = find(variantId).orElseThrow(VariantNotFoundException::new);
        variant.setUser(user);

        return variantRepository.save(variant);

    }

    @Override
    public List<Variant> getUserVariants() {
        return getCurrentUserId().map(variantRepository::findByUserIdentifier)
                .orElse(Collections.emptyList());
    }

    @Override
    public Optional<Variant> find(Long id) {
        return variantRepository.findById(id);
    }

    private String generateUniqueIdentifier() {
        String identifier;
        do { //#TODO never - infinite loop
            identifier = identifierGenerator.generate();
        } while (userRepository.findByIdentifier(identifier).isPresent());
        return identifier;
    }

    private Optional<String> getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Optional.of((org.springframework.security.core.userdetails.User) auth.getPrincipal())
                .map(org.springframework.security.core.userdetails.User::getUsername);

    }
}
