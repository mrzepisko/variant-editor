package io.github.mrzepisko.varianteditor.web;

import io.github.mrzepisko.varianteditor.AbstractControllerTest;
import io.github.mrzepisko.varianteditor.model.Variant;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VariantController.class)
public class VariantControllerTest extends AbstractControllerTest {
    private static final String urlVariants = "/variants";
    private static final String urlSingle = "/variants/{id}";
    private static final String urlAssign = "/variants/{id}/assign";

    @Test
    public void getVariant() throws Exception {
        Variant v = new Variant(1L, 1L, "alt", "chr", "lorem ipsum", null);
        when(variantEditorService.find(anyLong())).thenReturn(Optional.of(v));
        ResultActions action = mvc.perform(get(urlSingle, v.getId()))
                .andDo(print())
                .andExpect(status().isOk());
        checkResultData(v, action, true);
    }

    @Test
    public void getVariantNull() throws Exception {
        when(variantEditorService.find(anyLong())).thenReturn(Optional.empty());
        mvc.perform(get(urlSingle, 0))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void assignVariant() throws Exception {
        Variant v = new Variant(2L, 5L, "a2lt", "ch5r", "lsorem ispsum", null);
        when(variantEditorService.assignVariant(anyLong(), anyString())).thenReturn(v);
        ResultActions result = mvc.perform(put(urlAssign, v.getId()).param("userIdentifier", "0"))
                .andDo(print())
                .andExpect(status().isOk());
        checkResultData(v, result, true);
    }

    @Test
    public void assignVariantNoUser() throws Exception {
        when(variantEditorService.assignVariant(anyLong(), anyString())).thenThrow(new VariantNotFoundException());
        mvc.perform(put(urlAssign, 0).param("userIdentifier", "0"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void assignVariantNoVariant() throws Exception {
        when(variantEditorService.assignVariant(anyLong(), anyString())).thenThrow(new UserNotFoundException());
        mvc.perform(put(urlAssign, 0).param("userIdentifier", "0"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    @WithAnonymousUser
    public void getVariantsUnauthorized() throws Exception {
        mvc.perform(get(urlVariants))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    public void getVariants() throws Exception {
        Variant v1 = new Variant(5L, 2L, "a3lt", "csdr", "dfssdd ispsum", null);
        Variant v2 = new Variant(7L, 67L, "adfgdfsgt", "sdfgsdfggg", "dfssdd ispsum", null);
        when(variantEditorService.getUserVariants()).thenReturn(Arrays.asList(v1, v2));

        mvc.perform(get(urlVariants))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    @WithMockUser
    public void getVariantsEmpty() throws Exception {
        when(variantEditorService.getUserVariants()).thenReturn(Collections.emptyList());

        mvc.perform(get(urlVariants))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void newVariant() throws Exception {
        Variant v = new Variant(34563456L, "fyikty", "dtfbyj", "dfghdfgnl;kjth");
        when(variantEditorService.createVariant(any(Variant.class))).thenAnswer(i -> i.getArgument(0));

        ResultActions result = mvc.perform(post(urlVariants)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(v)))
                .andExpect(status().isCreated());
        checkResultData(v, result);
    }

    private void checkResultData(Variant v, ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("$.position", is(v.getPosition().intValue())))
                .andExpect(jsonPath("$.alteration", is(v.getAlteration())))
                .andExpect(jsonPath("$.chromosome", is(v.getChromosome())))
                .andExpect(jsonPath("$.opis", is(v.getOpis())))
        ;
    }

    private void checkResultData(Variant v, ResultActions action, boolean checkId) throws Exception {
        if (checkId) {
            action.andExpect(jsonPath("$.id", is(v.getId().intValue())));
        }
        checkResultData(v, action);
    }
}
