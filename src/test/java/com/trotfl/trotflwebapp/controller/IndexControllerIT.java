package com.trotfl.trotflwebapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Greg Stroud
 */
@WebMvcTest
class IndexControllerIT extends BaseIT{

    @Test
    void getIndexPageTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}