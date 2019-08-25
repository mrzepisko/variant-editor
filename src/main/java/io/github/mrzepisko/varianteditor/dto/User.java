package io.github.mrzepisko.varianteditor.dto;

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
    @Id @GeneratedValue private Long id;

    @NonNull @Column(nullable = false, unique = true)
    @NotNull @NotEmpty
    private String identifier;

    @NonNull @Column(nullable = false)
    @NotNull @NotEmpty
    private String password;

}
