package io.github.mrzepisko.varianteditor;

import io.github.mrzepisko.varianteditor.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariantRepository extends JpaRepository<Variant, Long> {
    List<Variant> findByUserIdentifier(String identifier);
}
