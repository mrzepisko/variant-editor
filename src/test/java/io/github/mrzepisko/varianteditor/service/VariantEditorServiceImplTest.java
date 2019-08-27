package io.github.mrzepisko.varianteditor.service;

import io.github.mrzepisko.varianteditor.AbstractServiceTest;
import io.github.mrzepisko.varianteditor.dao.UserRepository;
import io.github.mrzepisko.varianteditor.dao.VariantRepository;
import io.github.mrzepisko.varianteditor.model.User;
import io.github.mrzepisko.varianteditor.model.Variant;
import io.github.mrzepisko.varianteditor.web.UserNotFoundException;
import io.github.mrzepisko.varianteditor.web.VariantNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class VariantEditorServiceImplTest extends AbstractServiceTest {
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private IdentifierGenerator identifierGenerator;
    @Mock
    private UserRepository userRepository;
    @Mock
    private VariantRepository variantRepository;

    @InjectMocks
    private VariantEditorServiceImpl service;

    @Before
    public void SetUpTest() {
        reset(passwordEncoder, identifierGenerator, userRepository, variantRepository);
    }

    @Test
    public void registerTest() {
        String identifier = "sdkljhsdfkgjsdfg";
        String pass = "asdasd";
        when(identifierGenerator.generate()).thenReturn(identifier);
        when(passwordEncoder.encode(anyString())).thenReturn(pass);
        when(userRepository.findByIdentifier(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer((Answer<User>) i -> (User) i.getArgument(0));

        User user = service.registerUser("valid");

        Assert.assertEquals(identifier, user.getIdentifier());
        Assert.assertEquals(pass, user.getPassword());
    }

    @Test
    public void createTest() {
        when(variantRepository.save(any(Variant.class)))
                .thenAnswer((Answer<Variant>) i -> (Variant) i.getArgument(0));

        Variant variant = new Variant(1L, "alt1", "alt2", "lorem ipsum");
        Variant result = service.createVariant(variant);

        Assert.assertEquals(variant, result);
        Assert.assertEquals(variant.getPosition(), result.getPosition());
        Assert.assertEquals(variant.getAlteration(), result.getAlteration());
        Assert.assertEquals(variant.getOpis(), result.getOpis());
    }

    @Test
    public void assignVariantTest() throws VariantNotFoundException, UserNotFoundException {
        User user = spy(new User());
        Variant variant = spy(new Variant());
        when(userRepository.findByIdentifier(anyString())).thenReturn(Optional.of(user));
        when(variantRepository.findById(anyLong())).thenReturn(Optional.of(variant));
        when(variantRepository.save(variant))
                .thenAnswer((Answer<Variant>) i -> (Variant) i.getArgument(0));

        Variant result = service.assignVariant(1L, "any");

        verify(result, times(1)).setUser(user);
    }

    @Test(expected = UserNotFoundException.class)
    public void assignVariantTestNoUser() throws VariantNotFoundException, UserNotFoundException {
        when(userRepository.findByIdentifier(anyString())).thenReturn(Optional.empty());

        service.assignVariant(1L, "any");
    }

    @Test(expected = VariantNotFoundException.class)
    public void assignVariantTestNoVariant() throws VariantNotFoundException, UserNotFoundException {
        User user = spy(new User());
        when(userRepository.findByIdentifier(anyString())).thenReturn(Optional.of(user));
        when(variantRepository.findById(anyLong())).thenReturn(Optional.empty());

        service.assignVariant(1L, "any");
    }

    @Test
    public void getUserVariants() {
        mockApplicationUser();
        List<Variant> list = spy(new ArrayList<>());
        int testSize = 1239764;
        mockApplicationUser();
        when(list.size()).thenReturn(testSize);
        when(variantRepository.findByUserIdentifier(anyString())).thenReturn(list);

        List<Variant> result = service.getUserVariants();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        Assert.assertEquals(result.size(), testSize);
    }



    @Test
    public void getUserVariantsEmpty() {
        mockApplicationUser();
        when(variantRepository.findByUserIdentifier(anyString())).thenReturn(Collections.emptyList());

        List<Variant> result = service.getUserVariants();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}