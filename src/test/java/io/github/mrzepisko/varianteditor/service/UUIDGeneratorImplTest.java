package io.github.mrzepisko.varianteditor.service;

import io.github.mrzepisko.varianteditor.AbstractServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UUIDGeneratorImplTest extends AbstractServiceTest {

    @InjectMocks
    private UUIDGeneratorImpl service;

    @Test
    public void generateTest() { //?
        String result = service.generate();
        assertTrue(result.length() > 0);
    }
}