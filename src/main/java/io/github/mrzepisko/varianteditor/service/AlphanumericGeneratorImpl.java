package io.github.mrzepisko.varianteditor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("AlphanumericGenerator")
public class AlphanumericGeneratorImpl implements IdentifierGenerator {
    @Value("${generator.identifier.length:6}")
    private Integer identifierLength;


    @Value("${generator.identifier.allowed}")
    private String allowed;


    @Override
    public String generate() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < identifierLength; i++) {
            sb.append(allowed.charAt(random.nextInt(allowed.length())));
        }
        return sb.toString();
    }
}
