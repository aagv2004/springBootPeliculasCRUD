package com.msduoc.peliculas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PeliculaModelTest {
    @Test
    void testGettersAndSetters() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("pelicula1");
        assertEquals(1L, pelicula.getId());
        assertEquals("pelicula1", pelicula.getTitulo());
    };
}
