package io.github.mrzepisko.varianteditor;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(uniqueConstraints =
@UniqueConstraint(columnNames = {"position", "alteration", "chromosome"}))
public class Variant {
    private @Id @GeneratedValue Long id;
    private Long position;
    private String alteration;
    private String chromosome;
    private String opis;

}
