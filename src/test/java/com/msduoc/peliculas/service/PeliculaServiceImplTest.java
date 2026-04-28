package com.msduoc.peliculas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.msduoc.peliculas.model.Pelicula;
import com.msduoc.peliculas.repository.PeliculaRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceImplTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    @InjectMocks
    private PeliculaServiceImpl service;

    private Pelicula pelicula;

    @BeforeEach
    void setUp() {
        pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("pelicula1");
    }

    @Test
    void testGetAllPeliculas() {
        List<Pelicula> expected = Arrays.asList(pelicula);
        when(peliculaRepository.findAll()).thenReturn(expected);
        assertEquals(expected, service.getAllPeliculas());
    }

    @Test
    void testGetPeliculaById() {
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));
        assertEquals(Optional.of(pelicula), service.getPeliculaById(1L));
    }

    @Test
    void testCreatePelicula() {
        when(peliculaRepository.save(pelicula)).thenReturn(pelicula);
        assertEquals(pelicula, service.createPelicula(pelicula));
    }

    @Test
    void testUpdatePeliculaExists() {
        when(peliculaRepository.existsById(1L)).thenReturn(true);
        when(peliculaRepository.save(pelicula)).thenReturn(pelicula);
        Pelicula result = service.updatePelicula(1L, pelicula);
        assertEquals(1L, pelicula.getId());
        assertEquals(pelicula, result);
        verify(peliculaRepository).save(pelicula);
    }

    @Test
    void testUpdatePeliculaNotExists() {
        when(peliculaRepository.existsById(1L)).thenReturn(false);
        assertNull(service.updatePelicula(1L, pelicula));
        verify(peliculaRepository, never()).save(any());
    }

    @Test
    void testDeletePelicula() {
        service.deletePelicula(1L);
        verify(peliculaRepository).deleteById(1L);
    }
}
