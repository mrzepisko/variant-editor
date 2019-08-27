package io.github.mrzepisko.varianteditor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints =
@UniqueConstraint(columnNames = {"position", "alteration", "chromosome"}))
public class Variant implements VariantView.Basic {
    @Id @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(VariantView.Aggr.class)
    private Long id;

    @NonNull @Column(nullable = false, updatable = false)
    @NotNull
    @JsonView(VariantView.Basic.class)
    private Long position;
    @NonNull @Column(nullable = false, updatable = false)
    @NotNull @NotEmpty
    @JsonView(VariantView.Basic.class)
    private String alteration;
    @NonNull @Column(nullable = false, updatable = false)
    @NotNull @NotEmpty
    @JsonView(VariantView.Basic.class)
    private String chromosome;
    @NonNull @Column(nullable = false, updatable = false)
    @JsonView(VariantView.Aggr.class)
    private String opis;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn
    @JsonView(VariantView.Basic.class)
    private User user;
}
