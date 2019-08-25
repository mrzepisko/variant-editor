package io.github.mrzepisko.varianteditor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationForm {
    @NonNull
    @NotNull
    @NotEmpty
    @Pattern(regexp = "(?=.*\\d).{8,}")
    private String password;
}
