package io.github.mrzepisko.varianteditor.dao;

import io.github.mrzepisko.varianteditor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdentifier(String identifier);
}
