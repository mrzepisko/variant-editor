package io.github.mrzepisko.varianteditor.model;

public interface VariantView {
    public interface Aggr {
        Long getId();
        String getOpis();
    }

    public interface Basic extends Aggr {
        Long getPosition();
        String getAlteration();
        String getChromosome();
        User getUser();
    }
}