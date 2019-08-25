package io.github.mrzepisko.varianteditor;

import io.github.mrzepisko.varianteditor.dto.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant, Long> {
}
