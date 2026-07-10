package com.roman.mangaapi.web;

import com.roman.mangaapi.model.Manga;
import com.roman.mangaapi.model.Status;
import com.roman.mangaapi.service.MangaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Test class for the MangaController.
 */
@WebMvcTest(MangaController.class)
public class MangaControllerTest {

    @Autowired 
    MockMvc mockMvc;
    @MockitoBean
    MangaService service;

    /**
     * Tests that the getMangaById method returns the correct manga.
     * @throws Exception if the test fails
     */
    @Test
    void getMangaById_returnsManga() throws Exception {
        when(service.findByMalId(1)).thenReturn(new Manga(1, "Test", 1, Status.PLAN_TO_READ));
        
        mockMvc.perform(get("/api/manga/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test"));
    }
}
