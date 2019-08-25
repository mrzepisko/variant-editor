package io.github.mrzepisko.varianteditor;

import io.github.mrzepisko.varianteditor.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant, Long> {
}
