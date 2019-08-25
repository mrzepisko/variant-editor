package io.github.mrzepisko.varianteditor;

import io.github.mrzepisko.varianteditor.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByIdentifier(String identifier);
}
