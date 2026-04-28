package com.msduoc.peliculas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.msduoc.peliculas.model.Pelicula;
import com.msduoc.peliculas.service.PeliculaService;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest
public class PeliculaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PeliculaService service;

    @Autowired
    private ObjectMapper mapper;

    private Pelicula pelicula;

    @BeforeEach
    void setUp() {
        pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("pelicula1");
    }

    @Test
    void testGetAllPeliculas() throws Exception {
        when(service.getAllPeliculas()).thenReturn(Arrays.asList(pelicula));
        mockMvc.perform(get("/peliculas"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(pelicula))));
    }

    @Test
    void testGetPeliculaById() throws Exception {
        when(service.getPeliculaById(1L)).thenReturn(Optional.of(pelicula));
        mockMvc.perform(get("/peliculas/1"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(pelicula)));
    }

    @Test
    void testCreatePelicula() throws Exception {
        when(service.createPelicula(any(Pelicula.class))).thenReturn(pelicula);
        mockMvc.perform(post("/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pelicula)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(pelicula)));
    }

    @Test
    void testUpdatePelicula() throws Exception {
        when(service.updatePelicula(eq(1L), any(Pelicula.class))).thenReturn(pelicula);
        mockMvc.perform(put("/peliculas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pelicula)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(pelicula)));
    }

    @Test
    void testDeletePelicula() throws Exception {
        mockMvc.perform(delete("/peliculas/1"))
                .andExpect(status().isOk());
        verify(service).deletePelicula(1L);
    }

}
