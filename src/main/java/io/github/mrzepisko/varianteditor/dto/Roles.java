package io.github.mrzepisko.varianteditor.dto;

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
