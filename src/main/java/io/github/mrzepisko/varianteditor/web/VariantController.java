package io.github.mrzepisko.varianteditor.web;

import io.github.mrzepisko.varianteditor.VariantRepository;
import io.github.mrzepisko.varianteditor.model.Variant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VariantController {
    private final VariantRepository repository;

    public VariantController(VariantRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/variants")
    private Variant newVariant(@RequestBody Variant newVariant) {
        return repository.save(newVariant);
    }

    @GetMapping("/variants/{id}")
    private Variant getVariant(@PathVariable Long id) throws VariantNotFoundException {
        return repository.findById(id)
                .orElseThrow(VariantNotFoundException::new);
    }

    @GetMapping("/variants")
    private List<Variant> getVariants() {
        return repository.findAll();
    }
}
