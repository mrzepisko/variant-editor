package io.github.mrzepisko.varianteditor.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service("AlphanumericGenerator")
public class AlphanumericGeneratorImpl implements IdentifierGenerator {
    private static final String allowed = "abcdefghijklmnopqrstuwxyz0123456789";


    @Override
    public String generate() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        final int length = 16;
        for (int i = 0; i < length; i++) {
            sb.append(allowed.charAt(random.nextInt(allowed.length())));
        }
        return sb.toString();
    }
}
