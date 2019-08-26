package io.github.mrzepisko.varianteditor.web;

import io.github.mrzepisko.varianteditor.model.Variant;
import io.github.mrzepisko.varianteditor.service.VariantEditorService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VariantController {
    private final VariantEditorService service;

    public VariantController(VariantEditorService userService) {
        this.service = userService;
    }

    @PostMapping("/variants")
    private Variant newVariant(@RequestBody Variant newVariant) {
        return service.create(newVariant);
    }

    @GetMapping("/variants")
    private @ResponseBody List<Variant> getVariants() {
        return service.getUserVariants();
    }

    @GetMapping("/variants/{id}")
    private Variant getVariant(@PathVariable Long id) throws VariantNotFoundException {
        return service.find(id).orElseThrow(VariantNotFoundException::new);
    }

    @PutMapping("/variants/{id}/assign")
    private Variant assignVariant(@PathVariable Long id, String userIdentifier) throws VariantNotFoundException, UsernameNotFoundException {
        return service.assignVariant(id, userIdentifier);
    }
}
