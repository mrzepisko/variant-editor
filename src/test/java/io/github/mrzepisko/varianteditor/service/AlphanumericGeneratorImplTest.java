package io.github.mrzepisko.varianteditor.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class AlphanumericGeneratorImplTest {

    @InjectMocks
    private AlphanumericGeneratorImpl service;

    @Test
    public void generate() {
        setup("a", 5);
        String value = service.generate();
        Assert.assertEquals(value, "aaaaa");
    }

    private void setup(String allowed, int size) {
        ReflectionTestUtils.setField(service, "allowed", allowed);
        ReflectionTestUtils.setField(service, "identifierLength", size);
    }
}