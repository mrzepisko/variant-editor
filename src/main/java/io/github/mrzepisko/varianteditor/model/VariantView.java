package io.github.mrzepisko.varianteditor.model;

public interface VariantView {
    public interface Aggr {
        String getOpis();
    }

    public interface Basic extends Aggr {
        Long getId();
        Long getPosition();
        String getAlteration();
        String getChromosome();
    }

    public interface Extra extends Basic {
        User getUser();
    }
}