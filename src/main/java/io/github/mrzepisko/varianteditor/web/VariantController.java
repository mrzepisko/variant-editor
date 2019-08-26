package io.github.mrzepisko.varianteditor.web;

import io.github.mrzepisko.varianteditor.UserRepository;
import io.github.mrzepisko.varianteditor.VariantRepository;
import io.github.mrzepisko.varianteditor.model.Variant;
import io.github.mrzepisko.varianteditor.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VariantController {
    private final VariantRepository variantRepository;
    private final UserService userService;

    public VariantController(VariantRepository variantRepository, UserService userService) {
        this.variantRepository = variantRepository;
        this.userService = userService;
    }

    @PostMapping("/variants")
    private Variant newVariant(@RequestBody Variant newVariant) {
        return variantRepository.save(newVariant);
    }

    @GetMapping("/variants") //TODO current user
    private List<Variant> getVariants() {
        return userService.getUserVariants();
    }

    @GetMapping("/variants/{id}")
    private Variant getVariant(@PathVariable Long id) throws VariantNotFoundException {
        return variantRepository.findById(id)
                .orElseThrow(VariantNotFoundException::new);
    }

    @PutMapping("/variants/{id}/assign")
    private Variant assignVariant(@PathVariable Long id, String userIdentifier) throws VariantNotFoundException, UsernameNotFoundException {
        return userService.assignVariant(id, userIdentifier);
    }
}
