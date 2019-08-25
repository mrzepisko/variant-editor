package io.github.mrzepisko.varianteditor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private @Id @GeneratedValue Long id;
    private @NonNull Long position;
    private @NonNull String alteration;
    private @NonNull String chromosome;
    private @NonNull String opis;

}
