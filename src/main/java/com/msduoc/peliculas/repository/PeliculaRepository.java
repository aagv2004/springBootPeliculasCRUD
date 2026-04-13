package com.msduoc.peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.msduoc.peliculas.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{

}
