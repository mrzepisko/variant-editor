package io.github.mrzepisko.varianteditor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id @GeneratedValue private Long id;

    @NonNull @Column(nullable = false, unique = true)
    @NotNull @NotEmpty
    private String identifier;

    @NonNull @Column(nullable = false)
    @NotNull @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
