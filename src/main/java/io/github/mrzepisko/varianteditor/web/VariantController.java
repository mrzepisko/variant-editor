package io.github.mrzepisko.varianteditor.web;

import com.fasterxml.jackson.annotation.JsonView;
import io.github.mrzepisko.varianteditor.model.Variant;
import io.github.mrzepisko.varianteditor.model.VariantView;
import io.github.mrzepisko.varianteditor.service.VariantEditorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VariantController {
    private final VariantEditorService service;

    public VariantController(VariantEditorService userService) {
        this.service = userService;
    }

    @PostMapping("/variants")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(VariantView.Basic.class)
    private Variant newVariant(@RequestBody @Valid Variant newVariant) {
        return service.createVariant(newVariant);
    }

    @GetMapping("/variants")
    @JsonView(VariantView.Aggr.class)
    private @ResponseBody List<Variant> getVariants() {
        return service.getUserVariants();
    }

    @GetMapping("/variants/{id}")
    @JsonView(VariantView.Extra.class)
    private Variant getVariant(@PathVariable Long id) throws VariantNotFoundException {
        return service.find(id).orElseThrow(VariantNotFoundException::new);
    }

    @PutMapping("/variants/{id}/assign")
    @JsonView(VariantView.Extra.class)
    private Variant assignVariant(@PathVariable Long id, String userIdentifier) throws VariantNotFoundException, UserNotFoundException {
        return service.assignVariant(id, userIdentifier);
    }
}
