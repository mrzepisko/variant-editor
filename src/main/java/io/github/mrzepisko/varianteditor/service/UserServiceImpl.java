package io.github.mrzepisko.varianteditor.service;

import io.github.mrzepisko.varianteditor.UserRepository;
import io.github.mrzepisko.varianteditor.dto.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder;
    private IdentifierGenerator identifierGenerator;
    private UserRepository repository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, @Qualifier("AlphanumericGenerator") IdentifierGenerator identifierGenerator, UserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.identifierGenerator = identifierGenerator;
        this.repository = repository;
    }

    @Override
    public User register(String password) {
        User user = new User();
        user.setIdentifier(generateUniqueIdentifier());
        user.setPassword(passwordEncoder.encode(password)); //TODO
        //user.setPassword(password);

        return repository.save(user);
    }

    private String generateUniqueIdentifier() {
        String identifier;
        do {
            identifier = identifierGenerator.generate();
        } while (repository.findByIdentifier(identifier) != null);
        return identifier;
    }

    @Override
    public void assignVariant(Long variantId) {

    }
}
