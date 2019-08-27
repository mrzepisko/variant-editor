package io.github.mrzepisko.varianteditor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints =
@UniqueConstraint(columnNames = {"position", "alteration", "chromosome"}))
public class Variant {
    @Id @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NonNull @Column(nullable = false, updatable = false)
    private Long position;

    @NonNull @Column(nullable = false, updatable = false)
    private String alteration;
    @NonNull @Column(nullable = false, updatable = false)
    private String chromosome;
    @NonNull @Column(nullable = false, updatable = false)
    private String opis;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
}
