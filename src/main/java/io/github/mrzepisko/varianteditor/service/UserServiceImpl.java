package io.github.mrzepisko.varianteditor.service;

import io.github.mrzepisko.varianteditor.UserRepository;
import io.github.mrzepisko.varianteditor.VariantRepository;
import io.github.mrzepisko.varianteditor.model.User;
import io.github.mrzepisko.varianteditor.model.Variant;
import io.github.mrzepisko.varianteditor.web.VariantNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder;
    private IdentifierGenerator identifierGenerator;
    private UserRepository userRepository;
    private VariantRepository variantRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           VariantRepository variantRepository,
                           @Qualifier("AlphanumericGenerator") IdentifierGenerator identifierGenerator) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.variantRepository = variantRepository;
        this.identifierGenerator = identifierGenerator;
    }

    @Override
    public User register(String password) {
        User user = new User();
        user.setIdentifier(generateUniqueIdentifier());
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    private String generateUniqueIdentifier() {
        String identifier;
        do { //#TODO never - infinite loop
            identifier = identifierGenerator.generate();
        } while (userRepository.findByIdentifier(identifier).isPresent());
        return identifier;
    }

    @Override
    public Variant assignVariant(Long variantId, String userIdentifier) throws VariantNotFoundException {
        User user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found %s", userIdentifier)));
        Variant variant = variantRepository.findById(variantId).orElseThrow(VariantNotFoundException::new);
        variant.setUser(user);

        return variantRepository.save(variant);

    }

    @Override
    public List<Variant> getUserVariants() {
        return getCurrentUserId().map(variantRepository::findByUserIdentifier)
                .orElse(Collections.emptyList());
    }

    private Optional<String> getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Optional.of((org.springframework.security.core.userdetails.User) auth.getPrincipal())
                .map(org.springframework.security.core.userdetails.User::getUsername);

    }
}
