package io.github.mrzepisko.varianteditor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mrzepisko.varianteditor.service.VariantEditorService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.reset;

@RunWith(SpringRunner.class)
public abstract class AbstractControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mvc;

    @MockBean
    protected UserDetailsService userDetailsService;

    @MockBean
    protected VariantEditorService variantEditorService;

    @Before
    public void Setup() {
        reset(userDetailsService, variantEditorService);
    }
}
