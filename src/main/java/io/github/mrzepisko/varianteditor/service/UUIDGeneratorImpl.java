package io.github.mrzepisko.varianteditor.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("UUIDGenerator")
public class UUIDGeneratorImpl implements IdentifierGenerator  {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
