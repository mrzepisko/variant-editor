package io.github.mrzepisko.varianteditor.model;

public enum Roles {
    USER("USER"),

    ;
    private final String value;

    private Roles(String value) {
         this.value = value;
    }

    public String getValue() {
        return value;
    }
}
